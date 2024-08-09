package api.modsen.library.services;

import api.modsen.library.config.security.JwtProvider;
import api.modsen.library.entities.authorization.JwtAuthentication;
import api.modsen.library.entities.authorization.JwtRequest;
import api.modsen.library.entities.authorization.JwtResponse;
import api.modsen.library.entities.authorization.User;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

import static api.modsen.library.config.LibraryAppConstants.*;

@Service
public class AuthenticationService {
    @Autowired
    private UserService userService;
    private Map<String, String> refreshStorage = new HashMap<>();
    @Autowired
    private JwtProvider jwtProvider;

    public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException{
        final User user = userService.getByLogin(authRequest.getUsername())
                .orElseThrow(() -> new AuthException(USER_NOT_FOUND_MESSAGE));
        if (user.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getUsername(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException(INVALID_TOKEN_OR_INCORRECT_PASSWORD_MESSAGE);
        }
    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) throws AuthException{
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String username = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(username);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByLogin(username)
                        .orElseThrow(() -> new AuthException(USER_NOT_FOUND_MESSAGE));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse refresh(@NonNull String refreshToken) throws AuthException{
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByLogin(login)
                        .orElseThrow(() -> new AuthException(USER_NOT_FOUND_MESSAGE));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getUsername(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException(INVALID_JWT_SIGNATURE);
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}

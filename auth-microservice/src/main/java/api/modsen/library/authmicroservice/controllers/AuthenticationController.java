package api.modsen.library.authmicroservice.controllers;

import api.modsen.library.authmicroservice.entities.authorization.JwtRequest;
import api.modsen.library.authmicroservice.entities.authorization.JwtResponse;
import api.modsen.library.authmicroservice.entities.authorization.RefreshJwtRequest;
import api.modsen.library.authmicroservice.services.AuthenticationService ;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth", produces = "application/json")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid
                                                 @RequestBody JwtRequest authRequest)
            throws AuthException {
        final JwtResponse token = authenticationService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> getNewAccessToken(@Valid
                                                             @RequestBody RefreshJwtRequest request)
            throws AuthException {
        final JwtResponse token = authenticationService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/reissue")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@Valid
                                                              @RequestBody RefreshJwtRequest request)
            throws AuthException {
        final JwtResponse token = authenticationService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
}

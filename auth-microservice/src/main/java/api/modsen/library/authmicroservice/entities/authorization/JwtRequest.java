package api.modsen.library.authmicroservice.entities.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import static api.modsen.library.authmicroservice.config.AuthAppConstants .BLANK_USERNAME_MESSAGE;
import static api.modsen.library.authmicroservice.config.AuthAppConstants .BLANK_PASSWORD_MESSAGE;

@Getter
@Setter
public class JwtRequest {
    @JsonProperty("username")
    @NotBlank(message = BLANK_USERNAME_MESSAGE)
    private String username;

    @JsonProperty("password")
    @NotBlank(message = BLANK_PASSWORD_MESSAGE)
    private String password;
}

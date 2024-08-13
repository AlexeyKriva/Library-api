package api.modsen.library.authmicroservice.entities.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import static api.modsen.library.authmicroservice.config.AuthAppConstants.BLANK_REFRESH_TOKEN_MESSAGE;

@Getter
@Setter
public class RefreshJwtRequest {
    @JsonProperty("refreshToken")
    @NotBlank(message = BLANK_REFRESH_TOKEN_MESSAGE)
    public String refreshToken;
}
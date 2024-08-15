package api.modsen.library.authmicroservice.entities.authorization;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private Set<Role> roles;
}

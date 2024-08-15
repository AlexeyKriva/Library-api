package api.modsen.library.authmicroservice.services;

import api.modsen.library.authmicroservice.entities.authorization.Role;
import api.modsen.library.authmicroservice.entities.authorization.User;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users;

    public UserService() {
        this.users = List.of(
                new User("user1", "password1",Collections.singleton(Role.USER)),
                new User("user2", "password2",Collections.singleton(Role.ADMIN))
        );
    }

    public Optional<User> getByLogin(@NonNull String username) {
        return users.stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }
}

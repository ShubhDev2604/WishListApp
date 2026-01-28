package in.lifehive.notes_backend.model;

import lombok.Data;

@Data
public class UserLoginResponse {
    Long id;
    String email;
    String accessToken;
    String tokenType;
    Role role;
    Long expiresIn;
}

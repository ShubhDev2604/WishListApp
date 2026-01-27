package in.lifehive.notes_backend.service;

import in.lifehive.notes_backend.exception.PasswordNotMatchingException;
import in.lifehive.notes_backend.exception.UserNotFoundException;
import in.lifehive.notes_backend.model.*;
import in.lifehive.notes_backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private UserRepository repo;
    private BCryptPasswordEncoder encoder;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<Users> fetchAllUsers() {
        return repo.findAll();
    }

    public Users fetchUserById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public UserLoginResponse insertNewUsers(UserRequest user) {
        Users userForRepo = new Users();
        userForRepo.setEmail(user.getEmail());
        userForRepo.setPassword(encoder.encode(user.getPassword()));
        userForRepo.setRole(Role.USER);
        userForRepo.setProvider(AuthProvider.LOCAL);
        userForRepo.setEnabled(true);
        userForRepo = repo.save(userForRepo);
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setEmail(userForRepo.getEmail());
        userLoginResponse.setRole(userForRepo.getRole());
        userLoginResponse.setId(userForRepo.getId());
        userLoginResponse.setTokenType("Bearer");
        userLoginResponse.setAccessToken("");
        return new UserLoginResponse();
    }

    public UserLoginResponse verifyUser(UserRequest userRequest) throws Exception {
        String encodedPassword = encoder.encode(userRequest.getPassword());
        Users userFromRepo = repo.findByEmail(userRequest.getEmail());
        UserLoginResponse response = null;
        if(userFromRepo == null) {
            throw new UserNotFoundException();
        }
        if(Objects.equals(encodedPassword, userFromRepo.getPassword())) {
            response.setEmail(userFromRepo.getEmail());
            response.setRole(userFromRepo.getRole());
            response.setId(userFromRepo.getId());
            response.setTokenType("Bearer");
            response.setAccessToken("");
            return new UserLoginResponse();
        } else {
            throw new PasswordNotMatchingException();
        }
    }
}

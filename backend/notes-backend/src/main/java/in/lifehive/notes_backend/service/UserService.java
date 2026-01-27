package in.lifehive.notes_backend.service;

import in.lifehive.notes_backend.exception.PasswordNotMatchingException;
import in.lifehive.notes_backend.exception.UserNotFoundException;
import in.lifehive.notes_backend.model.*;
import in.lifehive.notes_backend.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private UserRepository repo;
    private BCryptPasswordEncoder encoder;
    private AuthenticationManager authenticationManager;
    private JWTService jwtService;

    public UserService(UserRepository repo, BCryptPasswordEncoder encoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.repo = repo;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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
        userLoginResponse.setAccessToken(jwtService.generateToken(user.getEmail()));
        userLoginResponse.setEmail(userForRepo.getEmail());
        userLoginResponse.setRole(userForRepo.getRole());
        userLoginResponse.setId(userForRepo.getId());
        userLoginResponse.setTokenType("Bearer");
        return userLoginResponse;
    }

    public UserLoginResponse verifyUser(UserRequest userRequest) throws Exception {
        Users userFromRepo = repo.findByEmail(userRequest.getEmail());
        UserLoginResponse response = new UserLoginResponse();
        if(userFromRepo == null) {
            throw new UserNotFoundException();
        }
        if(encoder.matches(userRequest.getPassword(), userFromRepo.getPassword())) {
            response.setEmail(userFromRepo.getEmail());
            response.setRole(userFromRepo.getRole());
            response.setId(userFromRepo.getId());
            response.setTokenType("Bearer");
            response.setAccessToken(jwtService.generateToken(userFromRepo.getEmail()));
            return response;
        } else {
            throw new PasswordNotMatchingException();
        }
//        Authentication authentication =
//                authenticationManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(
//                                userRequest.getEmail(),
//                                userRequest.getPassword()
//                        )
//                );
//        Users userFromRepo = repo.findByEmail(userRequest.getEmail());
//        UserLoginResponse response = new UserLoginResponse();
//        if(authentication.isAuthenticated()) {
//            response.setEmail(userFromRepo.getEmail());
//            response.setRole(userFromRepo.getRole());
//            response.setId(userFromRepo.getId());
//            response.setTokenType("Bearer");
//            response.setAccessToken(jwtService.generateToken(userFromRepo.getEmail()));
//            return response;
//        } else {
//            throw new PasswordNotMatchingException();
//        }
    }
}

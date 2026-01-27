package in.lifehive.notes_backend.controller;

import in.lifehive.notes_backend.model.UserLoginResponse;
import in.lifehive.notes_backend.model.UserRequest;
import in.lifehive.notes_backend.model.Users;
import in.lifehive.notes_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
public class UserController {
    public UserController(UserService service) {
        this.service = service;
    }

    UserService service;

    @GetMapping("/users")
    ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(service.fetchAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    ResponseEntity<?> getAllUsers(@PathVariable Long id) {
        Users user = service.fetchUserById(id);
        if(user == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Resource not found");
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PostMapping("/signup")
    ResponseEntity<?> userSignup(@Valid @RequestBody UserRequest userRequest) {
        UserLoginResponse response = service.insertNewUsers(userRequest);
        if(response != null) {
            return ResponseEntity
                    .ok()
                    .body(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Something went wrong!");
        }
    }

    @PostMapping("/login")
    ResponseEntity<?> userLogin(@Valid @RequestBody UserRequest userRequest) throws Exception {
        UserLoginResponse response = service.verifyUser(userRequest);
        if(response != null) {
            return ResponseEntity
                    .ok()
                    .body(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Something went wrong!");
        }
    }
}

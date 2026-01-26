package in.lifehive.notes_backend.controller;

import in.lifehive.notes_backend.model.Users;
import in.lifehive.notes_backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

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

    @GetMapping("/csrf-token")
    ResponseEntity<CsrfToken> getCsrfToken(HttpServletRequest request) {
        return ResponseEntity.ok().body((CsrfToken) request.getAttribute("_csrf"));
    }

    @PostMapping("/user")
    ResponseEntity<String> insertNewUser(@RequestBody Users user) {
        Users user1 = service.insertNewUsers(user);
        if(user1 != null) {
            return ResponseEntity
                    .ok()
                    .body("User added");
        } else {
            return ResponseEntity
                    .internalServerError()
                    .body("Something went wrong");
        }
    }
}

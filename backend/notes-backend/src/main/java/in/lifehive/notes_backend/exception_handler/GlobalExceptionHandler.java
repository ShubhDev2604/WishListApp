package in.lifehive.notes_backend.exception_handler;

import in.lifehive.notes_backend.exception.PasswordNotMatchingException;
import in.lifehive.notes_backend.exception.UserNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleNoResource(NoResourceFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Endpoint not found");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> duplicateMail(DataIntegrityViolationException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("An account with this email already exists!!");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFound(UserNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Email not registered!!!");
    }

    @ExceptionHandler(PasswordNotMatchingException.class)
    public ResponseEntity<String> handleAll(PasswordNotMatchingException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Invalid password!!!");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong");
    }
}

package in.lifehive.notes_backend.service;

import in.lifehive.notes_backend.model.Users;
import in.lifehive.notes_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<Users> fetchAllUsers() {
        return repo.findAll();
    }

    public Users fetchUserById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Users insertNewUsers(Users user) {
        return repo.save(user);
    }

}

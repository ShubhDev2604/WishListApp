package in.lifehive.notes_backend.repository;

import in.lifehive.notes_backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}

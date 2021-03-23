package backend.repository;

import backend.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
 
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findById(Long id);

  List<User> findByName(String name);

  List<User> findByEmail(String email);

  List<User> findAll();
}

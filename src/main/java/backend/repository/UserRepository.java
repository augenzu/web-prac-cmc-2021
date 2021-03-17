package backend.repository;

import backend.entity.User;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 
public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findAllByName(String name);

  List<User> findAllById(Long id);

  List<User> findAll();
}

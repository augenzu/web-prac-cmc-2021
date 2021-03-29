package backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import backend.entity.Order;
import backend.entity.User;
 
public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findByNameContainingIgnoreCase(String name);

  List<User> findByEmailIgnoreCase(String email);

  @Query("select u.orders from User u where u.id = :id")
  List<Order> findUserOrdersById(@Param("id") Long id);
}

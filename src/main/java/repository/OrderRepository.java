package repository;

import entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 
public interface OrderRepository extends JpaRepository<User, Long> {
  
}

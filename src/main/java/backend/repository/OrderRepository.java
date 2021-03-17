package backend.repository;

import backend.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 
public interface OrderRepository extends JpaRepository<Order, Long> {
  
}

package backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.entity.Order;
 
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderByOrderedAtDesc();
}

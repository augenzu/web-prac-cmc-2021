package backend.repository;

import backend.entity.Order;
import backend.entity.Status;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long id);

    List<Order> findByStatus(Status status);

    List<Order> findAllByOrderByTime();
}

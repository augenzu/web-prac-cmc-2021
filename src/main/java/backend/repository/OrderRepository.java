package backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import backend.entity.Good;
import backend.entity.Order;
 
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderedAtBetweenOrderByOrderedAtDesc(LocalDateTime beg, LocalDateTime end);

    List<Order> findAllByOrderByOrderedAtDesc();

    @Query("select ord.goods from Order ord where ord.id = :id")
    List<Good> findOrderGoodsById(@Param("id") Long id);
}

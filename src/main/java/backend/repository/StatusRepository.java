package backend.repository;

import backend.entity.Order;
import backend.entity.Status;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByName(String name);

    List<Status> findAllByOrderByName();

    @Query("select st.orders from Status st where st.name = :name")
    List<Order> findOrdersByStatusName(@Param("name") String statusName);
}

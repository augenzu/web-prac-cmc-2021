package backend.repository;

import backend.entity.Status;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findById(Long id);

    List<Status> findAllByOrderByName();
}

package backend.repository;

import backend.entity.AppType;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface AppTypeRepository extends JpaRepository<AppType, Long> {
    Optional<AppType> findById(Long id);

    List<AppType> findAllByOrderByName();

    List<AppType> findAll();
}

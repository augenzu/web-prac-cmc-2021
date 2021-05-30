package backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.entity.AppType;
 
public interface AppTypeRepository extends JpaRepository<AppType, Long> {
    Optional<AppType> findByName(String name);

    List<AppType> findAllByOrderByName();
}

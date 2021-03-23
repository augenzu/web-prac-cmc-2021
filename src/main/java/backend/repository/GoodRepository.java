package backend.repository;

import backend.entity.AppType;
import backend.entity.Good;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface GoodRepository extends JpaRepository<Good, Long> {
    Optional<Good> findById(Long id);

    List<Good> findByAppType(AppType appType);
    
    List<Good> findByCompany(String company);

    List<Good> findByDescriptionContaining(String descriptionPart);

    List<Good> findAllByOrderByName();
}

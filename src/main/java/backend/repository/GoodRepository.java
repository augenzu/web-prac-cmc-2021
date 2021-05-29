package backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.entity.Good;
 
public interface GoodRepository extends JpaRepository<Good, Long> {
    List<Good> findByNameContainingIgnoreCase(String part);

    List<Good> findByCompanyContainingIgnoreCase(String part);

    List<Good> findByAssemblyPlaceContainingIgnoreCase(String part);

    List<Good> findByDescriptionContainingIgnoreCase(String part);

    List<Good> findByPriceBetween(Double low, double high);
}

package backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import backend.entity.Good;
 
public interface GoodRepository extends JpaRepository<Good, Long> {
    List<Good> findAllByOrderByName();

    List<Good> findByNameContainingIgnoreCase(String part);

    List<Good> findByCompanyContainingIgnoreCase(String part);

    List<Good> findByAssemblyPlaceContainingIgnoreCase(String part);

    List<Good> findByDescriptionContainingIgnoreCase(String part);

    List<Good> findByPriceBetween(Double low, double high);

    @Query("select count(*) > 0 from OrderGood entry where entry.good = :param")
    Boolean hasOrderEntries(@Param("param") Good param);
}

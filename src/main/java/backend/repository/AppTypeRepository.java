package backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import backend.entity.AppType;
import backend.entity.Good;
 
public interface AppTypeRepository extends JpaRepository<AppType, Long> {
    List<AppType> findAllByOrderByName();

    @Query("select at.goods from AppType at where at.name = :name")
    List<Good> findGoodsByAppTypeName(@Param("name") String appTypeName);
}

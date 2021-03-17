package backend.repository;

import backend.entity.Good;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 
public interface GoodRepository extends JpaRepository<Good, Long> {
    List<Good> findAll();

    List<Good> findAllById(Long id);
}

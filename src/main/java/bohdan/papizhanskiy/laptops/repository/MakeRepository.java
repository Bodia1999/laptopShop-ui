package bohdan.papizhanskiy.laptops.repository;

import bohdan.papizhanskiy.laptops.entity.Laptop;
import bohdan.papizhanskiy.laptops.entity.Make;
import javafx.scene.control.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MakeRepository extends JpaRepository<Make, Long> {

//    Page<Make> findAllByNameLike(String name, Pageable pageable);
    //List<Make> findAllByNameLike(String name);
}

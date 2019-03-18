package bohdan.papizhanskiy.laptops.repository;

import bohdan.papizhanskiy.laptops.entity.Make;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakeRepository extends JpaRepository<Make, Long> {

//    Page<Make> findAllByNameLike(String name, Pageable pageable);
    //List<Make> findAllByNameLike(String name);
}

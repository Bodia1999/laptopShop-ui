package bohdan.papizhanskiy.laptops.repository;

import bohdan.papizhanskiy.laptops.entity.Laptop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long>, JpaSpecificationExecutor {

    Page<Laptop> findById(Long id, Pageable pageable);
    List<Laptop> findAllByMake(String make);
}

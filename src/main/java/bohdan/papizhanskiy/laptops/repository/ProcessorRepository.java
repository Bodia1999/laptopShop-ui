package bohdan.papizhanskiy.laptops.repository;


import bohdan.papizhanskiy.laptops.entity.Laptop;
import bohdan.papizhanskiy.laptops.entity.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessorRepository extends JpaRepository<Processor, Long>, JpaSpecificationExecutor<Processor> {
    List<Laptop> findAllByNameLike(String name);
    List<Laptop> findAllByModelLike(String model);
}

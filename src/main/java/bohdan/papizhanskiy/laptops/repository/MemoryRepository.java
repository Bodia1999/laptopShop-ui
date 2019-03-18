package bohdan.papizhanskiy.laptops.repository;

import bohdan.papizhanskiy.laptops.entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryRepository extends JpaRepository<Memory,Long>, JpaSpecificationExecutor<Memory> {
}

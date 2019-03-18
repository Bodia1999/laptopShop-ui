package bohdan.papizhanskiy.laptops.repository;

import bohdan.papizhanskiy.laptops.entity.ProductForOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductForOrderRepository extends JpaRepository<ProductForOrder,Long> {
}

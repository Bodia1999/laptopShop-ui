package bohdan.papizhanskiy.laptops.repository;

import bohdan.papizhanskiy.laptops.entity.ShippingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, Long> {
}

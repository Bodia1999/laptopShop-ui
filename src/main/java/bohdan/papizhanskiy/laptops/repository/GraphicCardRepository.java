package bohdan.papizhanskiy.laptops.repository;

import bohdan.papizhanskiy.laptops.entity.GraphicCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphicCardRepository extends JpaRepository<GraphicCard,Long>, JpaSpecificationExecutor<GraphicCard> {
}

package bohdan.papizhanskiy.laptops.specification;

import bohdan.papizhanskiy.laptops.dto.request.MemoryFilterRequest;
import bohdan.papizhanskiy.laptops.entity.Memory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class MemorySpecification implements Specification<Memory> {

    private MemoryFilterRequest memoryFilterRequest;

    public MemorySpecification(MemoryFilterRequest memoryFilterRequest) {
        this.memoryFilterRequest = memoryFilterRequest;
    }

    @Override
    public Predicate toPredicate(Root<Memory> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate byName = findByName(root, criteriaBuilder);
        if (byName != null) predicates.add(byName);
        Predicate byVolume = findByVolume(root, criteriaBuilder);
        if (byVolume != null) predicates.add(byVolume);
        Predicate byTypeOfMemory = findByTypeOfMemory(root, criteriaBuilder);
        if (byVolume != null) predicates.add(byTypeOfMemory);
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate findByName(Root<Memory> root, CriteriaBuilder criteriaBuilder) {
        String memoryName = memoryFilterRequest.getName();
        if (memoryName == null || memoryName.trim().isEmpty()) {
            return null;
        }
        return criteriaBuilder.like(root.get("name"), '%' + memoryName + '%');
    }

    private Predicate findByVolume(Root<Memory> root, CriteriaBuilder criteriaBuilder) {
        Integer volumeOfMemoryFrom = memoryFilterRequest.getVolumeOfMemoryFrom();
        Integer volumeOfMemoryTo = memoryFilterRequest.getVolumeOfMemoryTo();
        if (volumeOfMemoryFrom == null && volumeOfMemoryTo == null) {
            return null;
        }

        if (volumeOfMemoryFrom == null) {
            memoryFilterRequest.setVolumeOfMemoryFrom(0);
        }
        if (volumeOfMemoryTo == null) {
            memoryFilterRequest.setVolumeOfMemoryTo(Integer.MAX_VALUE);
        }

        return criteriaBuilder.between(root.get("volumeOfMemory"), volumeOfMemoryFrom, volumeOfMemoryTo);
    }

    private Predicate findByTypeOfMemory(Root<Memory> root, CriteriaBuilder criteriaBuilder) {
        String typeOfMemory = memoryFilterRequest.getTypeOfMemory();
        if (typeOfMemory == null || typeOfMemory.trim().isEmpty()) {
            return null;
        }
        return criteriaBuilder.like(root.get("typeOfMemory"), typeOfMemory);
    }


}

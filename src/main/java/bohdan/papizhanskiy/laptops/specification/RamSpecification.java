package bohdan.papizhanskiy.laptops.specification;

import bohdan.papizhanskiy.laptops.dto.request.RamFilterRequest;
import bohdan.papizhanskiy.laptops.entity.Ram;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RamSpecification implements Specification<Ram> {
    private RamFilterRequest ramFilterRequest;

    public RamSpecification(RamFilterRequest ramFilterRequest) {
        this.ramFilterRequest = ramFilterRequest;
    }

    @Override
    public Predicate toPredicate(Root<Ram> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate byName = findByName(root, criteriaBuilder);
        if (byName != null) predicates.add(byName);
        Predicate byVolumeOfMemory = findByVolumeOfMemory(root, criteriaBuilder);
        if (byVolumeOfMemory != null) predicates.add(byVolumeOfMemory);
        Predicate byWorkingFrequency = findByWorkingFrequency(root, criteriaBuilder);
        if (byWorkingFrequency != null) predicates.add(byWorkingFrequency);

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate findByName(Root<Ram> root, CriteriaBuilder criteriaBuilder) {
        String name = ramFilterRequest.getName();
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        return criteriaBuilder.like(root.get("name"), name);
    }

    private Predicate findByVolumeOfMemory(Root<Ram> root, CriteriaBuilder criteriaBuilder) {
        Integer volumeOfMemoryFrom = ramFilterRequest.getVolumeOfMemoryFrom();
        Integer volumeOfMemoryTo = ramFilterRequest.getVolumeOfMemoryTo();
        if (volumeOfMemoryFrom == null && volumeOfMemoryTo == null) {
            return null;
        }
        if (volumeOfMemoryFrom == null) {
            ramFilterRequest.setVolumeOfMemoryFrom(0);
        }
        if (volumeOfMemoryTo == null) {
            ramFilterRequest.setVolumeOfMemoryTo(Integer.MAX_VALUE);
        }
        return criteriaBuilder.between(root.get("volumeOfMemory"), volumeOfMemoryFrom, volumeOfMemoryTo);
    }

    private Predicate findByWorkingFrequency(Root<Ram> root, CriteriaBuilder criteriaBuilder) {
        Integer workingFrequencyFrom = ramFilterRequest.getWorkingFrequencyFrom();
        Integer workingFrequencyTo = ramFilterRequest.getWorkingFrequencyTo();
        if (workingFrequencyFrom == null && workingFrequencyTo == null) {
            return null;
        }
        if (workingFrequencyFrom == null) {
            ramFilterRequest.setWorkingFrequencyFrom(0);
        }
        if (workingFrequencyTo == null) {
            ramFilterRequest.setWorkingFrequencyTo(Integer.MAX_VALUE);
        }
        return criteriaBuilder.between(root.get("workingFrequency"), workingFrequencyFrom, workingFrequencyTo);
    }
}

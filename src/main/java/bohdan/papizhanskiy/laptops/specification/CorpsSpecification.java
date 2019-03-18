package bohdan.papizhanskiy.laptops.specification;

import bohdan.papizhanskiy.laptops.dto.request.CorpsFilterRequest;
import bohdan.papizhanskiy.laptops.entity.Corps;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CorpsSpecification implements Specification<Corps> {

    private CorpsFilterRequest corpsFilterRequest;

    public CorpsSpecification(CorpsFilterRequest corpsFilterRequest) {
        this.corpsFilterRequest = corpsFilterRequest;
    }

    @Override
    public Predicate toPredicate(Root<Corps> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate byWeight = findByWeight(root, criteriaBuilder);
        if (byWeight != null) predicates.add(byWeight);
        Predicate byColorOfCorps = findByColorOfCorps(root, criteriaBuilder);
        if (byColorOfCorps != null) predicates.add(byColorOfCorps);
        Predicate byMaterialOfCorps = findByMaterialOfCorps(root, criteriaBuilder);
        if (byMaterialOfCorps != null) predicates.add(byMaterialOfCorps);

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate findByWeight(Root<Corps> root, CriteriaBuilder criteriaBuilder) {
        Double weightFrom = corpsFilterRequest.getWeightFrom();
        Double weightTo = corpsFilterRequest.getWeightTo();
        if (weightFrom == null && weightTo == null) {
            return null;
        }
        if (weightFrom == null) {
            corpsFilterRequest.setWeightFrom(0.0);
        }
        if (weightTo == null) {
            corpsFilterRequest.setWeightTo(Double.MAX_VALUE);
        }

        return criteriaBuilder.between(root.get("weight"), weightFrom, weightTo);
    }

    private Predicate findByColorOfCorps(Root<Corps> root, CriteriaBuilder criteriaBuilder) {
        String colorOfCorps = corpsFilterRequest.getColorOfCorps();
        if (colorOfCorps == null || colorOfCorps.trim().isEmpty()) {
            return null;
        }
        return criteriaBuilder.like(root.get("colorOfCorps"), colorOfCorps);
    }

    private Predicate findByMaterialOfCorps(Root<Corps> root, CriteriaBuilder criteriaBuilder) {
        String materialOfCorps = corpsFilterRequest.getMaterialOfCorps();
        if (materialOfCorps == null || materialOfCorps.trim().isEmpty()) {
            return null;
        }

        return criteriaBuilder.like(root.get("materialOfCorps"), materialOfCorps);
    }
}

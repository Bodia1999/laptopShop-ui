package bohdan.papizhanskiy.laptops.specification;

import bohdan.papizhanskiy.laptops.dto.request.ScreenFilterRequest;
import bohdan.papizhanskiy.laptops.entity.Screen;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ScreenSpecification implements Specification<Screen> {
    private ScreenFilterRequest screenFilterRequest;

    public ScreenSpecification(ScreenFilterRequest screenFilterRequest) {
        this.screenFilterRequest = screenFilterRequest;
    }

    @Override
    public Predicate toPredicate(Root<Screen> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate byResolution = findByResolution(root, criteriaBuilder);
        if (byResolution != null) predicates.add(byResolution);
        Predicate bySize = findBySize(root, criteriaBuilder);
        if (bySize != null) predicates.add(bySize);
        Predicate byTypeOfScreen = findByTypeOfScreen(root, criteriaBuilder);
        if (byTypeOfScreen != null) predicates.add(byTypeOfScreen);
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate findByTypeOfScreen(Root<Screen> root, CriteriaBuilder criteriaBuilder) {
        String type = screenFilterRequest.getType();
        if (type == null || type.trim().isEmpty()) {
            return null;
        }

        return criteriaBuilder.like(root.get("type"), type);
    }

    private Predicate findBySize(Root<Screen> root, CriteriaBuilder criteriaBuilder) {
        Integer sizeFrom = screenFilterRequest.getSizeFrom();
        Integer sizeTo = screenFilterRequest.getSizeTo();
        if (sizeFrom == null && sizeTo == null) {
            return null;
        }
        if (sizeFrom == null) {
            screenFilterRequest.setSizeFrom(0);
        }

        if (sizeTo == null) {
            screenFilterRequest.setSizeTo(Integer.MAX_VALUE);
        }

        return criteriaBuilder.between(root.get("size"), sizeFrom, sizeTo);
    }


    private Predicate findByResolution(Root<Screen> root, CriteriaBuilder criteriaBuilder) {
        String resolution = screenFilterRequest.getResolution();

        if (resolution == null || resolution.trim().isEmpty()) {
            return null;
        }

        return criteriaBuilder.like(root.get("size"), resolution);
    }

}

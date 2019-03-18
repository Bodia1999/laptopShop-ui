package bohdan.papizhanskiy.laptops.specification;

import bohdan.papizhanskiy.laptops.dto.request.ProcessorFilterRequest;
import bohdan.papizhanskiy.laptops.entity.Processor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProcessorSpecification implements Specification<Processor> {

    private ProcessorFilterRequest processorFilterRequest;

    public ProcessorSpecification(ProcessorFilterRequest processorFilterRequest) {
        this.processorFilterRequest = processorFilterRequest;
    }

    @Override
    public Predicate toPredicate(Root<Processor> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate byName = findByName(root, criteriaBuilder);
        if (byName != null) predicates.add(byName);
        Predicate byWorkingFrequency = findByWorkingFrequency(root, criteriaBuilder);
        if (byWorkingFrequency != null) predicates.add(byWorkingFrequency);
        Predicate byQuantityOfCores = findByQuantityOfCores(root, criteriaBuilder);
        if (byQuantityOfCores != null) predicates.add(byQuantityOfCores);
        Predicate byModel = findByModel(root, criteriaBuilder);
        if (byModel != null) predicates.add(byModel);

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate findByName(Root<Processor> root, CriteriaBuilder criteriaBuilder) {
        String processor = processorFilterRequest.getName();
        if (processor == null || processor.trim().isEmpty()) {
            return null;
        }
        return criteriaBuilder.like(root.get("name"), processor);
    }

    private Predicate findByModel(Root<Processor> root, CriteriaBuilder criteriaBuilder) {
        String model = processorFilterRequest.getModel();
        if (model == null || model.trim().isEmpty()) {
            return null;
        }
        return criteriaBuilder.like(root.get("model"), model);
    }

    private Predicate findByWorkingFrequency(Root<Processor> root, CriteriaBuilder criteriaBuilder) {
        Integer workingFrequencyFrom = processorFilterRequest.getWorkingFrequencyFrom();
        Integer workingFrequencyTo = processorFilterRequest.getWorkingFrequencyTo();
        if (workingFrequencyFrom == null && workingFrequencyTo == null) {
            return null;
        }

        if (workingFrequencyFrom == null) {
            processorFilterRequest.setWorkingFrequencyFrom(0);
        }
        if (workingFrequencyTo == null) {
            processorFilterRequest.setWorkingFrequencyTo(Integer.MAX_VALUE);
        }

        return criteriaBuilder.between(root.get("workingFrequency"), workingFrequencyFrom, workingFrequencyTo);
    }

    private Predicate findByQuantityOfCores(Root<Processor> root, CriteriaBuilder criteriaBuilder) {
        Integer quantityOfCoresFrom = processorFilterRequest.getQuantityOfCoresFrom();
        Integer quantityOfCoresTo = processorFilterRequest.getQuantityOfCoresTo();
        if (quantityOfCoresFrom == null && quantityOfCoresTo == null) {
            return null;
        }

        if (quantityOfCoresFrom == null) {
            processorFilterRequest.setWorkingFrequencyFrom(0);
        }
        if (quantityOfCoresTo == null) {
            processorFilterRequest.setWorkingFrequencyTo(Integer.MAX_VALUE);
        }

        return criteriaBuilder.between(root.get("quantityOfCores"), quantityOfCoresFrom, quantityOfCoresTo);
    }
}

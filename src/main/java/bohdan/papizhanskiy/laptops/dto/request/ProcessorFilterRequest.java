package bohdan.papizhanskiy.laptops.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessorFilterRequest {

    private String name;

    private String model;

    private Integer workingFrequencyFrom;
    private Integer workingFrequencyTo;

    private Integer quantityOfCoresFrom;
    private Integer quantityOfCoresTo;

    private PaginationRequest pagination;
}

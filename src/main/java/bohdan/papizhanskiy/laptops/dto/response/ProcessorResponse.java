package bohdan.papizhanskiy.laptops.dto.response;

import bohdan.papizhanskiy.laptops.entity.Processor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ProcessorResponse {

    private  Long id;
    private String name;
    private String model;
    private Integer workingFrequency;
    private Integer quantityOfCores;

    public ProcessorResponse (Processor processor){
        id = processor.getId();
        name = processor.getName();
        model = processor.getModel();
        workingFrequency = processor.getWorkingFrequency();
        quantityOfCores = processor.getQuantityOfCores();
    }
}

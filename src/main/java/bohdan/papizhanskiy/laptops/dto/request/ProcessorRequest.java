package bohdan.papizhanskiy.laptops.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ProcessorRequest {

//    @NotNull
//    @NotEmpty
    private String name;

//    @NotNull
//    @NotEmpty
    private String model;

//    @NotNull
//    @NotEmpty
//    @Min(1)
    private Integer workingFrequency;

//    @NotNull
//    @NotEmpty
//    @Min(1)
    private Integer quantityOfCores;
}
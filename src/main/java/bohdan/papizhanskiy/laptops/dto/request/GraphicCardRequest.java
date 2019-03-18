package bohdan.papizhanskiy.laptops.dto.request;

import bohdan.papizhanskiy.laptops.entity.Laptop;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class GraphicCardRequest {

//    @NotNull
//    @NotEmpty
    private String name;

//    @NotNull
//    @NotEmpty
    private String model;

//    @NotNull
//    @NotEmpty
    private String typeOfGraphicCard;

//    @NotNull
//    @NotEmpty
//    @Min(1)
    private Integer volumeOfMemory;

//    @NotNull
//    @NotEmpty
    private String typeOfMemory;


}

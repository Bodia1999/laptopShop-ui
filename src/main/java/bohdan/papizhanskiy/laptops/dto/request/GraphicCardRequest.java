package bohdan.papizhanskiy.laptops.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

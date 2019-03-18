package bohdan.papizhanskiy.laptops.dto.request;

import bohdan.papizhanskiy.laptops.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LaptopRequest {
//    @NotNull
//    @NotEmpty
    private String model;

//    @NotNull
//    @NotEmpty
    private Double price;

    @Type(type = "text")
    private String description;

    private String imageDirection;
    private String descriptionImagePath1;
    private String descriptionImagePath2;
    private String descriptionImagePath3;
    //@NotNull
    private Boolean availabilityOfWIFI;

//    @NotNull
//    @NotEmpty
//    @Min(1)
    private Long makeId;

//    @NotNull
//    @NotEmpty
//    @Min(1)
    private Long graphicCardId;

//    @NotNull
//    @NotEmpty
//    @Min(1)
    private Long ramId;

//    @NotNull
//    @NotEmpty
//    @Min(1)
    private Long corpsId;

//    @NotNull
//    @NotEmpty
//    @Min(1)
    private Long memoryId;

//    @NotNull
//    @NotEmpty
//    @Min(1)
    private Long processorId;

//    @NotNull
//    @NotEmpty
//    @Min(1)
    private Long screenId;

    //@NotNull
    private Boolean availabilityOfBluetooth;

    //@NotNull
    private Boolean availabilityOfUSBTypeC;

   // @NotNull
    private Boolean availabilityOfUSBSecondGeneration;

   // @NotNull
    private Boolean availabilityOfUSBThirdGeneration;
//
   // @NotNull
    private Boolean availabilityOfHDMI;

   // @NotNull
    private Boolean availabilityOfLAN;

    //@NotNull
    private Boolean availabilityOfAUX;



//    private Screen screen;
//
//    private Ram ram;
//
//    private Processor processor;
//
//    private Memory memory;
//
//    private GraphicCard graphicCard;
//
//    private Corps corps;

}

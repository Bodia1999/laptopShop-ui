package bohdan.papizhanskiy.laptops.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Make make;

    private String model;

    private Double price;

    @Type(type = "text")
    private String description;

    private String mainImagePath;

    private String descriptionImagePath1;
    private String descriptionImagePath2;
    private String descriptionImagePath3;

    @ManyToOne
    private Screen screen;

    @ManyToOne//(mappedBy = "laptop")
    private Ram ram;

    @ManyToOne//(mappedBy = "laptop")
    private Processor processor;

    @ManyToOne//(mappedBy = "laptop")
    private Memory memory;

    @ManyToOne
    private GraphicCard graphicCard;

    @ManyToOne
    private Corps corps;


//    @ManyToMany(mappedBy = "laptops")
//    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "laptop")
    private List<ProductForOrder> productForOrder = new ArrayList<>();

    private Boolean availabilityOfWIFI;

    private Boolean availabilityOfBluetooth;

    private Boolean availabilityOfUSBTypeC;

    private Boolean availabilityOfUSBSecondGeneration;

    private Boolean availabilityOfUSBThirdGeneration;

    private Boolean availabilityOfHDMI;

    private Boolean availabilityOfLAN;

    private Boolean availabilityOfAUX;


}

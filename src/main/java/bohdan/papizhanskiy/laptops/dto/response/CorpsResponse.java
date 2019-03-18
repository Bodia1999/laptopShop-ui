package bohdan.papizhanskiy.laptops.dto.response;

import bohdan.papizhanskiy.laptops.entity.Corps;
import bohdan.papizhanskiy.laptops.entity.Laptop;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CorpsResponse {

    private Long id;

    private Double weight;

    private String dimensions;

    private String colorOfCorps;

    private String materialOfCorps;

//    private Laptop laptop;

    public CorpsResponse(Corps corps){
        id = corps.getId();
        weight = corps.getWeight();
        dimensions = corps.getDimensions();
        colorOfCorps = corps.getColorOfCorps();
        materialOfCorps = corps.getMaterialOfCorps();
//        laptop = getLaptop();
    }
}

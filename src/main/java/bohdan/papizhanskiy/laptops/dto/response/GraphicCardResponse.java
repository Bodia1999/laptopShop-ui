package bohdan.papizhanskiy.laptops.dto.response;

import bohdan.papizhanskiy.laptops.entity.GraphicCard;
import bohdan.papizhanskiy.laptops.entity.Laptop;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GraphicCardResponse {

    private Long id;

    private String name;

    private String model;

    private String typeOfGraphicCard;

    private Integer volumeOfMemory;

    private String typeOfMemory;



    public GraphicCardResponse(GraphicCard graphicCard){
        id = graphicCard.getId();
        name = graphicCard.getName();
        model = graphicCard.getModel();
        typeOfGraphicCard = graphicCard.getTypeOfGraphicCard();
        volumeOfMemory = graphicCard.getVolumeOfMemory();
        typeOfMemory = graphicCard.getTypeOfMemory();

    }

}

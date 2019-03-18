package bohdan.papizhanskiy.laptops.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GraphicCardFilterRequest {

    private String name;

    private String model;

    private Integer volumeOfMemoryFrom;
    private Integer volumeOfMemoryTo;

    private PaginationRequest pagination;
}

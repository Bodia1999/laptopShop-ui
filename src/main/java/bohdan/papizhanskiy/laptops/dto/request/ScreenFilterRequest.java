package bohdan.papizhanskiy.laptops.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScreenFilterRequest {

    private String type;

    private String resolution;

    private Integer sizeFrom;
    private Integer sizeTo;

    private  PaginationRequest pagination;
}

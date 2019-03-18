package bohdan.papizhanskiy.laptops.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorpsFilterRequest {

    private Double weightFrom;
    private Double weightTo;

    private String colorOfCorps;

    private String materialOfCorps;

    private PaginationRequest pagination;
}

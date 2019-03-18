package bohdan.papizhanskiy.laptops.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoryFilterRequest {

    private String name;
    private String typeOfMemory;
    private Integer volumeOfMemoryFrom;
    private Integer volumeOfMemoryTo;

    private PaginationRequest pagination;
}

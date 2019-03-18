package bohdan.papizhanskiy.laptops.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RamFilterRequest {

    private String name;

    private String typeOfMemory;

    private Integer volumeOfMemoryFrom;
    private Integer volumeOfMemoryTo;

    private Integer workingFrequencyFrom;
    private Integer workingFrequencyTo;

    private PaginationRequest pagination;
}

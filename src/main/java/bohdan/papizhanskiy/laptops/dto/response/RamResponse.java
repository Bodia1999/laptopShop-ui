package bohdan.papizhanskiy.laptops.dto.response;

import bohdan.papizhanskiy.laptops.entity.Ram;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RamResponse {

    private Long id;

    private String name;

    private String typeOfMemory;

    private Integer volumeOfMemory;

    private Integer workingFrequency;

    public RamResponse(Ram ram){
        id = ram.getId();
        name = ram.getName();
        typeOfMemory = ram.getTypeOfMemory();
        volumeOfMemory = ram.getVolumeOfMemory();
        workingFrequency = ram.getWorkingFrequency();
    }
}

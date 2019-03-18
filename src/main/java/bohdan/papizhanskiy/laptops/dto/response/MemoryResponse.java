package bohdan.papizhanskiy.laptops.dto.response;

import bohdan.papizhanskiy.laptops.entity.Memory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class MemoryResponse {

    private Long id;
    private String name;
    private String typeOfMemory;
    private Integer volumeOfMemory;

    public MemoryResponse (Memory memory){
        id = memory.getId();
        name = memory.getName();
        typeOfMemory = memory.getTypeOfMemory();
        volumeOfMemory = memory.getVolumeOfMemory();

    }
}

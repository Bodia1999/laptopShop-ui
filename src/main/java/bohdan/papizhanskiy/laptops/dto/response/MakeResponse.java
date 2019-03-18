package bohdan.papizhanskiy.laptops.dto.response;

import bohdan.papizhanskiy.laptops.entity.Make;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class MakeResponse {

    private Long id;
    private String name;
    private List<LaptopResponse> laptop = new ArrayList<>();

    public MakeResponse (Make make){
        id = make.getId();
        name = make.getName();
        laptop = make.getLaptop().stream().map(LaptopResponse::new).collect(Collectors.toList());

        }


    }


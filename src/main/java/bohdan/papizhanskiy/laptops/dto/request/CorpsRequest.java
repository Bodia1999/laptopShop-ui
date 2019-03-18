package bohdan.papizhanskiy.laptops.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CorpsRequest {

//    @NotNull
//    @NotEmpty
    private Double weight;

//    @NotNull
//    @NotEmpty
    private String dimensions;

//    @NotNull
//    @NotEmpty
    private String colorOfCorps;

//    @NotNull
//    @NotEmpty
    private String materialOfCorps;
}

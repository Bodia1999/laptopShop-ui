package bohdan.papizhanskiy.laptops.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ScreenRequest {
//    @NotNull
//    @NotEmpty
    private String type;

//    @NotNull
//    @NotEmpty
    private String resolution;

//    @NotNull
//    @NotEmpty
    private String size;
}

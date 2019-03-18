package bohdan.papizhanskiy.laptops.dto.response;

import bohdan.papizhanskiy.laptops.entity.Screen;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScreenResponse {
    private Long id;

    private String type;

    private String resolution;

    private String size;

    public ScreenResponse(Screen screen){
        id = screen.getId();
        type = screen.getType();
        resolution = screen.getResolution();
        size = screen.getSize();
    }
}

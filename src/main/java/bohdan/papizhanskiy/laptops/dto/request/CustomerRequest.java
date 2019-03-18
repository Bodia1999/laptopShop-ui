package bohdan.papizhanskiy.laptops.dto.request;

import bohdan.papizhanskiy.laptops.dto.response.OrderResponse;
import bohdan.papizhanskiy.laptops.entity.Order;
import bohdan.papizhanskiy.laptops.entity.ShippingInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomerRequest {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String surname;

    @NotNull
    @NotEmpty
    @Min(14)
    private Integer age;

    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Min(6)
    @Max(16)
    private String password;

    @NotNull
    @NotEmpty
    @Min(1)


    private List<OrderResponse> orders = new ArrayList<>();

    private List<ShippingInfo> shippingInfos = new ArrayList<>();
}

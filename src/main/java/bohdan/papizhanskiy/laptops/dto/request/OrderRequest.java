package bohdan.papizhanskiy.laptops.dto.request;

import bohdan.papizhanskiy.laptops.entity.ProductForOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotNull
    @NotEmpty
    @Min(1)
    private Long customerId;
    private List<ProductForOrder> productForOrder = new ArrayList<>();
}

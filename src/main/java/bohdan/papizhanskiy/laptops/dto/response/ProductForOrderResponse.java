package bohdan.papizhanskiy.laptops.dto.response;

import bohdan.papizhanskiy.laptops.entity.ProductForOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductForOrderResponse {
    private Long id;

    private Integer count;

    public ProductForOrderResponse(ProductForOrder productForOrder){
        id = productForOrder.getId();
        count = productForOrder.getCount();
    }
}

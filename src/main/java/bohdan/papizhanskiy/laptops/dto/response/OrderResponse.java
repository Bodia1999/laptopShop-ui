package bohdan.papizhanskiy.laptops.dto.response;

import bohdan.papizhanskiy.laptops.entity.Customer;
import bohdan.papizhanskiy.laptops.entity.Order;
import bohdan.papizhanskiy.laptops.entity.ProductForOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;

    private Customer customer;

    private List<ProductForOrderResponse> productForOrder = new ArrayList<>();

    public OrderResponse(Order order){
        id = order.getId();
        customer = order.getCustomer();
        productForOrder = order.getProductForOrder().stream().map(ProductForOrderResponse::new).collect(Collectors.toList());
    }
}

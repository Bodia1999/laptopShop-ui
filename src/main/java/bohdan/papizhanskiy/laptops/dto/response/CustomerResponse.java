package bohdan.papizhanskiy.laptops.dto.response;

import bohdan.papizhanskiy.laptops.entity.Customer;
import bohdan.papizhanskiy.laptops.entity.Order;
import bohdan.papizhanskiy.laptops.entity.ShippingInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CustomerResponse {

    private Long id;

    private String name;

    private String surname;

    private Integer age;

    private String email;

    private String password;

//    private List<OrderResponse> orders = new ArrayList<>();

//    private List<ShippingInfoResponse> shippingInfos = new ArrayList<>();

    public CustomerResponse (Customer customer){
        id = customer.getId();
        name = customer.getName();
        age = customer.getAge();
        email = customer.getEmail();
        password = customer.getPassword();
//        orders = customer.getOrders().stream().map(OrderResponse::new).collect(Collectors.toList());
//        shippingInfos = customer.getShippingInfos().stream().map(ShippingInfoResponse::new).collect(Collectors.toList());
    }
}

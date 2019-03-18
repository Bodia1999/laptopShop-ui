package bohdan.papizhanskiy.laptops.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    @Column(unique = true)
    private String surname;

    private Integer age;
    @Column(unique = true)
    private String email;
@ToString.Exclude
    private String password;
    @ToString.Exclude
    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();
    @ToString.Exclude
    @OneToMany(mappedBy = "customer")
    private List<ShippingInfo> shippingInfos = new ArrayList<>();

}

package bohdan.papizhanskiy.laptops.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ShippingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contactName;

    private String phoneNumber;

    private String address;

    private String country;

    private String region;//штат/область/провінція

    private String city;

    private String postalCode;

    @ManyToOne
    private Customer customer;





}

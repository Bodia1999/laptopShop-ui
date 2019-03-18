package bohdan.papizhanskiy.laptops.dto.response;

import bohdan.papizhanskiy.laptops.entity.Customer;
import bohdan.papizhanskiy.laptops.entity.Order;
import bohdan.papizhanskiy.laptops.entity.ShippingInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShippingInfoResponse {

    private Long id;

    private String contactName;

    private String phoneNumber;

    private String address;

    private String country;

    private String region;//штат/область/провінція

    private String city;

    private String postalCode;

    private Customer customer;

    public ShippingInfoResponse (ShippingInfo shippingInfo){
        id = shippingInfo.getId();
        contactName = shippingInfo.getContactName();
        phoneNumber = shippingInfo.getPhoneNumber();
        address = shippingInfo.getAddress();
        country = shippingInfo.getCountry();
        region = shippingInfo.getRegion();
        city = shippingInfo.getCity();
        postalCode = shippingInfo.getPostalCode();
        customer = shippingInfo.getCustomer();
    }
}

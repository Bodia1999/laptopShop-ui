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
public class ShippingInfoRequest {
    @NotNull
    @NotEmpty
    private String contactName;

    @NotNull
    @NotEmpty
    private String phoneNumber;

    @NotNull
    @NotEmpty
    private String address;

    @NotNull
    @NotEmpty
    private String country;

    @NotNull
    private String region;//штат/область/провінція

    @NotNull
    private String city;

    @NotNull
    @NotEmpty
    private String postalCode;

    @NotNull
    @NotEmpty
    @Min(0)
    private Long customerId;

}

package bohdan.papizhanskiy.laptops.service;

import bohdan.papizhanskiy.laptops.dto.request.PaginationRequest;
import bohdan.papizhanskiy.laptops.dto.request.ShippingInfoRequest;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.dto.response.MakeResponse;
import bohdan.papizhanskiy.laptops.dto.response.ShippingInfoResponse;
import bohdan.papizhanskiy.laptops.entity.Make;
import bohdan.papizhanskiy.laptops.entity.ShippingInfo;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.repository.ShippingInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShippingInfoService {

    @Autowired
    private ShippingInfoRepository shippingInfoRepository;

    @Autowired
    private CustomerService customerService;

    public ShippingInfoResponse save(ShippingInfoRequest shippingInfoRequest) throws Exception {
        return new ShippingInfoResponse(shippingInfoRequestToShippingInfo(shippingInfoRequest, null));

    }

    public List<ShippingInfoResponse> findAll() {
        return shippingInfoRepository.findAll().stream().map(ShippingInfoResponse::new).collect(Collectors.toList());
    }

    public ShippingInfoResponse update(ShippingInfoRequest shippingInfoRequest, Long id) throws Exception {
        return new ShippingInfoResponse(shippingInfoRequestToShippingInfo(shippingInfoRequest, findOne(id)));
    }

    public void delete(Long id) throws WrongInputException {
        shippingInfoRepository.delete(findOne(id));
    }

    private ShippingInfo shippingInfoRequestToShippingInfo(ShippingInfoRequest shippingInfoRequest, ShippingInfo shippingInfo) throws Exception {
        if (shippingInfo == null) {
            shippingInfo = new ShippingInfo();
        }
        shippingInfo.setAddress(shippingInfoRequest.getAddress());
        shippingInfo.setCity(shippingInfoRequest.getCity());
        shippingInfo.setContactName(shippingInfoRequest.getContactName());
        shippingInfo.setCountry(shippingInfoRequest.getCountry());
        shippingInfo.setPhoneNumber(shippingInfoRequest.getPhoneNumber());
        shippingInfo.setRegion(shippingInfoRequest.getRegion());
        shippingInfo.setPostalCode(shippingInfoRequest.getPostalCode());
        shippingInfo.setCustomer(customerService.findOne(shippingInfoRequest.getCustomerId()));
        return shippingInfoRepository.save(shippingInfo);
    }

    public ShippingInfo findOne(Long id) throws WrongInputException {
        return shippingInfoRepository.findById(id)
                .orElseThrow(() -> new WrongInputException("ShippingInfo with id " + id + " not exists"));
    }

    public DataResponse<ShippingInfoResponse> findAll(PaginationRequest paginationRequest) {
        Page<ShippingInfo> all = shippingInfoRepository.findAll(paginationRequest.mapToPageRequest());
        return new DataResponse<>(all.get().map(ShippingInfoResponse::new).collect(Collectors.toList()), all.getTotalPages(), all.getTotalElements());
    }

}

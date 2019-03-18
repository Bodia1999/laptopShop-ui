package bohdan.papizhanskiy.laptops.controller;

import bohdan.papizhanskiy.laptops.dto.request.ShippingInfoRequest;
import bohdan.papizhanskiy.laptops.dto.response.ShippingInfoResponse;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.service.ShippingInfoService;
import org.hibernate.persister.walking.spi.WalkingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RequestMapping("/shippingInfo")
@RestController
public class ShippingInfoController {

    @Autowired
    private ShippingInfoService shippingInfoService;

    @GetMapping
    public List<ShippingInfoResponse> findAll(){
        return shippingInfoService.findAll();
    }

    @PostMapping
    public ShippingInfoResponse save (@RequestBody ShippingInfoRequest shippingInfoRequest) throws Exception {
        return shippingInfoService.save(shippingInfoRequest);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) throws WrongInputException{
        shippingInfoService.delete(id);
    }

    @PutMapping("/{id}")
    public ShippingInfoResponse update(@PathVariable Long id, @RequestBody ShippingInfoRequest shippingInfoRequest)throws Exception{
        return shippingInfoService.update(shippingInfoRequest, id);
    }

    @PostMapping("/findOne")
    public ShippingInfoResponse findOne(@RequestParam Long id) throws WrongInputException{
        return new ShippingInfoResponse(shippingInfoService.findOne(id));
    }



}

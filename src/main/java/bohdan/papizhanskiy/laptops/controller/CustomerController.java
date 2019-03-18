package bohdan.papizhanskiy.laptops.controller;

import bohdan.papizhanskiy.laptops.dto.request.CustomerRequest;
import bohdan.papizhanskiy.laptops.dto.request.PaginationRequest;
import bohdan.papizhanskiy.laptops.dto.response.CustomerResponse;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerResponse> findAll() {
        return customerService.findAll();
    }

    @PostMapping
    public CustomerResponse save(@RequestBody CustomerRequest customerRequest) throws WrongInputException {
        return customerService.save(customerRequest);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) throws WrongInputException {
        customerService.delete(id);
    }

    @PutMapping

    public CustomerResponse update(@RequestBody CustomerRequest customerRequest, @RequestParam Long id) throws WrongInputException {
        return customerService.update(customerRequest, id);
    }

    @PostMapping("/findOne")
    public CustomerResponse findOne(@RequestParam Long id) throws WrongInputException{
        return new CustomerResponse(customerService.findOne(id));
    }

//    @PostMapping("/page")
//    public DataResponse<CustomerResponse> findAll(@RequestBody PaginationRequest paginationRequest){
//        return customerService.findAll(paginationRequest);
//    }
}

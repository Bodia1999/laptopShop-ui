package bohdan.papizhanskiy.laptops.service;

import bohdan.papizhanskiy.laptops.dto.request.CustomerRequest;
import bohdan.papizhanskiy.laptops.dto.request.PaginationRequest;
import bohdan.papizhanskiy.laptops.dto.response.CustomerResponse;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.dto.response.MakeResponse;
import bohdan.papizhanskiy.laptops.entity.Customer;
import bohdan.papizhanskiy.laptops.entity.Make;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public CustomerResponse save(CustomerRequest customerRequest) throws WrongInputException {

        return new CustomerResponse(customerRequestToCustomer(null, customerRequest));

    }

    public CustomerResponse update(CustomerRequest customerRequest, Long id) throws WrongInputException {
        return new CustomerResponse(customerRequestToCustomer(findOne(id), customerRequest));
    }

    private Customer customerRequestToCustomer(Customer customer, CustomerRequest customerRequest) throws WrongInputException {
        if (customer == null) {
            customer = new Customer();
        }
        customer.setName(customerRequest.getName());
        customer.setSurname(customerRequest.getSurname());
        customer.setAge(customerRequest.getAge());
        customer.setEmail(customerRequest.getEmail());
        customer.setPassword(customerRequest.getPassword());


        return customerRepository.save(customer);
    }


    public List<CustomerResponse> findAll() {
        return customerRepository.findAll().stream().map(CustomerResponse::new).collect(Collectors.toList());
    }

    public Customer findOne(Long id) throws WrongInputException {
        return customerRepository.findById(id).orElseThrow(() -> new WrongInputException("Customer with " + id + " not exists"));
    }

    public void delete(Long id) throws WrongInputException {
        customerRepository.delete(findOne(id));
    }



}

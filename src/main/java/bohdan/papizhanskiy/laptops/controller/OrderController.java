package bohdan.papizhanskiy.laptops.controller;

import bohdan.papizhanskiy.laptops.dto.request.OrderRequest;
import bohdan.papizhanskiy.laptops.dto.response.OrderResponse;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping
    public OrderResponse save(@RequestBody OrderRequest orderRequest)throws WrongInputException {
        return orderService.save(orderRequest);
    }

    @GetMapping
    public List<OrderResponse> findAll() {
        return orderService.findAll();
    }

    @PutMapping("/{id}")
    public OrderResponse update(@PathVariable Long id, @RequestBody OrderRequest orderRequest) throws WrongInputException {
        return orderService.update(id, orderRequest);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) throws WrongInputException {
        orderService.delete(id);
    }

    @PostMapping("/findOne")
    public OrderResponse findOne(@RequestParam Long id) throws WrongInputException{
        return new OrderResponse(orderService.findOne(id));
    }
}



package bohdan.papizhanskiy.laptops.service;

import bohdan.papizhanskiy.laptops.dto.request.OrderRequest;
import bohdan.papizhanskiy.laptops.dto.request.PaginationRequest;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.dto.response.OrderResponse;
import bohdan.papizhanskiy.laptops.entity.Order;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;


    private Order orderRequestToOrder(Order order, OrderRequest orderRequest) throws WrongInputException {
        if (order == null) {
            order = new Order();
        }
        order.setProductForOrder(orderRequest.getProductForOrder());
        order.setCustomer(customerService.findOne(orderRequest.getCustomerId()));

        return orderRepository.save(order);
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(OrderResponse::new).collect(Collectors.toList());
    }

    public OrderResponse save(OrderRequest orderRequest) throws WrongInputException {
        return new OrderResponse(orderRequestToOrder(null, orderRequest));
    }

    public Order findOne(Long id) throws WrongInputException {
        return orderRepository.findById(id).orElseThrow(() -> new WrongInputException("Order with " + id + " not exists"));
    }

    public void delete(Long id) throws WrongInputException {
        orderRepository.delete(findOne(id));
    }

    public OrderResponse update(Long id, OrderRequest orderRequest) throws WrongInputException {
        return new OrderResponse(orderRequestToOrder(findOne(id), orderRequest));
    }

    public DataResponse<OrderResponse> findAll(PaginationRequest paginationRequest) {
        Page<Order> all = orderRepository.findAll(paginationRequest.mapToPageRequest());
        return new DataResponse<>(all.get().map(OrderResponse::new).collect(Collectors.toList()), all.getTotalPages(), all.getTotalElements());
    }
}

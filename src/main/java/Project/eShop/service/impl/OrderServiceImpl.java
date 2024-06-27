package Project.eShop.service.impl;

import Project.eShop.advice.exception.RecordNotFoundException;
import Project.eShop.converter.OrderConverter;
import Project.eShop.dto.OrderRequest;
import Project.eShop.dto.OrderResponse;
import Project.eShop.model.Customer;
import Project.eShop.model.Order;
import Project.eShop.repository.CustomerRepository;
import Project.eShop.repository.OrderRepository;
import Project.eShop.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderConverter orderConverter;
    private OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderServiceImpl(OrderConverter orderConverter,
                            OrderRepository orderRepository,
                            CustomerRepository customerRepository) {
        this.orderConverter = orderConverter;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Optional<Customer> customer = customerRepository.findById(orderRequest.getCustomerId());
        Customer existingCustomer;


        if (customer.isEmpty()) {
            throw new RecordNotFoundException("Email not found. Invalid email request.");
        }else{
            log.info("Going to use existing customer with identifier: " + orderRequest.getCustomerId());
            existingCustomer = customer.get();
        }

        Order savedOrder = orderConverter.toOrder(orderRequest);
        OrderResponse orderResponse = new OrderResponse();
        savedOrder.setCustomer(existingCustomer);
        orderRepository.save(savedOrder);
        BeanUtils.copyProperties(savedOrder, orderResponse);
        return orderResponse;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(String.format("Order with id %s not exist", id)));
        OrderResponse orderResponse = new OrderResponse();

        BeanUtils.copyProperties(order, orderResponse);
        return orderResponse;
    }

    @Override
    public Order updateOrder(OrderRequest orderRequest, Long id) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(String.format("Order with id %s not exist", id)));
        existingOrder.setTotalAmount(orderRequest.getTotalAmount());

        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(String.format("Unable to delete, order with id %s not exist", id)));

        orderRepository.deleteById(id);
    }
}

package eShop.Project_eShop.service.impl;

import eShop.Project_eShop.advice.exception.RecordNotFoundException;
import eShop.Project_eShop.converter.CustomerConverter;
import eShop.Project_eShop.converter.OrderConverter;
import eShop.Project_eShop.dto.CustomerRequest;
import eShop.Project_eShop.dto.OrderRequest;
import eShop.Project_eShop.dto.OrderResponse;
import eShop.Project_eShop.model.Customer;
import eShop.Project_eShop.model.Order;
import eShop.Project_eShop.repository.CustomerRepository;
import eShop.Project_eShop.repository.OrderRepository;
import eShop.Project_eShop.service.OrderService;
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

    // public TollResponse addToll(TollRequest tollRequest) {
    //        Optional<Owner> owner = ownerRepository.findByEmail(tollRequest.getEmail());
    //        Owner existingOwner;
    //
    //        if (owner.isEmpty()) {
    //            log.info("Going to create new owner with identifier: " + tollRequest.getEmail());
    //            existingOwner = ownerRepository.save(ownerConverter.toOwner(tollRequest));
    //
    //        } else {
    //            log.info("Going to use existing owner with identifier: " + tollRequest.getEmail());
    //            existingOwner = owner.get();
    //        }
    //
    //        TollPass tollPass = tollConverter.toTollPass(tollRequest);
    //        tollPass.setOwner(existingOwner);
    //        return tollConverter.toTollResponse(tollRepository.save(tollPass));
    //    }
    @Transactional
    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Optional<Customer> customer = customerRepository.findById(orderRequest.getId());
        Customer existingCustomer;


        if (customer.isEmpty()) {
            throw new RecordNotFoundException("Email not found. Invalid email request.");
        }else{
            log.info("Going to use existing customer with identifier: " + orderRequest.getId());
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

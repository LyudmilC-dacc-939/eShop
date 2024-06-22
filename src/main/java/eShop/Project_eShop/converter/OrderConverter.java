package eShop.Project_eShop.converter;

import eShop.Project_eShop.advice.exception.RecordNotFoundException;
import eShop.Project_eShop.dto.OrderRequest;
import eShop.Project_eShop.model.Customer;
import eShop.Project_eShop.model.Order;
import eShop.Project_eShop.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@Slf4j
public class OrderConverter {

    @Autowired
    private CustomerRepository customerRepository;

    public Order toOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setTotalAmount(orderRequest.getTotalAmount());
        Optional<Customer> customer = customerRepository.findById(orderRequest.getId());
        order.setCustomer(customer.get());
        order.setOrderDate(Instant.now());
        return order;
    }


//    public Customer customerToOrder(OrderRequest orderRequest) {
//        //email input-->get customer by email-->get customer id-->return customer id to order
//        Optional<Customer> customer = customerRepository.findByEmail(orderRequest.getCustomerEmail());
//        Customer existingCustomer;
//        if (customer.isEmpty()) {
//            throw new RecordNotFoundException("Email not found. Invalid email request.");
//        }
//
//        log.info("Going to use existing customer with identifier: " + orderRequest.getCustomerEmail());
//        existingCustomer = customer.get();
//
//        return existingCustomer;
//    }

}



package Project.eShop.converter;

import Project.eShop.dto.OrderRequest;
import Project.eShop.model.Customer;
import Project.eShop.model.Order;
import Project.eShop.model.Product;
import Project.eShop.repository.CustomerRepository;

import Project.eShop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class OrderConverter {

    private CustomerRepository customerRepository;
    private ProductRepository productRepository;

    @Autowired
    public OrderConverter(CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public Order toOrder(OrderRequest orderRequest) {
        Order order = new Order();
        //order.setTotalAmount(orderRequest.getTotalAmount());
        Optional<Customer> customer = customerRepository.findById(orderRequest.getCustomerId());
        order.setCustomer(customer.get());
        order.setOrderDate(Instant.now());

        Double priceOfAllProducts = 0.0;
        Set<Product> products = new HashSet<>();
        for (Product product: productRepository.findAll()){
           for (Long productId: orderRequest.getProductsId()){
               if(product.getId().equals(productId)){
                   products.add(product);
               }
               priceOfAllProducts+=product.getPrice();
           }
        }
        order.setTotalAmount(priceOfAllProducts);
        order.setProducts(products);
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



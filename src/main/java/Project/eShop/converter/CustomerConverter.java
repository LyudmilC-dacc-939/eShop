package Project.eShop.converter;

import Project.eShop.dto.CustomerRequest;
import Project.eShop.model.Address;
import Project.eShop.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerConverter {



    public Customer toCustomer(CustomerRequest customerRequest){
        Customer customer = new Customer();
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setEmail(customerRequest.getEmail());
        return customer;
    }
}

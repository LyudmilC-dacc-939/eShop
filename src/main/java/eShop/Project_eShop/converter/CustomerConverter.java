package eShop.Project_eShop.converter;

import eShop.Project_eShop.dto.CustomerRequest;
import eShop.Project_eShop.dto.OrderRequest;
import eShop.Project_eShop.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    public Customer toCustomer(CustomerRequest customerRequest){
        Customer customer = new Customer();
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setAddress(customerRequest.getAddress());
        customer.setEmail(customerRequest.getEmail());
        return customer;
    }
}

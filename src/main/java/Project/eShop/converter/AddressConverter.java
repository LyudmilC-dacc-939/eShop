package Project.eShop.converter;

import Project.eShop.dto.AddressRequest;
import Project.eShop.model.Address;
import Project.eShop.model.Customer;
import Project.eShop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddressConverter {

    @Autowired
    private CustomerRepository customerRepository;

    public Address toAddress(AddressRequest addressRequest) {
        Address address = new Address();
        address.setCountry(addressRequest.getCountry());
        address.setCity(addressRequest.getCity());
        address.setStreet(addressRequest.getStreet());
        address.setPostalCode(addressRequest.getPostalCode());
        Optional<Customer> customer = customerRepository.findById(addressRequest.getCustomerId());
        address.setCustomer(customer.get());

        return address;
    }
}

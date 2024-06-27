package Project.eShop.dto;

import Project.eShop.model.Address;
import Project.eShop.model.Order;
import lombok.Data;

import java.util.Set;

@Data
public class CustomerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    //private String address;
    private Set<Address> addresses;
    private Set<Order> orders;
}

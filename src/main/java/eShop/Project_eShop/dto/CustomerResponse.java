package eShop.Project_eShop.dto;

import eShop.Project_eShop.model.Order;
import lombok.Data;

import java.util.Set;

@Data
public class CustomerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Set<Order> orders;
}

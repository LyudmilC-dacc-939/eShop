package Project.eShop.dto;

import Project.eShop.model.Customer;
import lombok.Data;

@Data
public class AddressResponse {

    private Long id;
    private String street;
    private String city;
    private String country;
    private String postalCode;
    private Customer customer;

}

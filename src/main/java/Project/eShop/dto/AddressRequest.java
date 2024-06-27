package Project.eShop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {

    @NotNull(message = "Street name cannot be null!")
    @Size(min = 4, max = 20, message = "Street name must be between 4-20 characters")
    private String street;
    @NotNull(message = "City cannot be null!")
    @Size(min = 3, max = 20, message = "City must be between 3-20 characters")
    private String city;
    @NotNull(message = "Country cannot be null!")
    private String country;
    @NotNull(message = "Postal code cannot be null!")
    @Size(min = 3, max = 10, message = "Postal code must be between 3-10 characters")
    private String postalCode;
    @NotNull(message = "Order must have parent id!")
    private Long customerId;

}


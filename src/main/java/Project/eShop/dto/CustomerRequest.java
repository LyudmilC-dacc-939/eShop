package Project.eShop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

    @NotNull(message = "First name cannot be null!")
    @Size(max = 25,message = "First name must be maximum 25 characters!")
    private String firstName;
    @NotNull(message = "Last name cannot be null!")
    @Size(min = 4,max = 50,message = "Last name must be between 4 and 50 characters!")
    private String lastName;
    @NotNull(message = "Email cannot be null!")
    @Email
    private String email;
    //@NotNull(message = "Address cannot be null!")
    //private String address;
    //Преди отделянето на адресите в отделен клас
}

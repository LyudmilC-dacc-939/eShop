package Project.eShop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class OrderRequest {

    @NotNull(message = "Total amount cannot be null!")
    private Double totalAmount;
    @NotNull(message = "CustomerId cannot be null!")
    private Long customerId;
    private Set<Long> productsId;
    //private Customer customer
    //Ако го оставя така ще иска да вкарвам масив с всичките данни за потребителя, сега вече е с id

//    @NotNull(message = "Order must have at least one product!")
//    private Set<Long> productsId;
}



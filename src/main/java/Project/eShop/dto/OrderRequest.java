package Project.eShop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    @NotNull(message = "Total amount cannot be null!")
    private Double totalAmount;
    @NotNull(message = "Order must have parent id!")
    private Long customerId;
    //private Customer customer
    //Ако го оставя така ще иска да вкарвам масив с всичките данни за потребителя, сега вече е с id

//    @NotNull(message = "Order must have at least one product!")
//    private Set<Long> productsId;
}



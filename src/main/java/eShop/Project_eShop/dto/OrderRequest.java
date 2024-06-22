package eShop.Project_eShop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    @NotNull(message = "Total amount cannot be null!")
    private Double totalAmount;
    @NotNull(message = "Order must have parent id!")
    private Long id;
}

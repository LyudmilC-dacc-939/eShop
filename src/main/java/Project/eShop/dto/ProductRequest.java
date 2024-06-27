package Project.eShop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    @NotNull(message = "Name cannot be null!")
    private String name;
    @NotNull(message = "Price cannot be null!")
    private Double price;
    @NotNull(message = "Order cannot be null!")
    private Long orderId;
}

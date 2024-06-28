package Project.eShop.dto;

import Project.eShop.model.Product;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
public class OrderResponse {
    private Long id;
    private Instant orderDate;
    private Double totalAmount;
    private Set<Product> products;
}


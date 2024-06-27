package Project.eShop.dto;

import Project.eShop.model.Customer;
import Project.eShop.model.Product;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private Instant orderDate;
    private Double totalAmount;
    private Customer customer;
    private List<Product> products;
}


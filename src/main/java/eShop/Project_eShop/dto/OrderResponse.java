package eShop.Project_eShop.dto;

import eShop.Project_eShop.model.Customer;
import lombok.Data;

import java.time.Instant;

@Data
public class OrderResponse {
    private Long id;
    private Instant orderDate;
    private Double totalAmount;
    private Customer customer;
}

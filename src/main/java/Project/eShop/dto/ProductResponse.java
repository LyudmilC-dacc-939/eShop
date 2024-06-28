package Project.eShop.dto;

import Project.eShop.model.Order;
import lombok.Data;

import java.util.Set;


@Data
public class ProductResponse {

    private Long id;
    private String name;
    private Double price;
    Set<Order> orders;

}

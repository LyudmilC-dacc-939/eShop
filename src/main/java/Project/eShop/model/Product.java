package Project.eShop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    @Column(name = "product_name")
    private String name;
    private Double price;
    // private Order order;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("products")
    private Set<Order> orders = new HashSet<>();



}

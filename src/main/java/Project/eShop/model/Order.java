package Project.eShop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    @Column(name = "order_date")
    private Instant orderDate;
    private Double totalAmount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new LinkedList<>();

    //  @ManyToMany(fetch = FetchType.EAGER)
    //    @JoinTable(
    //            name = "order_products",
    //            joinColumns = @JoinColumn(name = "order_id"),
    //            inverseJoinColumns = @JoinColumn(name = "product_id")
    //    )
    //    @JsonManagedReference
    //    private List<Product> products = new LinkedList<>();

}

package Project.eShop.converter;

import Project.eShop.dto.ProductRequest;
import Project.eShop.model.Order;
import Project.eShop.model.Product;
import Project.eShop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ProductConverter {

    @Autowired
    private OrderRepository orderRepository;

    public Product toProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        Order order = orderRepository.getReferenceById(productRequest.getOrderId());
        Set<Order> orders = new HashSet<>();
        orders.add(order);
        product.setOrders(orders);

        return product;
    }
}

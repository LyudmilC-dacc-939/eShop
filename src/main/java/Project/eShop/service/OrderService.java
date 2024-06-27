package Project.eShop.service;

import Project.eShop.dto.OrderRequest;
import Project.eShop.dto.OrderResponse;
import Project.eShop.model.Order;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);

    List<Order> getAllOrders();

    OrderResponse getOrderById(Long id);

    Order updateOrder(OrderRequest orderRequest, Long id);

    void deleteOrder(Long id);
}

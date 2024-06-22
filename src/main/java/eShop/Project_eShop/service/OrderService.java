package eShop.Project_eShop.service;

import eShop.Project_eShop.dto.OrderRequest;
import eShop.Project_eShop.dto.OrderResponse;
import eShop.Project_eShop.model.Order;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);

    List<Order> getAllOrders();

    OrderResponse getOrderById(Long id);

    Order updateOrder(OrderRequest orderRequest, Long id);

    void deleteOrder(Long id);
}

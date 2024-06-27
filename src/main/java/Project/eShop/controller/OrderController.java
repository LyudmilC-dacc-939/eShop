package Project.eShop.controller;

import Project.eShop.dto.OrderRequest;
import Project.eShop.dto.OrderResponse;
import Project.eShop.model.Order;
import Project.eShop.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(path = "/create")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest){
        return new ResponseEntity<>(orderService.createOrder(orderRequest),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(orderService.getOrderById(id),
                HttpStatus.FOUND);
    }

    @GetMapping("/all-orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.FOUND);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@Valid @RequestBody OrderRequest orderRequest,
                             @PathVariable("id") Long id){
        orderService.updateOrder(orderRequest, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<OrderResponse> deleteOrderById(@PathVariable ("id") Long id){
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package Project.eShop.service.impl;

import Project.eShop.advice.exception.RecordNotFoundException;
import Project.eShop.converter.ProductConverter;
import Project.eShop.dto.ProductRequest;
import Project.eShop.dto.ProductResponse;
import Project.eShop.model.Order;
import Project.eShop.model.Product;
import Project.eShop.repository.OrderRepository;
import Project.eShop.repository.ProductRepository;
import Project.eShop.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private OrderRepository orderRepository;
    private ProductConverter productConverter;
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(OrderRepository orderRepository,
                              ProductConverter productConverter,
                              ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productConverter = productConverter;
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Optional<Order> order = orderRepository.findById(productRequest.getOrderId());

        if (order.isEmpty()) {
            throw new RecordNotFoundException("Order not found. Invalid order id request.");
        }
        ProductResponse productResponse = new ProductResponse();
        Product savedProduct = productConverter.toProduct(productRequest);
        productRepository.save(savedProduct);
        savedProduct.setOrder(order.get());
        BeanUtils.copyProperties(savedProduct, productResponse);
        return productResponse;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(String.format("Product with id %s not exist", id)));
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);
        return productResponse;
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, Long productId, Long orderId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new RecordNotFoundException(String.format("Product with id %s not exist", productId)));

        Order currentOrder = orderRepository.getReferenceById(orderId);
        if (currentOrder == null) {
            throw new RecordNotFoundException("Order id not exist!");
        }
        product.setOrder(currentOrder);
        product.setPrice(productRequest.getPrice());
        product.setName(productRequest.getName());

        ProductResponse productResponse = new ProductResponse();

        BeanUtils.copyProperties(product, productResponse);
        System.out.println(currentOrder);
        return productResponse;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(String.format("Product with id %s not exist", id)));
        productRepository.deleteById(id);
    }
}

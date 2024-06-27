package Project.eShop.service;

import Project.eShop.dto.ProductRequest;
import Project.eShop.dto.ProductResponse;
import Project.eShop.model.Product;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest productRequest);

    List<Product> getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(ProductRequest productRequest, Long productId, Long orderId);

    void deleteProduct(Long id);
}

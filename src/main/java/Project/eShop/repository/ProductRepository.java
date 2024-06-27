package Project.eShop.repository;

import Project.eShop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getAllByOrderId(Long id);

    List<Product> getAll();

}

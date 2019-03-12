package login.repository;

import org.springframework.data.repository.CrudRepository;

import login.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}

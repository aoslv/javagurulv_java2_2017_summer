package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Product;

import java.util.List;
import java.util.Optional;

public interface Database {

    void addProduct(Product product);
    void deleteProduct(Product product);
    List<Product> getAllProducts();
    Optional<Product> getProductByTitle(String title);

}

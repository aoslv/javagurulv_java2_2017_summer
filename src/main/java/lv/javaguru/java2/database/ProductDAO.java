package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {

    void add(Product product);
    void delete(Product product);
    List<Product> getAll();
    Optional<Product> getByTitle(String title);

}

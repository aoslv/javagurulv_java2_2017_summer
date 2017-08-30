package lv.javaguru.java2.console.database;

import com.google.common.collect.Lists;
import lv.javaguru.java2.console.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DatabaseImpl implements Database {

    private List<Product> products = Lists.newArrayList();

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public void deleteProduct(Product product) {
        products.remove(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return Lists.newArrayList(products);
    }

    @Override
    public Optional<Product> getProductByTitle(String title) {
        return products.stream()
                .filter(p -> p.getTitle().equals(title))
                .findFirst();
    }

}

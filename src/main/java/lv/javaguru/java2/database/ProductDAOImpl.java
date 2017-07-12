package lv.javaguru.java2.database;

import com.google.common.collect.Lists;
import lv.javaguru.java2.domain.Product;

import java.util.List;
import java.util.Optional;

public class ProductDAOImpl implements ProductDAO {

    private List<Product> products = Lists.newArrayList();

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public void delete(Product product) {
        products.remove(product);
    }

    @Override
    public List<Product> getAll() {
        return Lists.newArrayList(products);
    }

    @Override
    public Optional<Product> getByTitle(String title) {
        return products.stream()
                .filter(p -> p.getTitle().equals(title))
                .findFirst();
    }

}

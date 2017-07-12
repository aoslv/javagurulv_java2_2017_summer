package lv.javaguru.java2.businesslogic;


import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Product;

import java.util.List;
import java.util.Optional;

public class BusinessLogicImpl implements BusinessLogic {

    private Database dao;

    public BusinessLogicImpl(Database dao) {
        this.dao = dao;
    }

    @Override
    public boolean addProduct(String title, String description) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);

        if (alreadyExist(product)) {
            return false;
        } else {
            dao.addProduct(product);
            return true;
        }
    }

    @Override
    public boolean removeProductByTitle(String title) {
        Optional<Product> foundProduct = dao.getProductByTitle(title);
        if (foundProduct.isPresent()) {
            Product product = foundProduct.get();
            dao.deleteProduct(product);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return dao.getAllProducts();
    }

    private boolean alreadyExist(Product product) {
        return dao.getProductByTitle(product.getTitle()).isPresent();
    }

}

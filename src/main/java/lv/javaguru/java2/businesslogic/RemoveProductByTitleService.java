package lv.javaguru.java2.businesslogic;


import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

public interface RemoveProductByTitleService {
    boolean remove(String title);
}

@Component
class RemoveProductByTitleServiceImpl implements RemoveProductByTitleService {

    @Autowired private ProductDAO dao;

    @Override
    public boolean remove(String title) {
        Optional<Product> foundProduct = dao.getByTitle(title);
        if (foundProduct.isPresent()) {
            Product product = foundProduct.get();
            dao.delete(product);
            return true;
        } else {
            return false;
        }
    }
}

package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

public interface GetAllProductsService {
    List<Product> getAllProducts();
}

@Component
class GetAllProductsServiceImpl implements GetAllProductsService {

    @Autowired private ProductDAO dao;

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        return dao.getAll();
    }

}

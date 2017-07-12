package lv.javaguru.java2.businesslogic;


import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Product;

public class AddProductServiceImpl implements AddProductService {

    private ProductDAO dao;

    public AddProductServiceImpl(ProductDAO dao) {
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
            dao.add(product);
            return true;
        }
    }

    private boolean alreadyExist(Product product) {
        return dao.getByTitle(product.getTitle()).isPresent();
    }

}

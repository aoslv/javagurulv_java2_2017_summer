package lv.javaguru.java2.businesslogic;


import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Product;

import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.domain.ProductBuilder.createProduct;

public class BusinessLogicImpl implements BusinessLogic {

    private Database database;
    private AddProductValidator addProductValidator;

    public BusinessLogicImpl(Database database,
                             AddProductValidator addProductValidator) {
        this.database = database;
        this.addProductValidator = addProductValidator;
    }

    @Override
    public Response addProduct(String title, String description) {
        List<Error> validationErrors = addProductValidator.validate(title, description);
        if (!validationErrors.isEmpty()) {
            return Response.createFailResponse(validationErrors);
        }

        Product product = createProduct()
                .withTitle(title)
                .withDescription(description).build();

        database.addProduct(product);

        return Response.createSuccessResponse();
    }


    @Override
    public boolean removeProductByTitle(String title) {
        Optional<Product> foundProduct = database.getProductByTitle(title);
        if (foundProduct.isPresent()) {
            Product product = foundProduct.get();
            database.deleteProduct(product);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return database.getAllProducts();
    }

}

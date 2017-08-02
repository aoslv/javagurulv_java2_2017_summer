package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static lv.javaguru.java2.domain.ProductBuilder.createProduct;

public interface AddProductService {

    Response addProduct(String title, String description);

}

@Component
class AddProductServiceImpl implements AddProductService {

    @Autowired private ProductDAO dao;
    @Autowired private AddProductValidator addProductValidator;


    @Override
    public Response addProduct(String title, String description) {
        List<Error> validationErrors = addProductValidator.validate(title, description);
        if (!validationErrors.isEmpty()) {
            return Response.createFailResponse(validationErrors);
        }

        Product product = createProduct()
                .withTitle(title)
                .withDescription(description).build();

        dao.save(product);

        return Response.createSuccessResponse();
    }
}

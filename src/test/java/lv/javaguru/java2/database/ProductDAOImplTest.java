package lv.javaguru.java2.database;

import lv.javaguru.java2.database.jdbc.ProductDAOImpl;
import lv.javaguru.java2.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static lv.javaguru.java2.domain.ProductBuilder.createProduct;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ProductDAOImplTest {

    private DatabaseUtil databaseUtil = new DatabaseUtil();
    private ProductDAO productDAO = new ProductDAOImpl();

    @Before
    public void init() {
        databaseUtil.cleanDatabase();
    }

    @Test
    public void testCreate() throws Exception {
        Product product = createProduct()
                .withTitle("Title")
                .withDescription("Description").build();

        product = productDAO.save(product);
        assertThat(product.getId(), is(notNullValue()));

        Optional<Product> productFromDB = productDAO.getById(product.getId());
        assertThat(productFromDB.isPresent(), is(true));
        assertEquals(product.getId(), productFromDB.get().getId());
        assertEquals(product.getTitle(), productFromDB.get().getTitle());
        assertEquals(product.getDescription(), productFromDB.get().getDescription());
    }

}

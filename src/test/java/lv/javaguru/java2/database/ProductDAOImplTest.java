package lv.javaguru.java2.database;

import lv.javaguru.java2.config.SpringAppConfig;
import lv.javaguru.java2.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

import static lv.javaguru.java2.domain.ProductBuilder.createProduct;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ProductDAOImplTest {

    private DatabaseUtil databaseUtil;
    private ProductDAO productDAO;

    @Before
    public void init() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringAppConfig.class);
        productDAO = applicationContext.getBean(ProductDAO.class);
        databaseUtil = applicationContext.getBean(DatabaseUtil.class);
        databaseUtil.cleanDatabase();
    }

    @Test
    public void testCreate() throws Exception {
        Product product = createProduct()
                .withTitle("Title AAA")
                .withDescription("Description VVV").build();

        assertThat(product.getId(), is(nullValue()));
        product = productDAO.save(product);
        assertThat(product.getId(), is(notNullValue()));

        Optional<Product> productFromDB = productDAO.getById(product.getId());

        assertThat(productFromDB.isPresent(), is(true));
        assertEquals(product.getId(), productFromDB.get().getId());
        assertEquals(product.getTitle(), productFromDB.get().getTitle());
        assertEquals(product.getDescription(), productFromDB.get().getDescription());
    }

}

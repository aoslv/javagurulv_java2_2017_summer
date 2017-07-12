package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddProductServiceImplTest {

    private ProductDAO dao;
    private AddProductService service;

    @Before
    public void init(){
        dao = mock(ProductDAO.class);
        service = new AddProductServiceImpl(dao);
    }

    @Test
    public void shouldAddNewProductIfNotExistInTheList() {
        doReturn(Optional.empty()).when(dao).getByTitle("Bread");
        boolean result = service.addProduct("Bread", "1gab");
        assertThat(result, is(true));
        verify(dao).getByTitle("Bread");
    }

    @Test
    public void shouldNotAddNewProductIfAlreadyExistInTheList() {
        Product product = mock(Product.class);
        doReturn(Optional.of(product)).when(dao).getByTitle("Milk");
        boolean result = service.addProduct("Milk", "1L");
        assertThat(result, is(false));
    }

}
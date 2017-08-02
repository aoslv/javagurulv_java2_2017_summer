package lv.javaguru.java2.businesslogic;

import com.google.common.collect.Lists;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddProductServiceImplTest {

    @Mock private ProductDAO dao;
    @Mock private AddProductValidator addProductValidator;

    @InjectMocks
    private AddProductService service = new AddProductServiceImpl();


    @Test
    public void shouldAddNewProductIfNotExistInTheList() {
        doReturn(Lists.newArrayList()).when(addProductValidator).validate("Bread", "1gab");
        Response result = service.addProduct("Bread", "1gab");
        assertThat(result.isSuccess(), is(true));
        verify(dao).save(any(Product.class));
    }

    @Test
    public void shouldNotAddNewProductIfAlreadyExistInTheList() {
        Error error = new Error("title", "Already exist");
        doReturn(Lists.newArrayList(error)).when(addProductValidator).validate("Bread", "1gab");
        Response result = service.addProduct("Bread", "1gab");
        assertThat(result.isFail(), is(true));
    }

}
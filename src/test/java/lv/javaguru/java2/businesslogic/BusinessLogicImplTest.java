package lv.javaguru.java2.businesslogic;

import com.google.common.collect.Lists;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Product;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BusinessLogicImplTest {

    private Database database;
    private AddProductValidator addProductValidator;
    private BusinessLogic service;

    @Before
    public void init(){
        database = mock(Database.class);
        addProductValidator = mock(AddProductValidator.class);
        service = new BusinessLogicImpl(database, addProductValidator);
    }

    @Test
    public void shouldAddNewProductIfNotExistInTheList() {
        doReturn(Lists.newArrayList()).when(addProductValidator).validate("Bread", "1gab");
        Response result = service.addProduct("Bread", "1gab");
        assertThat(result.isSuccess(), is(true));
        verify(database).addProduct(any(Product.class));
    }

    @Test
    public void shouldNotAddNewProductIfAlreadyExistInTheList() {
        Error error = new Error("title", "Already exist");
        doReturn(Lists.newArrayList(error)).when(addProductValidator).validate("Bread", "1gab");
        Response result = service.addProduct("Bread", "1gab");
        assertThat(result.isFail(), is(true));
    }

}

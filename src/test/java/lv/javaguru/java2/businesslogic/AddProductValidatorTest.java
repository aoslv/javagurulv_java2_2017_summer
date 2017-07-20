package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.domain.ProductBuilder.createProduct;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;

public class AddProductValidatorTest {

    private Database database;
    private AddProductValidator validator;

    @Before
    public void init() {
        database = Mockito.mock(Database.class);
        validator = new AddProductValidator(database);
    }

    @Test
    public void shouldReturnErrorWhenTitleIsNull() {
        List<Error> errors = validator.validate(null, "description");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("title"));
        assertThat(errors.get(0).getErrorMessage(), is("Must be not empty"));
    }

    @Test
    public void shouldReturnErrorWhenTitleIsEmpty() {
        List<Error> errors = validator.validate("", "description");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("title"));
        assertThat(errors.get(0).getErrorMessage(), is("Must be not empty"));
    }

    @Test
    public void shouldReturnErrorWhenDescriptionIsNull() {
        List<Error> errors = validator.validate("title", null);
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("description"));
        assertThat(errors.get(0).getErrorMessage(), is("Must be not empty"));
    }

    @Test
    public void shouldReturnErrorWhenDescriptionIsEmpty() {
        List<Error> errors = validator.validate("title", "");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("description"));
        assertThat(errors.get(0).getErrorMessage(), is("Must be not empty"));
    }

    @Test
    public void shouldFailIfProductWitSameTitleAlreadyExistInDB() {
        Product product = createProduct().build();
        doReturn(Optional.of(product)).when(database).getProductByTitle("title");
        List<Error> errors = validator.validate("title", "description");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("title"));
        assertThat(errors.get(0).getErrorMessage(), is("Already exist"));
    }

}

package lv.javaguru.java2.businesslogic.users;

import lv.javaguru.java2.businesslogic.api.Error;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.domain.UserBuilder.createUser;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class RegisterUserValidatorImplTest {

    @Mock private UserDAO userDAO;

    @InjectMocks
    private RegisterUserValidator validator = new RegisterUserValidatorImpl();


    @Test
    public void shouldReturnErrorWhenLoginIsNull() {
        List<Error> errors = validator.validate(null, "password");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("login"));
        assertThat(errors.get(0).getErrorMessage(), is("Must be not empty"));
    }

    @Test
    public void shouldReturnErrorWhenLoginIsEmpty() {
        List<Error> errors = validator.validate("", "password");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("login"));
        assertThat(errors.get(0).getErrorMessage(), is("Must be not empty"));
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsNull() {
        List<Error> errors = validator.validate("login", null);
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("password"));
        assertThat(errors.get(0).getErrorMessage(), is("Must be not empty"));
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsEmpty() {
        List<Error> errors = validator.validate("login", "");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("password"));
        assertThat(errors.get(0).getErrorMessage(), is("Must be not empty"));
    }

    @Test
    public void shouldFailIfUserWithSameLoginAlreadyExistInDB() {
        User user = createUser().build();
        doReturn(Optional.of(user)).when(userDAO).getByLogin("login");
        List<Error> errors = validator.validate("login", "password");
        assertThat(errors.size(), is(1));
        assertThat(errors.get(0).getField(), is("login"));
        assertThat(errors.get(0).getErrorMessage(), is("Already exist"));
    }

}

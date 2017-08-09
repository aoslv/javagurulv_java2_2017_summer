package lv.javaguru.java2.domain;

import org.junit.Test;

import static lv.javaguru.java2.domain.ShoppingListBuilder.createShoppingList;
import static lv.javaguru.java2.domain.UserBuilder.createUser;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ShoppingListBuilderTest {

    @Test
    public void createShoppingListWithUser() {
        ShoppingList list = createShoppingList()
                .withId(1L)
                .withTitle("Monday")
                .withUser(
                        createUser()
                            .withId(2L)
                            .withLogin("java")
                            .withPassword("star")
                ).build();
        assertThat(list.getId(), is(1L));
        assertThat(list.getTitle(), is("Monday"));
        assertThat(list.getUser(), is(notNullValue()));
        assertThat(list.getUser().getId(), is(2L));
        assertThat(list.getUser().getLogin(), is("java"));
        assertThat(list.getUser().getPassword(), is("star"));
    }

}

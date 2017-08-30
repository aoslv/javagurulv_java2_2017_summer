package lv.javaguru.java2.console.domain;

public class ShoppingListBuilder {

    private Long id;
    private String title;
    private User user;

    private ShoppingListBuilder() {}

    public static ShoppingListBuilder createShoppingList() {
        return new ShoppingListBuilder();
    }

    public ShoppingList build() {
        ShoppingList list = new ShoppingList();
        list.setId(id);
        list.setTitle(title);
        list.setUser(user);
        return list;
    }

    public ShoppingListBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ShoppingListBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ShoppingListBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public ShoppingListBuilder withUser(UserBuilder userBuilder) {
        this.user = userBuilder.build();
        return this;
    }

}

package lv.javaguru.java2.console.domain;

public class ShoppingListItemBuilder {

    private Long id;
    private ShoppingList shoppingList;
    private Product product;
    private String quantity;

    private ShoppingListItemBuilder() {}

    public static ShoppingListItemBuilder createShoppingListItem() {
        return new ShoppingListItemBuilder();
    }

    public ShoppingListItem build() {
        ShoppingListItem item = new ShoppingListItem();
        item.setId(id);
        item.setShoppingList(shoppingList);
        item.setProduct(product);
        item.setQuantity(quantity);
        return item;
    }

    public ShoppingListItemBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ShoppingListItemBuilder withShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
        return this;
    }

    public ShoppingListItemBuilder withShoppingList(ShoppingListBuilder shoppingListBuilder) {
        this.shoppingList = shoppingListBuilder.build();
        return this;
    }

    public ShoppingListItemBuilder withProduct(Product product) {
        this.product = product;
        return this;
    }

    public ShoppingListItemBuilder withProduct(ProductBuilder productBuilder) {
        this.product = productBuilder.build();
        return this;
    }

    public ShoppingListItemBuilder withQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

}

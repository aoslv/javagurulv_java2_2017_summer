package lv.javaguru.java2.domain;

public class ProductBuilder {

    private String title;
    private String description;

    private ProductBuilder() {}

    public static ProductBuilder createProduct() {
        return new ProductBuilder();
    }

    public static Product createProduct(String title,
                                        String description) {
        return createProduct()
                .withTitle("Milk")
                .withDescription("1L").build();
    }

    public Product build() {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        return product;
    }

    public ProductBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ProductBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

}

package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.GetAllProductsService;
import lv.javaguru.java2.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrintShoppingListView implements View {

    private GetAllProductsService getAllProductsService;

    @Autowired
    public PrintShoppingListView(GetAllProductsService getAllProductsService) {
        this.getAllProductsService = getAllProductsService;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Print shopping list to console execution start!");
        for (Product product : getAllProductsService.getAllProducts()) {
            System.out.println(product.getTitle() + "[" + product.getDescription() + "]");
        }
        System.out.println("Print shopping list to console execution end!");
        System.out.println();
    }
}

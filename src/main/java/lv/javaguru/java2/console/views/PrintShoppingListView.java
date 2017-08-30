package lv.javaguru.java2.console.views;

import lv.javaguru.java2.console.businesslogic.GetAllProductsService;
import lv.javaguru.java2.console.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrintShoppingListView implements View {

    @Autowired private GetAllProductsService getAllProductsService;

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

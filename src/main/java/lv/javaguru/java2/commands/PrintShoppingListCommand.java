package lv.javaguru.java2.commands;

import lv.javaguru.java2.Command;
import lv.javaguru.java2.domain.Product;

import java.util.List;

public class PrintShoppingListCommand implements Command {
    @Override
    public void execute(List<Product> products) {
        System.out.println();
        System.out.println("Print shopping list to console execution start!");
        for (Product product : products) {
            System.out.println(product.getTitle() + "[" + product.getDescription() + "]");
        }
        System.out.println("Print shopping list to console execution end!");
        System.out.println();
    }
}

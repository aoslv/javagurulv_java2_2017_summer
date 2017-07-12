package lv.javaguru.java2.commands;

import lv.javaguru.java2.businesslogic.BusinessLogic;
import lv.javaguru.java2.domain.Product;

public class PrintShoppingListCommand implements Command {

    private BusinessLogic businessLogic;

    public PrintShoppingListCommand(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Print shopping list to console execution start!");
        for (Product product : businessLogic.getAllProducts()) {
            System.out.println(product.getTitle() + "[" + product.getDescription() + "]");
        }
        System.out.println("Print shopping list to console execution end!");
        System.out.println();
    }
}

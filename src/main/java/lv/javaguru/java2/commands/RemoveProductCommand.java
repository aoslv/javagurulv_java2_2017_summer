package lv.javaguru.java2.commands;

import lv.javaguru.java2.businesslogic.BusinessLogic;

import java.util.Scanner;

public class RemoveProductCommand implements Command {

    private BusinessLogic businessLogic;

    public RemoveProductCommand(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Remove product from list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product title:");
        final String title = sc.nextLine();

        ///////////////////BL/////////////////////////

        boolean result = businessLogic.removeProductByTitle(title);

        ////////////////////BL end /////////////////

        if (result) {
            System.out.println("Product with title " + title + " was found and will be removed from list!");
        } else {
            System.out.println("Product with title " + title + " not found and not be removed from list!");
        }

        System.out.println("Remove product from list execution end!");
        System.out.println();
    }
}

package lv.javaguru.java2.commands;

import lv.javaguru.java2.businesslogic.BusinessLogic;

import java.util.Scanner;

public class AddProductCommand implements Command {

    private BusinessLogic businessLogic;

    public AddProductCommand(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Add product to list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product title:");
        String title = sc.nextLine();
        System.out.print("Enter product description:");
        String description = sc.nextLine();

        ///////////////////////BL/////////////////////

        boolean result = businessLogic.addProduct(title, description);

        //////////////BL END//////////

        if (!result) {
            System.out.println("Can not addProduct this product. " +
                    "Already exist in the list");
        }

        System.out.println("Add product to list execution end!");
        System.out.println();
    }

}

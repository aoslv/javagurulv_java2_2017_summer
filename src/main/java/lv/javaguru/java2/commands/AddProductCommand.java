package lv.javaguru.java2.commands;

import lv.javaguru.java2.Command;
import lv.javaguru.java2.businesslogic.AddProductService;
import lv.javaguru.java2.domain.Product;

import java.util.List;
import java.util.Scanner;

public class AddProductCommand implements Command {

    private AddProductService addProductService;

    public AddProductCommand(AddProductService addProductService) {
        this.addProductService = addProductService;
    }

    @Override
    public void execute(List<Product> products) {
        System.out.println();
        System.out.println("Add product to list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product title:");
        String title = sc.nextLine();
        System.out.print("Enter product description:");
        String description = sc.nextLine();

        ///////////////////////BL/////////////////////

        boolean result = addProductService.addProduct(title, description);

        //////////////BL END//////////

        if (!result) {
            System.out.println("Can not add this product. " +
                    "Already exist in the list");
        }

        System.out.println("Add product to list execution end!");
        System.out.println();
    }

}

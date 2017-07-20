package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.BusinessLogic;
import lv.javaguru.java2.businesslogic.Response;

import java.util.Scanner;

public class AddProductView implements View {

    private BusinessLogic businessLogic;

    public AddProductView(BusinessLogic businessLogic) {
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

        Response response = businessLogic.addProduct(title, description);

        //////////////BL END//////////

        if (response.isFail()) {
            // TODO print List<Error>
            System.out.println("Can not addProduct this product. " +
                    "Already exist in the list");
        }

        System.out.println("Add product to list execution end!");
        System.out.println();
    }

}

package lv.javaguru.java2.console.views;

import lv.javaguru.java2.console.businesslogic.AddProductService;
import lv.javaguru.java2.console.businesslogic.api.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddProductView implements View {

    @Autowired private AddProductService addProductService;

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

        Response response = addProductService.addProduct(title, description);

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

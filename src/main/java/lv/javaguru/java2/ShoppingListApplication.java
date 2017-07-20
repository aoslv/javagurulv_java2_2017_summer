package lv.javaguru.java2;

import lv.javaguru.java2.businesslogic.AddProductValidator;
import lv.javaguru.java2.businesslogic.BusinessLogic;
import lv.javaguru.java2.businesslogic.BusinessLogicImpl;
import lv.javaguru.java2.views.AddProductView;
import lv.javaguru.java2.views.View;
import lv.javaguru.java2.views.PrintShoppingListView;
import lv.javaguru.java2.views.RemoveProductView;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.database.DatabaseImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShoppingListApplication {

    public static void main(String[] args) {
        Database database = new DatabaseImpl();
        AddProductValidator addProductValidator = new AddProductValidator(database);
        BusinessLogic businessLogic = new BusinessLogicImpl(database, addProductValidator);

        Map<Integer, View> commands = new HashMap<>();
        commands.put(1, new AddProductView(businessLogic));
        commands.put(2, new RemoveProductView(businessLogic));
        commands.put(3, new PrintShoppingListView(businessLogic));

        while (true) {
            printProgramMenu();
            int menuItem = getFromUserMenuItemToExecute();
            if (menuItem == 4) {
                break;
            } else {
                View view = commands.get(menuItem);
                view.execute();
            }
        }

    }

    private static void printProgramMenu() {
        System.out.println("Program Menu:");
        System.out.println("1. Add product to list");
        System.out.println("2. Remove product from list");
        System.out.println("3. Print list to console");
        System.out.println("4. Exit");
    }

    private static int getFromUserMenuItemToExecute() {
        System.out.print("Please enter menu item number to execute:");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }

}

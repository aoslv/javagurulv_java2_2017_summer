package lv.javaguru.java2;

import lv.javaguru.java2.businesslogic.AddProductService;
import lv.javaguru.java2.businesslogic.AddProductServiceImpl;
import lv.javaguru.java2.commands.AddProductCommand;
import lv.javaguru.java2.commands.PrintShoppingListCommand;
import lv.javaguru.java2.commands.RemoveProductCommand;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.database.ProductDAOImpl;
import lv.javaguru.java2.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ShoppingListApplication {

    public static void main(String[] args) {
        // Use cases:
        // 1. Add product to list
        // 2. Remove product from list
        // 3. Print shopping list to console
        // 4. Exit
        ProductDAO productDAO = new ProductDAOImpl();
        AddProductService addProductService = new AddProductServiceImpl(productDAO);

        Map<Integer, Command> commands = new HashMap<>();
        commands.put(1, new AddProductCommand(addProductService));
        commands.put(2, new RemoveProductCommand());
        commands.put(3, new PrintShoppingListCommand());

        List<Product> products = new ArrayList<>();
        while (true) {
            printProgramMenu();
            int menuItem = getFromUserMenuItemToExecute();
            if (menuItem == 4) {
                break;
            } else {
                Command command = commands.get(menuItem);
                command.execute(products);
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

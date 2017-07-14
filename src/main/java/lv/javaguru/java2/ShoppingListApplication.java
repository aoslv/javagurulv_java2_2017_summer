package lv.javaguru.java2;

import lv.javaguru.java2.businesslogic.BusinessLogic;
import lv.javaguru.java2.businesslogic.BusinessLogicImpl;
import lv.javaguru.java2.commands.AddProductCommand;
import lv.javaguru.java2.commands.Command;
import lv.javaguru.java2.commands.PrintShoppingListCommand;
import lv.javaguru.java2.commands.RemoveProductCommand;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.database.DatabaseImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShoppingListApplication {

    public static void main(String[] args) {
        Database database = new DatabaseImpl();
        BusinessLogic businessLogic = new BusinessLogicImpl(database);

        Map<Integer, Command> commands = new HashMap<>();
        commands.put(1, new AddProductCommand(businessLogic));
        commands.put(2, new RemoveProductCommand(businessLogic));
        commands.put(3, new PrintShoppingListCommand(businessLogic));

        while (true) {
            printProgramMenu();
            int menuItem = getFromUserMenuItemToExecute();
            if (menuItem == 4) {
                break;
            } else {
                Command command = commands.get(menuItem);
                command.execute();
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

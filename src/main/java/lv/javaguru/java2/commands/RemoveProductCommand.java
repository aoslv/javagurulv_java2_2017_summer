package lv.javaguru.java2.commands;

import lv.javaguru.java2.Command;
import lv.javaguru.java2.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RemoveProductCommand implements Command {
    @Override
    public void execute(List<Product> products) {
        System.out.println();
        System.out.println("Remove product from list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product title:");
        final String title = sc.nextLine();
        Optional<Product> foundProduct = products.stream()
                .filter(p -> p.getTitle().equals(title))
                .findFirst();
        if (foundProduct.isPresent()) {
            System.out.println("Product with title " + title + " was found and will be removed from list!");
            Product product = foundProduct.get();
            products.remove(product);
        } else {
            System.out.println("Product with title " + title + " not found and not be removed from list!");
        }
        System.out.println("Remove product from list execution end!");
        System.out.println();
    }
}

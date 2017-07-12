package lv.javaguru.java2;

import lv.javaguru.java2.domain.Product;

import java.util.List;

public interface Command {

    void execute(List<Product> products);

}

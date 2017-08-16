package lv.javaguru.java2.threads;

public class BankAccount {

    private int money = 0;

    public void add() {
        money++;
    }

    public void minus() {
        money--;
    }

    public int getMoney() {
        return money;
    }
}

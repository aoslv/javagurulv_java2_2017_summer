package lv.javaguru.java2.threads;

public class MinusAction implements Runnable {

    private BankAccount bankAccount;

    public MinusAction(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 10000; i++) {
            bankAccount.minus();
        }
    }
}


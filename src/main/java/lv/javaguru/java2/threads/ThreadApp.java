package lv.javaguru.java2.threads;

import java.util.ArrayList;
import java.util.List;

public class ThreadApp {

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        BankAccount bankAccount = new BankAccount();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Runnable add = new AddAction(bankAccount);
            threads.add(new Thread(add));
            Runnable minus = new MinusAction(bankAccount);
            threads.add(new Thread(minus));
        }

        threads.stream().forEach(t -> t.start());

        threads.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Money = " + bankAccount.getMoney());

        long endTime = System.nanoTime();
        System.out.println("Execution Time = " + (endTime - startTime));
    }

}

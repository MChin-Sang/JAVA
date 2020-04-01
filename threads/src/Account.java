import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Account {
    private Producer producer;
    private Consumer consumer;
    private List<String> balance;
    private boolean writeable = true;

    public Account(Producer p, Consumer c) {
        balance = new ArrayList<>(Arrays.asList("EMPTY"));
        producer = p;
        consumer = c;
    }

    public void deposit() throws InterruptedException {
        // Continue to deposit as long as producer has money in their account
        while (producer.hasMoreFunds()) {
            synchronized (this) {
                // Producer can deposit, 1 at a time, a single type of currency to the account and must not add a different
                // type of currency until all of the previous currency is withdrawn
                while (!writeable) {
                    System.out.println("*** " + Thread.currentThread().getName() + " is waiting for funds to be withdrawn ***\n");
                    wait();
                }

                if (balance.size() == 1 && balance.get(0).equals("EMPTY")) {
                    balance.remove(0);
                }
                if (producer.checkCurrency()) {
                    producer.deposit(balance);

                    System.out.println("\nDeposit By Producer:");
                    for (String s : balance)
                        System.out.println("[+ADDED] to account a: " + s);
                    System.out.println("\nProducer now has: ");
                    producer.display();
                    System.out.println();

                    writeable = false;
                    notify();
                }
            }
        }
    }

    public synchronized void withdraw() throws InterruptedException {
        while (producer.hasMoreFunds())
            synchronized (this) {
                while (writeable) {
                    System.out.println("\n*** " + Thread.currentThread().getName() + " is waiting for funds to be deposited ***");
                    wait();
                }

                System.out.println("Withdraw By Consumer:");
                for (String s : balance)
                    System.out.println("[-REMOVED] from account a: " + s);

                consumer.withdraw(balance);

                System.out.println("\nConsumer now has: ");
                consumer.display();

                // set writeable to true (consumer must wait) when there is no money in the account
                if (balance.size() == 0) {
                    writeable = true;
                    notify();
                }

            }
    }
}

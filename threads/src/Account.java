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
        while (producer.hasMoreFunds()) {
            synchronized (this) {
                while (!producer.checkCurrency() && balance.size() != 0) {
                    System.out.println("*** " + Thread.currentThread().getName() + " is waiting for funds to be withdrawn ***\n");
                    producer.setNewCurrency();
                    wait();
                }
                if (balance.size() == 1 && balance.get(0).equals("EMPTY")) {
                    balance.remove(0);
                }
                System.out.println("\nDeposit By Producer:\n[+ADDED] to account a: " + producer.deposit(balance));

                System.out.println("\nProducer now has: ");
                producer.display();
                System.out.println();

                notify();
            }
        }
    }

    public synchronized void withdraw() throws InterruptedException {
        while (producer.hasMoreFunds() || balance.size() != 0) {
            synchronized (this) {
                while (balance.size() == 0 || balance.get(0) == "EMPTY") {
                    System.out.println("\n*** " + Thread.currentThread().getName() + " is waiting for funds to be deposited ***");
                    wait();
                }

                System.out.println("\nWithdraw By Consumer:\n[-REMOVED] from account a: " + consumer.withdraw(balance));

                System.out.println("\nConsumer now has: ");
                consumer.display();
                System.out.println();

                notify();
            }
        }
    }
}

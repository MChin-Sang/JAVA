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
                while (!writeable) {
                    System.out.println("*** " + Thread.currentThread().getName() + " is waiting for funds to be withdrawn ***\n");
                    wait();
                }

                if (balance.size() == 1 && balance.get(0).equals("EMPTY")) {
                    balance.remove(0);
                }

                writeable = false;
                balance.addAll(producer.deposit());
                System.out.println();

                System.out.println("Deposit By Producer:");
                for (String s : balance)
                    System.out.println("[+ADDED] to account a: " + s);
                System.out.println("\nProducer now has: ");
                producer.display();
                System.out.println();
                notify();
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
                writeable = true;

                System.out.println("Withdraw By Consumer:");
                for (String s : balance)
                    System.out.println("[-REMOVED] from account a: " + s);
                consumer.withdraw(balance);
                System.out.println("\nConsumer now has: ");
                consumer.display();
                notify();
            }
    }
}

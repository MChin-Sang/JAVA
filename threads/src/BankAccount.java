import java.util.List;

public class BankAccount {

    public static void main(String [] args) throws InterruptedException {
        String [] funds = {"Dollar", "Euro", "Euro", "Pound", "Pound", "Pound"};

        Producer p = new Producer(funds);
        Consumer c = new Consumer();
        Account account = new Account(p, c);

        System.out.println("Beginning balance of Producer is: ");
        p.display();

        Thread t1 = new Thread(()-> {
            try {
                account.deposit();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Depositer");

        Thread t2 = new Thread(()-> {
            try {
                account.withdraw();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Withdrawer");
        t2.start();
        t1.start();

    }
}

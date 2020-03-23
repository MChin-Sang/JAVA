public class BankAccount {
    private double balance;
    private boolean isGood;

    /**
     * Sets the balance of the account and depending on the value of the account isGood is set
     * isGood is used for telling the account users (threads) if they can change (set) the account balance
     * Note that the setBalance function is synchronized to only allow 1 thread at a time to change the balance
     *
     * If the account is 0 we want the producer to put some money into the account and the consumer to wait
     * Once money has been added to the account the producer cannot add anymore money and must wait until the money is taken out
     */
    public BankAccount(double balance) {
        if (balance >= 0.0) {
            this.balance = balance;
        }
        if (this.balance == 0.0) {
            this.isGood = true;
        } else {
            this.isGood = false;
        }
    }

    public static void main(String [] args) throws InterruptedException {
        BankAccount account = new BankAccount(0.0);

        Producer producer = new Producer(account.getIsGood());
        Thread t1 = new Thread(()-> {
            try {
                account.setBalance(producer.produce());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Depositer");

        Consumer consumer = new Consumer(account.getIsGood());
        Thread t2 = new Thread(()-> {
            try {
                account.setBalance(consumer.consume());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Withdrawer");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    public double getBalance() {
        return balance;
    }
    public boolean getIsGood() {
        return isGood;
    }

    // Only 1 thread can call setBalance at a time
    public synchronized void setBalance(double balance) {
        this.balance = balance;
    }
    public void setIsGood(boolean isGood) {
        this.isGood = isGood;
    }
}

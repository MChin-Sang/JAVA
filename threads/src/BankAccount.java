//import java.util.List;
//
//public class BankAccount {
//    private List<String> sharedAccount;
//    private boolean writeable;
//
//    private boolean allSameCurrency() {
//        return sharedAccount.stream()
//                .allMatch(sharedAccount.get(0)::equals);
//    }
//
//    public synchronized void deposit(String currencyType) throws InterruptedException {
//        if (sharedAccount.size() == 0) {
//            while (writeable) {
//                try {
//                    System.out.println("Waiting for friend to take money out of account...");
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public static void main(String [] args) throws InterruptedException {
//        BankAccount account = new BankAccount(0.0);
//
//        Producer producer = new Producer(account.getIsGood());
//        Thread t1 = new Thread(()-> {
//            try {
//                account.setBalance(producer.produce());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, "Depositer");
//
//        Consumer consumer = new Consumer(account.getIsGood());
//        Thread t2 = new Thread(()-> {
//            try {
//                account.setBalance(consumer.consume());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, "Withdrawer");
//
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();
//    }
//}

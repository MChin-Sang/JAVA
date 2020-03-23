public class Consumer {
    private boolean isGood;

    public Consumer(boolean isGood) {
        this.isGood = isGood;
    }

    public boolean getIsGood() {
        return this.isGood;
    }

    public void setIsGood(boolean isGood) {
        this.isGood = isGood;
    }
    public double consume() throws InterruptedException {
        // while account is equal to zero meaning current state of isGood is false
        while (isGood == true) {
            System.out.println(Thread.currentThread().getName() + " is waiting for money to be deposited");
            Thread.currentThread().wait();
        }
        setIsGood(true);
    }
}

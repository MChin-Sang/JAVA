import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Consumer {
    private List<String> fund = new ArrayList<>();

    public Consumer() { }

    public void withdraw(List<String> moneyFromAccount) {
        fund.addAll(moneyFromAccount);
    }

    public void display() {
        fund.stream().forEach(System.out::println);
    }

    public static void main(String[] args) {
        String [] funds = {"Dollar", "Euro"};
        Consumer c = new Consumer();
        c.withdraw(Arrays.asList(funds));
        String [] funds2 = {"Blah", "Blah"};
        c.withdraw((Arrays.asList(funds2)));
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Consumer {
    private List<String> fund = new ArrayList<>();

    public Consumer() { }

    public List<String> withdraw(List<String> moneyFromAccount) {
        Iterator<String> itr = moneyFromAccount.iterator();

        while (itr.hasNext()) {
            String i = itr.next();
            fund.add(i);
            itr.remove();
        }

        return moneyFromAccount;
    }

    public void display() {
        fund.stream().forEach(s -> System.out.println("-->" + s));
    }

    public static void main(String[] args) {
        String [] funds = {"Dollar", "Euro"};
        Consumer c = new Consumer();
        c.withdraw(Arrays.asList(funds));
        String [] funds2 = {"Blah", "Blah"};
        c.withdraw((Arrays.asList(funds2)));
    }
}

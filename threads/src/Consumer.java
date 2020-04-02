import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Consumer {
    private List<String> fund = new ArrayList<>();

    public Consumer() { }

    public String withdraw(List<String> moneyFromAccount) {
        String temp = "";
        Iterator<String> itr = moneyFromAccount.iterator();

        if (itr.hasNext()) {
            String i = itr.next();
            fund.add(i);
            temp = i;
            itr.remove();
        }
        return temp;
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

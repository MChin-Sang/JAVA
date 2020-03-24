import java.util.*;

/**
 * 1 - boolean hasNext(): It returns true if Iterator has more element to iterate.
 * 2 - Object next(): It returns the next element in the collection until the hasNext()method return true. This method throws ‘NoSuchElementException’ if there is no next element.
 * 3 - void remove(): It removes the current element in the collection. This method throws ‘IllegalStateException’ if this function is called before next( ) is invoked.
 */

public class Producer {
    private List<String> funds;

    public Producer(String [] funds) {
        this.funds = new ArrayList<String> (Arrays.asList(funds));
    }

    public List<String> deposite() {
       String compare = funds.get(0);
       List<String> sendList = new ArrayList<>();

       Iterator<String> itr = funds.iterator();

       while (itr.hasNext()) {
            String s = (String) itr.next();
            if (s.equals(compare)) {
                sendList.add(s);
                itr.remove();
            }
       }

       return sendList;
    }

    public void display() {
        funds.stream().forEach(System.out::println);
    }

    public static void main(String[] args) {
        String [] funds = {"Dollar", "Euro", "Euro", "Pound", "Pound", "Pound"};
        Producer p = new Producer(funds);
        List<String> newFunds = p.deposite();
        for (String s : newFunds)
            System.out.println(s);
        System.out.println("\n");
        p.display();
    }
}
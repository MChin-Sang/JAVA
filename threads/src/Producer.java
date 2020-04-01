import java.util.*;

/**
 * 1 - boolean hasNext(): It returns true if Iterator has more element to iterate.
 * 2 - Object next(): It returns the next element in the collection until the hasNext()method return true. This method throws ‘NoSuchElementException’ if there is no next element.
 * 3 - void remove(): It removes the current element in the collection. This method throws ‘IllegalStateException’ if this function is called before next( ) is invoked.
 */

public class Producer {
    private List<String> funds;
    private final String EMPTY_STATE = "NO FUNDS";
    private String compareCurrency;

    public Producer(String[] funds) {
        if (funds.length == 0) {
            this.funds = new ArrayList<>(Arrays.asList(EMPTY_STATE));
        } else {
            this.funds = new ArrayList<String>(Arrays.asList(funds));
            this.compareCurrency = this.funds.get(0);
        }
    }

    // return true if there is more of the last deposited currency type
    public boolean checkCurrency() {
        boolean found = false;
        for (int i = 0; i < funds.size() && found == false; i++) {
            if (funds.get(i).equals(compareCurrency)) {
                found = true;
            }
        }
        return found;
    }

    public void deposit(List<String> balance) {
        if (funds.isEmpty()) {
            funds.add(EMPTY_STATE);
        }
        if (!funds.get(0).equals(EMPTY_STATE)) {
            boolean found = false;
            Iterator<String> itr = funds.iterator();

            while (itr.hasNext() && !found) {
                String s = (String) itr.next();
                if (s.equals(compareCurrency)) {
                    balance.add(s);
                    itr.remove();
                    found = true;
                    if (itr.hasNext()) {
                        compareCurrency = itr.next();
                    } else {
                        compareCurrency = s;
                    }
                }
            }
            if (funds.isEmpty()) {
                funds.add(EMPTY_STATE);
            }
        }
    }
//        if (funds.isEmpty()) {
//            funds.add(EMPTY_STATE);
//        }
//        if (!funds.get(0).equals(EMPTY_STATE)) {
//            String compare = funds.get(0);
//            lastCurrency = compare;
//
//            List<String> sendList = new ArrayList<>();
//
//            Iterator<String> itr = funds.iterator();
//
//            while (itr.hasNext()) {
//                String s = (String) itr.next();
//                if (s.equals(compare)) {
//                    sendList.add(s);
//                    itr.remove();
//                }
//            }
//            if (funds.isEmpty()) {
//                funds.add(EMPTY_STATE);
//            }
//            return sendList;
//        }
//        return funds;


    public void display() {
        if (!hasMoreFunds()) {
            System.out.println("--> " + EMPTY_STATE + " <--");
        } else {
            funds.stream().forEach(s -> System.out.println("-->" + s));
        }
    }

    public boolean hasMoreFunds() {
        return !funds.get(0).equals(EMPTY_STATE);
        //return funds.size() >= 1;
    }

    public static void main(String[] args) {
        String[] funds = {"Dollar", "Euro", "Euro", "Pound", "Pound", "Pound"};
        Producer p = new Producer(funds);
    }
}

import java.util.*;

public class MapTest {
    public static void main(String [] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(100, "Michael");
        map.put(101, "Eric");
        map.put(102, "Albert");


        Set set = map.entrySet();
        Iterator itr = set.iterator();

        System.out.println("\n***Using Iterator***");
        while(itr.hasNext()) {
            Map.Entry i = (Map.Entry)itr.next();
            System.out.println(i.getKey()+" "+i.getValue() );
        }

        System.out.println("\n***Using Enhanced for loop***");
        for (Map.Entry m : map.entrySet()) {
            System.out.println(m.getKey() + ":" + m.getValue());
        }

        System.out.println("\n***Using Stream***");
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);
    }
}

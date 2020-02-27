import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
    public int id;
    public String name;
    public ArrayList<String> courses;

    public Student(int id, String name, ArrayList<String> courses) {
        if (id >= 0 && courses.size() > 0) {
            this.id = id;
            this.courses = courses;
            this.name = name;
        }
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public ArrayList<String> getCourses() {return courses;}

    public void setName(String name) { this.name = name; }
    public void setId(int id) { if (id > 0) this.id = id; }

    public static void main(String[] args) {
        ArrayList<Integer> obj = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();

        temp.add(1);
        temp.add(2);

        obj = (ArrayList<Integer>)temp.clone();
        obj.set(0, 10);

        //temp.clear();

        for (int x : obj) {
            System.out.println(x);
        }
    }
}

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Read from a text file where a student object has been serialized.
 * Output to the screen the student object which was created from the serialized object
 */

public class Deserializer {
    public Deserializer() {
        Student student = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("SerializedObj.txt"));
            student = (Student) ois.readObject();
            ois.close();

            System.out.printf("Processed Student:\n\tStudent ID: %d\n\tStudent Name: %s", student.getId(), student.getName());
            System.out.println("\n\tCourses:");
            int index = 1;
            for (String course : student.getCourses()) {
                System.out.println("\t\t" + (index++) + ". " + course);
            }
        } catch (IOException io) {
            System.out.println("Employee class not found");
            io.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        Deserializer d1 = new Deserializer();
    }
}

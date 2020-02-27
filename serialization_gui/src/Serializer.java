import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Take input from System.in and create a Student object.
 * In order to process an int a line was read and casted to an int to receive the 'enter' pressed by the user.
 * If no exceptions are thrown then the Student is serialized into a text file and a success message is displayed
 */

public class Serializer {
    Serializer() {
        int id;
        String name;
        ArrayList<String> courses = new ArrayList<>();
        Student tempStud;

        Scanner s = new Scanner(System.in);

        System.out.println("Student ID: ");
        id = Integer.parseInt(s.nextLine());
        System.out.println("Name: ");
        name = s.nextLine();
        System.out.println("How many courses are you taking?");
        int i = Integer.parseInt(s.nextLine());
        System.out.println("Please enter course below:");
        for (int j = 0; j < i; j++) {
            System.out.println((j+1) + ". ");
            String temp = s.nextLine();
            courses.add(temp);
        }

        tempStud = new Student(id,name,courses);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("SerializedObj.txt"));
            oos.writeObject(tempStud);
            oos.flush();
            oos.close();

            System.out.printf("Student Saved");
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Serializer s1 = new Serializer();
    }
}

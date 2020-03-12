import java.util.*;
import java.util.stream.Collectors;

/**
 * We assume that there has been a contest among students of different departments and the results
 * have been gathered as grades
 *
 * Use of streams which are: Sequences of elements which can be easily obtained from a Collection
 */
public class StudentProcess {
    public static Student [] studentArray = {
            new Student("Jack", "Smith", 50.0, "IT"),
            new Student("Aaron", "Johnson", 76.0, "IT"),
            new Student("Maaria", "White", 35.8, "Business"),
            new Student("John", "White", 47.0, "Media"),
            new Student("Laney", "White", 62.0, "IT"),
            new Student("Jack", "Jones", 32.9, "Business"),
            new Student("Wesley", "Jones", 42.89, "Media")
    };
    public static void displayTask(int task) {
        System.out.println("******* Task " +task+" *******");
    }
    public static void main(String [] args) {
        /*
         * Task 1: Create an array of Students, populate it with
         * some Students, make a list out of your array, and print all its elements
         */
        displayTask(1);
        List<Student> studentList = Arrays.asList(studentArray);
        studentList
                .stream()
                .forEach(System.out::println);
        /*
         * Task 2: Display Students with grades in the range 50.0-100.0, sorted into ascending order by
         * grade. (hint: you need to return a Stream<Student> out of your List<Student> first, and then use
         * Stream and Comparator classes’ methods classes’ methods.)
         */
        displayTask(2);
        studentList
                .stream()
                .filter((Student s)-> s.getGrade() >= 50.0 && s.getGrade() <= 100.00)
                //.sorted((s1, s2)->Double.compare(s1.getGrade(), s2.getGrade()))
                .sorted(Comparator.comparingDouble(Student::getGrade))
                .forEach(System.out::println);
        /*
         * Task 3: Display the first student in the collection with grade in the range 50.0-100.0
         *
         * Class Optional<T> : A container object which may or may not contain a non-null value
         */
        displayTask(3);
        studentList
                .stream()
                .filter((Student s)-> s.getGrade() >= 50.0 && s.getGrade() <= 100.00)
                .findFirst() //Returns an Optional describing the first element of this stream, or an empty Optional if the stream is empty.
                .ifPresent(System.out::println); //If a value is present, invoke the specified consumer with the value, otherwise do nothing
        /*
         * Task 4: Sort the Students (a)by their last names, and then their first names in ascending and
         * (b)by their last names, and then their first names in descending orders and display the students
         * after each of these two processes.
         */
        displayTask(4);
        System.out.println("Students in ascending order by last name then first:");
        studentList
                .stream()
                .sorted(Comparator.comparing(Student::getFirstName))
                .sorted((s1, s2) -> s1.getLastName().compareTo(s2.getLastName()))
                .forEach(System.out::println);
        System.out.println("\nStudents in descending order by last name then first:");
        studentList
                .stream()
                .sorted((s1, s2) -> s2.getFirstName().compareTo(s1.getFirstName()))
                .sorted((s1, s2) -> s2.getLastName().compareTo(s1.getLastName()))
                .forEach(System.out::println);
        /*
         * Task 5: Display unique Student last names, sorted
         */
        displayTask(5);
        studentList
                .stream()
              //.map(Student::getLastName()) could use this instead of below
                .map(s -> s.getLastName()) // get the stream and map it to a Stream<String>
                .distinct()
                .sorted()
                .forEach(System.out::println);
        /*
         * Task 6: Display Student full names, sorted in order by last name then first name.
         */
        displayTask(6);
        studentList
                .stream()
                .sorted(Comparator.comparing(Student::getFirstName))
                .sorted(Comparator.comparing(Student::getLastName))
                .map(Student::getName)
                .forEach(System.out::println);
        /*
         * Task 7: Display Students, grouped by their departments.
         */
        displayTask(7);
        Map<String, List<Student>> studentsByDepartment = studentList
                .stream()
                .collect(Collectors.groupingBy(Student::getDepartment));
        System.out.println(studentsByDepartment);
        /*
         * Task 8: Count and display the number of Students in each department.
         */
        displayTask(8);
        Map<String, Long> temp = studentList
                .stream()
                .collect(Collectors.groupingBy(Student::getDepartment, Collectors.counting()));
        System.out.println(temp);
        /*
         * Task 9: Calculate and display the sum of all Students’ grades.
         */
        displayTask(9);
        double totalGrade = studentList
                .stream()
                .mapToDouble(Student::getGrade)
                .sum();
        System.out.println("Total grades for all students is " + totalGrade);
        /*
         * Task 10: Average of Students' grades: 49.51
         */
        displayTask(10);
        double averageGrade = studentList
                .stream()
                .mapToDouble(Student::getGrade)
                .average()
                .orElse(-1); // allows for return of double as long as there are values to average
        System.out.printf("Average grades for all students is %.2f", averageGrade);

    }
}

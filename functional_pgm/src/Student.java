import java.util.Objects;

public class Student {
    private String firstName;
    private String lastName;
    private double grade;
    private String department;

    public Student(String firstName, String lastName, double grade, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.department = department;
    }
    public String getName() {
        String name = getFirstName() + " " + getLastName();
        return name;
    }

    /*
     * The default toString() method in Object prints 'class name @ hash code'
     *
     * Therefore, if we want to print out our class object a specific way we
     * must override the toString() method which comes from the Object class
     */
    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", grade=" + grade +
                ", department='" + department + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (this == o) return true;
        /* Check if o is an instance of Student or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Student)) return false;
        // typecast o to Complex so that we can compare data members
        Student student = (Student) o;
        // Compare the data members and return accordingly
        return Double.compare(student.getGrade(), getGrade()) == 0 &&
                getFirstName().equals(student.getFirstName()) &&
                getLastName().equals(student.getLastName()) &&
                getDepartment().equals(student.getDepartment());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getGrade(), getDepartment());
    }

    /**
     * Getter and Setters
     */
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public double getGrade() {return grade;}
    public String getDepartment() {return department;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setGrade(double grade) {this.grade = grade;}
    public void setDepartment(String department) {this.department = department;}
}


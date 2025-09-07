import java.time.format.DateTimeFormatter;
import java.io.*;
import java.time.LocalDate;

public class Enrollment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    Student student;
    Course course;
    LocalDate enrollmentDate;

    public Enrollment(Student student, Course course){
        this.student = student;
        this.course = course;
        this.enrollmentDate = LocalDate.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = enrollmentDate.format(formatter);
        return "Enrollment List:" +
                "\n" + student +
                "\n" + course +
                "\nEnrollment Date: " + formattedDate;
    }

}

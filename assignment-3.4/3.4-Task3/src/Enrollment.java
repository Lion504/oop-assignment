import java.io.*;

public class Enrollment implements Serializable {
    private static final long serialVersionID = 1L;
    Student student;
    Course course;

    public Enrollment(Student student, Course course){
        this.student = student;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "student=" + student +
                ", course=" + course +
                '}';
    }

}

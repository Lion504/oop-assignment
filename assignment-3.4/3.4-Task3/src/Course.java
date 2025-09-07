import java.io.*;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    int CourseId;
    String CourseTitle;
    String instructor;

    public Course (int CourseId, String CourseTitle, String instructor) {
        this.CourseId = CourseId;
        this.CourseTitle = CourseTitle;
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "CourseId=" + CourseId +
                ", CourseTitle='" + CourseTitle + '\'' +
                ", instructor='" + instructor + '\'' +
                '}';
    }

}

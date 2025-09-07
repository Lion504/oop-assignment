import java.io.*;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    int CourseId;
    String CourseTitle;

    public Course (int CourseId, String CourseTitle) {
        this.CourseId = CourseId;
        this.CourseTitle = CourseTitle;
    }

    @Override
    public String toString() {
        return "Course{" +
                "CourseId=" + CourseId +
                ", CourseTitle='" + CourseTitle + '\'' +
                '}';
    }

}

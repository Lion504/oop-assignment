import java.io.*;

public class Main {
    public static void main(String[] args) {
        // initial objects
        Student student1 = new Student(1, "Jimi", 20);
        Course course1 = new Course(102, "Java Programming");
        Enrollment enrollment1 = new Enrollment(student1, course1);

        //serialize the enrollment objects
        try {
            FileOutputStream fileOut = new FileOutputStream("enrollment.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(enrollment1);
            out.close();
            fileOut.close();
            System.out.println("Serialized enrollment object is saved in ./enrollment.ser \n");
        } catch (IOException i) {
            System.out.println("IOException is caught");
        }

        // deserialize the student objects
        Enrollment deserializedEnroll = null;
        try {
            FileInputStream fileIn = new FileInputStream("enrollment.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            deserializedEnroll = (Enrollment) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }

        if (deserializedEnroll != null) {
            System.out.println("Enrollment object is deserialized: \n" + deserializedEnroll);
        } else {
            System.out.println("Deserialization failed");
        }
    }
}


public class Task4 {
    private String name;

    public Task4(String name) {
        this.name = name;
    }

    public void meow() {
        System.out.println("The Task4 named " + name + " says: Meow!");
    }

    public static void main(String[] args) {
        // Create an instance of the Task4 class with a name
        Task4 Task4_1 = new Task4("Whiskers");
        Task4 Task4_2 = new Task4("Whiskers");
        Task4 Task4_3 = new Task4("Rex");
        Task4 Task4_4 = new Task4("Whiskers");
        // Call the meow method on the Task4 instance
        Task4_1.meow();
        Task4_2.meow();
        Task4_3.meow();
        Task4_4.meow();
    }
}
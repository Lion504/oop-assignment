import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Task 1: Sorting and Filtering using Lambda Expressions
 *
 * This program demonstrates:
 * 1. Creating a Person class with name, age, and city
 * 2. Sorting Person objects by age using lambda expressions
 * 3. Filtering Person objects by city using lambda expressions
 */
public class lambdaMain {

    /**
     * Helper method: Create a list of Person objects for testing
     */
    private static List<Person> createPeopleList() {
        List<Person> people = new ArrayList<>();

        people.add(new Person("Sophia", 29, "Seattle"));
        people.add(new Person("Liam", 24, "Boston"));
        people.add(new Person("Olivia", 31, "Seattle"));
        people.add(new Person("Noah", 26, "Miami"));
        people.add(new Person("Ava", 33, "Seattle"));
        people.add(new Person("Ethan", 21, "Boston"));
        people.add(new Person("Isabella", 28, "Miami"));
        people.add(new Person("Mason", 30, "Seattle"));

        return people;
    }

    /**
     * Helper method: Print all people in the list
     */
    private static void printPeopleList(List<Person> people) {
        for (Person person : people) {
            System.out.println(person);
        }
    }

    /**
     * Main method - Entry point of the program
     */
    public static void main(String[] args) {
        // Display original list
        System.out.println("===== ORIGINAL LIST =====");
        List<Person> people = createPeopleList();
        printPeopleList(people);

        // Example 1: Sort by name (alphabetically)
        System.out.println("\n1️⃣ Sorted by Name (Alphabetically):");
        people = createPeopleList(); // Create fresh list
        people.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        printPeopleList(people);

        // Example 2: Sort by age in ascending order
        System.out.println("\n2️⃣ Sorted by Age (Ascending):");
        people = createPeopleList(); // Create fresh list
        people.sort((person1, person2) -> person1.getAge() - person2.getAge());
        printPeopleList(people);
        // Alternative way: people.sort(Comparator.comparingInt(Person::getAge));

        // Example 3: Sort by age in descending order
        System.out.println("\n3️⃣ Sorted by Age (Descending):");
        people = createPeopleList(); // Create fresh list
        people.sort((p1, p2) -> p2.getAge() - p1.getAge());
        printPeopleList(people);

        // Example 4: Sort by city, then by age
        System.out.println("\n4️⃣ Sorted by City, then by Age:");
        people = createPeopleList(); // Create fresh list
        people.sort(Comparator.comparing(Person::getCity)
                              .thenComparingInt(Person::getAge));
        printPeopleList(people);
    }
}

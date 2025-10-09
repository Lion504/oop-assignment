/**
 * Person class representing a person with name, age, and city
 *
 * This class is used to store information about a person including:
 * - name: The person's name
 * - age: The person's age
 * - city: The city where the person lives
 */
public class Person {
    // Fields (private for encapsulation)
    private String name;
    private int age;
    private String city;

    /**
     * Constructor: Creates a new Person with given name, age, and city
     *
     * @param name The person's name
     * @param age The person's age
     * @param city The city where the person lives
     */
    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    /**
     * Getter for name
     *
     * @return The person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for age
     *
     * @return The person's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Getter for city
     *
     * @return The city where the person lives
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter for name
     *
     * @param name The new name for the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for age
     *
     * @param age The new age for the person
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Setter for city
     *
     * @param city The new city for the person
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * toString method for easy printing
     * Formats the person's information in a nice readable format
     *
     * @return A formatted string with person's name, age, and city
     */
    @Override
    public String toString() {
        return String.format("👤 %-10s | Age: %2d | City: %-15s", name, age, city);
    }
}


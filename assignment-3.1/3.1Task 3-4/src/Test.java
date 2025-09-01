
public class Test {
    public static void main(String[] args) {
        System.out.println("=== SHAPE HIERARCHY TEST PROGRAM ===\n");
        System.out.println("--- Basic Shape Testing ---");
        ShapeCalculator.main(new String[]{});

        Shape[] shapes = {
                new Shape.Circle(5.0, "Purple"),
                new Shape.Rectangle(4.0, 6.0, "Blue"),
                new Shape.Triangle(3.0, 8.0, "Green"),
        };

        // Demonstrate polymorphism
        System.out.println("\n--- Polymorphism Demonstration ---");

        ShapeCalculator.demonstratePolymorphism(shapes);

        // Display color statistics
        System.out.println("\n--- Color Statistics ---");
        ShapeCalculator.displayColorStatistics(shapes);
    }

}

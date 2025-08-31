
public class Test {
    public static void main(String[] args) {
        System.out.println("=== SHAPE HIERARCHY TEST PROGRAM ===\n");

        testBasicShapes();
        testPolymorphism();
        testColorFunctionality();
    }

    private static void testBasicShapes() {
        System.out.println("--- Basic Shape Testing ---");

        Circle circle = new Circle(4.0);
        Shape.Rectangle rectangle = new Shape.Rectangle(5.0, 3.0, "Blue");
        Shape.Triangle triangle = new Shape.Triangle(6.0, 4.0, "Green");

        System.out.println(circle + " -> Area: " + circle.calculateArea());
        System.out.println(rectangle + " -> Area: " + rectangle.calculateArea());
        System.out.println(triangle + " -> Area: " + triangle.calculateArea());
        System.out.println();
    }

    private static void testPolymorphism() {
        System.out.println("--- Polymorphism Testing ---");

        // Array of Shape references holding different object types
        Shape[] shapes = {
                new Circle(2.5),
                new Shape.Rectangle(3.0, 4.0, "Violet"),
                new Shape.Triangle(5.0, 6.0, "Pink")
        };

        // Polymorphic method calls
        for (int i = 0; i < shapes.length; i++) {
            Shape shape = shapes[i];
            System.out.printf("Shape %d: %s%n", i + 1, shape);
            System.out.printf("   Area: %.3f%n", shape.calculateArea());
            System.out.printf("   Color: %s%n", shape.getColor());
        }
        System.out.println();
    }

    private static void testColorFunctionality() {
        System.out.println("--- Color Functionality Testing ---");

        Circle circle = new Circle(3.0);
        System.out.println("Circle without color: " + circle);

        circle.setColor("Cyan");
        System.out.println("Circle after setting color: " + circle);

        Shape.Rectangle rect = new Shape.Rectangle(2.0, 3.0, null);
        System.out.println("Rectangle with null color: " + rect);
        System.out.println();
    }

}

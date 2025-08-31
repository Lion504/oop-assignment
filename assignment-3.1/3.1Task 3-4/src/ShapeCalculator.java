
public class ShapeCalculator {
    public static class EnhancedShapeCalculator {
        public static void main(String[] args) {
            // Create array of Shape objects with colors
            Shape[] shapes = {
                    new Circle(5.0),
                    new Shape.Rectangle(4.0, 6.0, "Blue"),
                    new Shape.Triangle(3.0, 8.0, "Green"),
                    new Circle(3.5),
                    new Shape.Rectangle(2.5, 7.0, "Purple")
            };

            System.out.println("Enhanced Shape Calculator with Colors\n");

            // Display each shape with its color and area
            for (Shape shape : shapes) {
                double area = shape.calculateArea();
                System.out.printf("Area of %s: %.2f%n", shape.toString(), area);
            }

            // Demonstrate polymorphism by calling methods on Shape references
            System.out.println("\n--- Polymorphism Demonstration ---");
            demonstratePolymorphism(shapes);

            // Display color statistics
            System.out.println("\n--- Color Statistics ---");
            displayColorStatistics(shapes);
        }

        // Method demonstrating polymorphism
        private static void demonstratePolymorphism(Shape[] shapes) {
            double totalArea = 0;

            for (Shape shape : shapes) {
                // The correct calculateArea() method is called based on the actual object type
                double area = shape.calculateArea();
                totalArea += area;

                // Display shape type using instanceof
                String shapeType = "";
                if (shape instanceof Circle) {
                    shapeType = "Circle";
                } else if (shape instanceof Shape.Rectangle) {
                    shapeType = "Rectangle";
                } else if (shape instanceof Shape.Triangle) {
                    shapeType = "Triangle";
                }

                System.out.printf("%s (%s): Area = %.2f%n", shapeType, shape.getColor(), area);
            }

            System.out.printf("Total area of all shapes: %.2f%n", totalArea);
        }

        // Method to display color statistics
        private static void displayColorStatistics(Shape[] shapes) {
            java.util.Map<String, Integer> colorCount = new java.util.HashMap<>();
            java.util.Map<String, Double> colorTotalArea = new java.util.HashMap<>();

            for (Shape shape : shapes) {
                String color = shape.getColor();
                double area = shape.calculateArea();

                colorCount.put(color, colorCount.getOrDefault(color, 0) + 1);
                colorTotalArea.put(color, colorTotalArea.getOrDefault(color, 0.0) + area);
            }

            System.out.println("Color distribution:");
            for (String color : colorCount.keySet()) {
                System.out.printf("%s: %d shape(s), Total area: %.2f%n",
                        color, colorCount.get(color), colorTotalArea.get(color));
            }
        }
    }

}

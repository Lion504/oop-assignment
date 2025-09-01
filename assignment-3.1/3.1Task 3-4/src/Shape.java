public class Shape {
    protected String color;

    public Shape() {
        this.color = "Unknown";
    }

    public Shape(String color) {
        this.color = color != null ? color : "Unknown";
    }

    // Method to calculate area default 0
    public double calculateArea() {
        return 0;
    }

    // Getter and setter for color
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color != null ? color : "Unknown";
    }

    @Override
    public String toString() {
        return "Shape with color " + color;
    }

    public static class Circle extends Shape {
        private final double radius;

        public Circle(double radius, String color) {
            super(color);
            if (radius <= 0) {
                throw new IllegalArgumentException("Radius must be positive");
            }
            this.radius = radius;
        }

        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }
        public double getRadius() {
            return radius;
        }
        @Override
        public String toString() {
            return color + " Circle with radius " + radius;
        }
    }


    public static class Rectangle extends Shape {
        private double width;
        private double height;

        public Rectangle(double width, double height, String color) {
            super(color);
            if (width <= 0 || height <= 0) {
                throw new IllegalArgumentException("Width and height must be positive");
            }
            this.width = width;
            this.height = height;
        }

        @Override
        public double calculateArea() {
            return width * height;
        }

        @Override
        public String toString() {
            return color + " Rectangle with width " + width + " and height " + height;
        }
    }


    public static class Triangle extends Shape {
        private final double base;
        private final double height;

        public Triangle(double base, double height, String color) {
            super(color);
            if (base <= 0 || height <= 0) {
                throw new IllegalArgumentException("Base and height must be positive");
            }
            this.base = base;
            this.height = height;
        }

        @Override
        public double calculateArea() {
            return 0.5 * base * height;
        }

        @Override
        public String toString() {
            return color + " Triangle with base " + base + " and height " + height;
        }
    }


}


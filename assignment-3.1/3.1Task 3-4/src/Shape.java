public class Shape {
    protected String color;

    public Shape() {
        this.color = "Unknown";
    }

    public Shape(String color) {
        this.color = color != null ? color : "Unknown";
    }

    // Method to calculate area - default implementation returns 0
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

    public class Circle extends Shape {
        private double radius;

        public Circle(double radius) {
            super();
            if (radius <= 0) {
                throw new IllegalArgumentException("Radius must be positive");
            }
            this.radius = radius;
        }

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

        public Rectangle(double width, double height) {
            super();
            if (width <= 0 || height <= 0) {
                throw new IllegalArgumentException("Width and height must be positive");
            }
            this.width = width;
            this.height = height;
        }

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

        public double getWidth() {
            return width;
        }

        public double getHeight() {
            return height;
        }

        @Override
        public String toString() {
            return color + " Rectangle with width " + width + " and height " + height;
        }
    }


    public static class Triangle extends Shape {
        private final double base;
        private final double height;

        public Triangle(double base, double height) {
            super();
            if (base <= 0 || height <= 0) {
                throw new IllegalArgumentException("Base and height must be positive");
            }
            this.base = base;
            this.height = height;
        }

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

        public double getBase() {
            return base;
        }

        public double getHeight() {
            return height;
        }

        @Override
        public String toString() {
            return color + " Triangle with base " + base + " and height " + height;
        }
    }


}


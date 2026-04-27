public class QuantityMeasurementApp {

    // Feet Class (From UC1)
    public static class Feet {
        private final double value;
        public Feet(double value) { this.value = value; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Feet feet = (Feet) obj;
            return Double.compare(feet.value, this.value) == 0;
        }
    }

    // Step 1: Separate Inches class (Similar to Feet)
    public static class Inches {
        private final double value;
        public Inches(double value) { this.value = value; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Inches inches = (Inches) obj;
            return Double.compare(inches.value, this.value) == 0;
        }
    }

    // Step 3: Static methods to reduce dependency on main
    public static boolean compareFeet(double val1, double val2) {
        return new Feet(val1).equals(new Feet(val2));
    }

    public static boolean compareInches(double val1, double val2) {
        return new Inches(val1).equals(new Inches(val2));
    }

    public static void main(String[] args) {
        System.out.println("Input: 1.0 inch and 1.0 inch");
        System.out.println("Output: Equal (" + compareInches(1.0, 1.0) + ")");

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + compareFeet(1.0, 1.0) + ")");
    }
}

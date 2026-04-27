public class QuantityMeasurementApp {

    // Step 1: Create a LengthUnit Enum with conversion factors
    public enum LengthUnit {
        FEET(12.0),   // Base unit: 1 Foot = 12 Inches
        INCHES(1.0);  // Base unit: 1 Inch = 1 Inch

        public final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }
    }

    // Step 2: Generic Quantity Length Class
    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength that = (QuantityLength) obj;

            // Convert both to a common base unit (Inches) for comparison
            double value1 = this.value * this.unit.conversionFactor;
            double value2 = that.value * that.unit.conversionFactor;

            return Double.compare(value1, value2) == 0;
        }
    }

    public static void main(String[] args) {
        QuantityLength oneFeet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength twelveInches = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println("Input: 1.0 feet and 12.0 inches");
        System.out.println("Output: Equal (" + oneFeet.equals(twelveInches) + ")");
    }
}
public class QuantityMeasurementApp {

    public enum LengthUnit {
        INCHES(1.0),
        FEET(12.0),
        YARDS(36.0),
        CENTIMETERS(0.393701); // 1 cm = 0.393701 inches

        public final double conversionFactor;
        LengthUnit(double conversionFactor) { this.conversionFactor = conversionFactor; }
    }

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be a finite number");
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            this.value = value;
            this.unit = unit;
        }

        // Instance method for conversion (Returns a NEW object - Immutability)
        public QuantityLength convertTo(LengthUnit targetUnit) {
            double convertedValue = (this.value * this.unit.conversionFactor) / targetUnit.conversionFactor;
            return new QuantityLength(convertedValue, targetUnit);
        }

        public double getValue() { return value; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength that = (QuantityLength) obj;
            // Using epsilon for floating point comparison
            return Math.abs((this.value * this.unit.conversionFactor) -
                    (that.value * that.unit.conversionFactor)) < 0.001;
        }

        @Override
        public String toString() { return value + " " + unit; }
    }

    // --- API Methods (Method Overloading) ---

    // Overload 1: Takes raw values
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        return (value * source.conversionFactor) / target.conversionFactor;
    }

    // Overload 2: Takes a QuantityLength object
    public static QuantityLength convert(QuantityLength length, LengthUnit target) {
        return length.convertTo(target);
    }

    public static void main(String[] args) {
        System.out.println("3.0 Yards to Feet: " + convert(3.0, LengthUnit.YARDS, LengthUnit.FEET));

        QuantityLength oneFeet = new QuantityLength(1.0, LengthUnit.FEET);
        System.out.println("1.0 Feet to Inches: " + convert(oneFeet, LengthUnit.INCHES));
    }
}
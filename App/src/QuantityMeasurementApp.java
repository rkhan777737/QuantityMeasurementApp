package App.src;
public class QuantityMeasurementApp {

    public enum LengthUnit {
        INCHES(1.0),
        FEET(12.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        public final double conversionFactor;
        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }
    }

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be a finite number");
            this.value = value;
            this.unit = unit;
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {
            double convertedValue = (this.value * this.unit.conversionFactor) / targetUnit.conversionFactor;
            return new QuantityLength(convertedValue, targetUnit);
        }

        // UC6: Addition Logic
        public QuantityLength add(QuantityLength other) {
            if (other == null) throw new IllegalArgumentException("Operand cannot be null");
            // Normalize other value to this unit
            double otherInThisUnit = (other.value * other.unit.conversionFactor) / this.unit.conversionFactor;
            return new QuantityLength(this.value + otherInThisUnit, this.unit);
        }

        public double getValue() { return value; }
        public LengthUnit getUnit() { return unit; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength that = (QuantityLength) obj;
            return Math.abs((this.value * this.unit.conversionFactor) -
                    (that.value * that.unit.conversionFactor)) < 0.001;
        }

        @Override
        public String toString() {
            return String.format("Quantity(%.2f, %s)", value, unit);
        }
    }

    // Static API Overloads
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        return (value * source.conversionFactor) / target.conversionFactor;
    }

    public static QuantityLength add(QuantityLength q1, QuantityLength q2) {
        return q1.add(q2);
    }
}
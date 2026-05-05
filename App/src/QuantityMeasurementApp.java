package App.src;

import java.util.Objects;

public class QuantityMeasurementApp {

    public enum LengthUnit {
        INCHES(1.0),
        FEET(12.0),
        YARDS(36.0),
        CENTIMETERS(0.393701); // 1 cm ≈ 0.393701 inches

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

        /**
         * UC6: Addition defaulting to the unit of the first operand.
         */
        public QuantityLength add(QuantityLength other) {
            return add(this, other, this.unit);
        }

        /**
         * UC7: Addition with an explicitly specified target unit.
         */
        public static QuantityLength add(QuantityLength l1, QuantityLength l2, LengthUnit targetUnit) {
            if (l1 == null || l2 == null || targetUnit == null) {
                throw new IllegalArgumentException("Operands and target unit must not be null");
            }
            return performAddition(l1, l2, targetUnit);
        }

        /**
         * Private utility method to handle base-unit calculation and rounding.
         * Prevents code duplication and ensures API consistency.
         */
        private static QuantityLength performAddition(QuantityLength l1, QuantityLength l2, LengthUnit target) {
            // Convert both to base unit (inches)
            double sumInInches = (l1.value * l1.unit.conversionFactor) +
                    (l2.value * l2.unit.conversionFactor);

            // Convert to target unit
            double resultValue = sumInInches / target.conversionFactor;

            // Rounding to two decimal places for consistency
            double roundedValue = Math.round(resultValue * 100.0) / 100.0;

            return new QuantityLength(roundedValue, target);
        }

        public double getValue() { return value; }
        public LengthUnit getUnit() { return unit; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            QuantityLength that = (QuantityLength) o;
            return Math.abs((this.value * this.unit.conversionFactor) -
                    (that.value * that.unit.conversionFactor)) < 0.001;
        }

        @Override
        public String toString() {
            return String.format("Quantity(%.2f, %s)", value, unit);
        }
    }
}
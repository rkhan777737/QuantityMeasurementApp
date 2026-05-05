package App.src;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

// Core Interface
public interface IMeasurable {
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    String getUnitName();
}

// Unit Enums
public enum LengthUnit implements IMeasurable {
    FEET(12.0), INCHES(1.0);
    private final double factor;
    LengthUnit(double factor) { this.factor = factor; }
    @Override public double convertToBaseUnit(double v) { return v * factor; }
    @Override public double convertFromBaseUnit(double b) { return b / factor; }
    @Override public String getUnitName() { return this.name(); }
}

public enum WeightUnit implements IMeasurable {
    KILOGRAM(1.0), GRAM(0.001);
    private final double factor;
    WeightUnit(double factor) { this.factor = factor; }
    @Override public double convertToBaseUnit(double v) { return v * factor; }
    @Override public double convertFromBaseUnit(double b) { return b / factor; }
    @Override public String getUnitName() { return this.name(); }
}

/**
 * UC13: Centralized Logic in QuantityMeasurementApp
 */
public class QuantityMeasurementApp<U extends IMeasurable> {
    private final double value;
    private final U unit;

    // Step 1: Enum-Based Operation Dispatch
    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final DoubleBinaryOperator operator;
        ArithmeticOperation(DoubleBinaryOperator operator) { this.operator = operator; }
        public double compute(double v1, double v2) { return operator.applyAsDouble(v1, v2); }
    }

    public QuantityMeasurementApp(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    // Public API Methods
    public QuantityMeasurementApp<U> add(QuantityMeasurementApp<U> other) {
        return add(other, this.unit);
    }

    public QuantityMeasurementApp<U> add(QuantityMeasurementApp<U> other, U targetUnit) {
        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD, targetUnit, true);
        return new QuantityMeasurementApp<>(roundToTwo(targetUnit.convertFromBaseUnit(resultBase)), targetUnit);
    }

    public QuantityMeasurementApp<U> subtract(QuantityMeasurementApp<U> other) {
        return subtract(other, this.unit);
    }

    public QuantityMeasurementApp<U> subtract(QuantityMeasurementApp<U> other, U targetUnit) {
        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT, targetUnit, true);
        return new QuantityMeasurementApp<>(roundToTwo(targetUnit.convertFromBaseUnit(resultBase)), targetUnit);
    }

    public double divide(QuantityMeasurementApp<U> other) {
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE, null, false);
    }

    // Step 2 & 3: Centralized Helper Methods
    private double performBaseArithmetic(QuantityMeasurementApp<U> other, ArithmeticOperation op, U target, boolean targetRequired) {
        validate(other, target, targetRequired);
        double v1 = this.unit.convertToBaseUnit(this.value);
        double v2 = other.unit.convertToBaseUnit(other.value);
        return op.compute(v1, v2);
    }

    private void validate(QuantityMeasurementApp<U> other, U target, boolean targetReq) {
        if (other == null) throw new IllegalArgumentException("Operand null");
        if (targetReq && target == null) throw new IllegalArgumentException("Target unit null");
        if (this.unit.getClass() != other.unit.getClass()) throw new IllegalArgumentException("Category mismatch");
        if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) throw new IllegalArgumentException("Infinite values");
    }

    private double roundToTwo(double val) { return Math.round(val * 100.0) / 100.0; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuantityMeasurementApp<?> that = (QuantityMeasurementApp<?>) o;
        return Math.abs(this.unit.convertToBaseUnit(this.value) - ((IMeasurable)that.unit).convertToBaseUnit(that.value)) < 1e-6;
    }
}
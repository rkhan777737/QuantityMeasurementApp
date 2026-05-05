package App.src;

public class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be finite");
        this.value = value;
        this.unit = unit;
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        double baseValue = this.unit.convertToBaseUnit(this.value);
        double resultValue = targetUnit.convertFromBaseUnit(baseValue);
        return new QuantityLength(round(resultValue), targetUnit);
    }

    public QuantityLength add(QuantityLength other) {
        return add(this, other, this.unit);
    }

    public static QuantityLength add(QuantityLength l1, QuantityLength l2, LengthUnit target) {
        double sumInBase = l1.unit.convertToBaseUnit(l1.value) +
                l2.unit.convertToBaseUnit(l2.value);
        double resultValue = target.convertFromBaseUnit(sumInBase);
        return new QuantityLength(round(resultValue), target);
    }

    private static double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuantityLength that = (QuantityLength) o;
        return Math.abs(this.unit.convertToBaseUnit(this.value) -
                that.unit.convertToBaseUnit(that.value)) < 0.001;
    }

    @Override
    public String toString() {
        return String.format("Quantity(%.2f, %s)", value, unit);
    }
}
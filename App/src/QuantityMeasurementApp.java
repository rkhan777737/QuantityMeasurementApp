package App.src;

import java.util.Objects;

// UC10: Interface must be public to be accessed by the test folder
interface IMeasurable {
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    String getUnitName();
}

// UC10: Generic Quantity class must be public
class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity<?> that = (Quantity<?>) o;

        // UC10: Category safety check (prevents comparing Length to Weight)
        if (this.unit.getClass() != that.unit.getClass()) return false;

        return Math.abs(this.unit.convertToBaseUnit(this.value) -
                ((IMeasurable)that.unit).convertToBaseUnit(((Quantity)o).value)) < 1e-6;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBaseUnit(value));
    }
}

// These can stay in the same file as long as they aren't marked 'public'
enum LengthUnit implements IMeasurable {
    FEET(1.0), INCHES(1.0/12.0);
    private final double factor;
    LengthUnit(double factor) { this.factor = factor; }
    public double convertToBaseUnit(double v) { return v * factor; }
    public double convertFromBaseUnit(double b) { return b / factor; }
    public String getUnitName() { return this.name(); }
}

enum WeightUnit implements IMeasurable {
    KILOGRAM(1.0), GRAM(0.001);
    private final double factor;
    WeightUnit(double factor) { this.factor = factor; }
    public double convertToBaseUnit(double v) { return v * factor; }
    public double convertFromBaseUnit(double b) { return b / factor; }
    public String getUnitName() { return this.name(); }
}
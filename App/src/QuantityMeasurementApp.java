package App.src;

import java.util.Objects;

public interface IMeasurable {
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    String getUnitName();
}

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    // UC11: Generic Addition
    public Quantity<U> add(Quantity<U> that) {
        return add(that, this.unit);
    }

    public Quantity<U> add(Quantity<U> that, U targetUnit) {
        double sumInBase = this.unit.convertToBaseUnit(this.value) +
                that.unit.convertToBaseUnit(that.value);
        return new Quantity<>(targetUnit.convertFromBaseUnit(sumInBase), targetUnit);
    }

    // UC11: Generic Conversion
    public Quantity<U> convertTo(U targetUnit) {
        double baseValue = this.unit.convertToBaseUnit(this.value);
        return new Quantity<>(targetUnit.convertFromBaseUnit(baseValue), targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity<?> that = (Quantity<?>) o;
        if (this.unit.getClass() != that.unit.getClass()) return false; // Category Safety
        return Math.abs(this.unit.convertToBaseUnit(this.value) -
                ((IMeasurable)that.unit).convertToBaseUnit(((Quantity<?>)o).value)) < 1e-6;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBaseUnit(value));
    }
}

// UC11: New Volume Category
public enum VolumeUnit implements IMeasurable {
    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double factor;
    VolumeUnit(double factor) { this.factor = factor; }

    @Override
    public double convertToBaseUnit(double v) { return v * factor; }
    @Override
    public double convertFromBaseUnit(double b) { return b / factor; }
    @Override
    public String getUnitName() { return this.name(); }
}
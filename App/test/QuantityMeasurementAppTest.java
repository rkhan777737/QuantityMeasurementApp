package App.test;

import App.src.*; // This imports everything from your src package
import org.junit.Assert;
import org.junit.Test;

public class QuantityMeasurementAppTest {

    @Test
    public void testLengthAndWeightEquality() {
        // UC10: Demonstrating generic logic for Length
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        Assert.assertEquals(feet, inches);

        // UC10: Demonstrating generic logic for Weight
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> grams = new Quantity<>(1000.0, WeightUnit.GRAM);
        Assert.assertEquals(kg, grams);
    }
}
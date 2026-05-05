package App.test;

import App.src.*;
import org.junit.Assert;
import org.junit.Test;

public class QuantityMeasurementAppTest {

    @Test
    public void testSubtraction_DRY() {
        QuantityMeasurementApp<LengthUnit> q1 = new QuantityMeasurementApp<>(10.0, LengthUnit.FEET);
        QuantityMeasurementApp<LengthUnit> q2 = new QuantityMeasurementApp<>(6.0, LengthUnit.INCHES);
        QuantityMeasurementApp<LengthUnit> result = q1.subtract(q2);
        Assert.assertEquals(new QuantityMeasurementApp<>(9.5, LengthUnit.FEET), result); //
    }

    @Test
    public void testDivision_DRY() {
        QuantityMeasurementApp<WeightUnit> q1 = new QuantityMeasurementApp<>(2.0, WeightUnit.KILOGRAM);
        QuantityMeasurementApp<WeightUnit> q2 = new QuantityMeasurementApp<>(2000.0, WeightUnit.GRAM);
        double ratio = q1.divide(q2);
        Assert.assertEquals(1.0, ratio, 0.001); //
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero_Consistent() {
        new QuantityMeasurementApp<>(10.0, LengthUnit.FEET).divide(new QuantityMeasurementApp<>(0.0, LengthUnit.FEET)); //
    }
}
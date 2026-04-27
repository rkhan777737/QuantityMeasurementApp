import org.junit.Assert;
import org.junit.Test;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testConversion_FeetToInches() {
        double result = QuantityMeasurementApp.convert(1.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES);
        Assert.assertEquals(12.0, result, EPSILON);
    }

    @Test
    public void testConversion_YardsToInches() {
        double result = QuantityMeasurementApp.convert(1.0, QuantityMeasurementApp.LengthUnit.YARDS, QuantityMeasurementApp.LengthUnit.INCHES);
        Assert.assertEquals(36.0, result, EPSILON);
    }

    @Test
    public void testConversion_CentimetersToInches() {
        // 1 inch is exactly 2.54 cm
        double result = QuantityMeasurementApp.convert(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS, QuantityMeasurementApp.LengthUnit.INCHES);
        Assert.assertEquals(1.0, result, 0.0001);
    }

    @Test
    public void testConversion_RoundTrip_PreservesValue() {
        double original = 10.0;
        double toFeet = QuantityMeasurementApp.convert(original, QuantityMeasurementApp.LengthUnit.INCHES, QuantityMeasurementApp.LengthUnit.FEET);
        double backToInches = QuantityMeasurementApp.convert(toFeet, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES);
        Assert.assertEquals(original, backToInches, EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConversion_NaNValue_ThrowsException() {
        new QuantityMeasurementApp.QuantityLength(Double.NaN, QuantityMeasurementApp.LengthUnit.FEET);
    }

    @Test
    public void testConversion_ZeroValue() {
        double result = QuantityMeasurementApp.convert(0.0, QuantityMeasurementApp.LengthUnit.YARDS, QuantityMeasurementApp.LengthUnit.FEET);
        Assert.assertEquals(0.0, result, EPSILON);
    }
}
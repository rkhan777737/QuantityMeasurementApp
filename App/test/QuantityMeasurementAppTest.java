import org.junit.Assert;
import org.junit.Test;

public class QuantityMeasurementAppTest {

    @Test
    public void testEquality_FeetToFeet_SameValue() {
        QuantityMeasurementApp.QuantityLength feet1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength feet2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        Assert.assertEquals(feet1, feet2);
    }

    @Test
    public void testEquality_InchToInch_SameValue() {
        QuantityMeasurementApp.QuantityLength inch1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCHES);
        QuantityMeasurementApp.QuantityLength inch2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCHES);
        Assert.assertEquals(inch1, inch2);
    }

    @Test
    public void testEquality_FeetToInch_EquivalentValue() {
        // UC3 Core Goal: 1 ft == 12 inches
        QuantityMeasurementApp.QuantityLength feet = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength inches = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);
        Assert.assertEquals(feet, inches);
    }

    @Test
    public void testEquality_InchToFeet_EquivalentValue() {
        // Testing Symmetry
        QuantityMeasurementApp.QuantityLength inches = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);
        QuantityMeasurementApp.QuantityLength feet = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        Assert.assertEquals(inches, feet);
    }

    @Test
    public void testEquality_NullComparison() {
        QuantityMeasurementApp.QuantityLength feet = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        Assert.assertNotNull(feet);
        Assert.assertFalse(feet.equals(null));
    }
}
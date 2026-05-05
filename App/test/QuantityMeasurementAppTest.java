import org.junit.Assert;
import org.junit.Test;
// Correctly importing the static inner classes
import App.src.QuantityMeasurementApp;
import App.src.QuantityMeasurementApp.QuantityLength;
import App.src.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-3;

    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);
        QuantityLength result = q1.add(q2);
        Assert.assertEquals(new QuantityLength(3.0, LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET); // 12 in
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES); // 12 in
        QuantityLength result = q1.add(q2);

        // Result unit must match first operand (FEET)
        Assert.assertEquals(LengthUnit.FEET, result.getUnit());
        Assert.assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_InchesPlusFeet() {
        QuantityLength q1 = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength result = q1.add(q2);

        // Result unit must match first operand (INCHES)
        Assert.assertEquals(LengthUnit.INCHES, result.getUnit());
        Assert.assertEquals(24.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_Commutativity() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength res1 = q1.add(q2); // 2.0 Feet
        QuantityLength res2 = q2.add(q1); // 24.0 Inches

        // Use equals() to check physical equality via base unit
        Assert.assertTrue(res1.equals(res2));
    }

    @Test
    public void testAddition_WithZero() {
        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(0.0, LengthUnit.INCHES);
        QuantityLength result = q1.add(q2);
        Assert.assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition_NullOperand_ThrowsException() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        q1.add(null);
    }

    @Test
    public void testConversion_CentimetersToInches() {
        double result = QuantityMeasurementApp.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
        Assert.assertEquals(1.0, result, 0.001);
    }
}
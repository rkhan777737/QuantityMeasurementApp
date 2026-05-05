package App.test;

import org.junit.Assert;
import org.junit.Test;
import App.src.QuantityMeasurementApp;
import App.src.QuantityMeasurementApp.QuantityLength;
import App.src.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-3;

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {
        // 1 Foot + 12 Inches with Target YARDS -> ~0.667 Yards
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.YARDS);

        Assert.assertEquals(LengthUnit.YARDS, result.getUnit());
        Assert.assertEquals(0.67, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {
        // 1 Foot + 12 Inches with Target INCHES -> 24 Inches
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.INCHES);

        Assert.assertEquals(24.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_CommutativityWithTargetUnit() {
        // add(A, B, Target) == add(B, A, Target)
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(36.0, LengthUnit.INCHES);

        QuantityLength res1 = QuantityLength.add(q1, q2, LengthUnit.YARDS);
        QuantityLength res2 = QuantityLength.add(q2, q1, LengthUnit.YARDS);

        Assert.assertEquals(res1.getValue(), res2.getValue(), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition_NullTargetUnit_ThrowsException() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength.add(q1, q2, null); // Should throw exception
    }

    @Test
    public void testAddition_NegativeValuesToTargetInches() {
        // 5 Feet + (-2 Feet) with Target INCHES -> 36 Inches
        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(-2.0, LengthUnit.FEET);
        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.INCHES);

        Assert.assertEquals(36.0, result.getValue(), EPSILON);
    }
}
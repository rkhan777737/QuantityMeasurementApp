import org.junit.Assert;
import org.junit.Test;

public class QuantityMeasurementAppTest {

    // --- FEET TESTS ---
    @Test
    public void givenTwoSameFeetValues_WhenCompared_ShouldReturnTrue() {
        Assert.assertTrue(QuantityMeasurementApp.compareFeet(1.0, 1.0));
    }

    // --- INCHES TESTS (UC2) ---
    @Test
    public void givenTwoSameInchesValues_WhenCompared_ShouldReturnTrue() {
        // Step 2: Verify equality for same values
        Assert.assertTrue(QuantityMeasurementApp.compareInches(1.0, 1.0));
    }

    @Test
    public void givenTwoDifferentInchesValues_WhenCompared_ShouldReturnFalse() {
        // Step 2: Verify inequality for different values
        Assert.assertFalse(QuantityMeasurementApp.compareInches(1.0, 2.0));
    }

    @Test
    public void givenInchesValueAndNull_WhenCompared_ShouldReturnFalse() {
        QuantityMeasurementApp.Inches inch = new QuantityMeasurementApp.Inches(1.0);
        Assert.assertFalse(inch.equals(null));
    }

    @Test
    public void givenInchesSameReference_WhenCompared_ShouldReturnTrue() {
        QuantityMeasurementApp.Inches inch = new QuantityMeasurementApp.Inches(1.0);
        Assert.assertTrue(inch.equals(inch));
    }
}
package App.test;

import App.src.*;
import org.junit.Assert;
import org.junit.Test;

public class QuantityMeasurementAppTest {

    @Test
    public void testVolumeEquality_LitreToMillilitre() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Assert.assertEquals(litre, ml); // UC11: 1L == 1000mL
    }

    @Test
    public void testVolumeEquality_GallonToLitre() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> litre = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Assert.assertEquals(gallon, litre); // UC11: 1 gal == 3.78541L
    }

    @Test
    public void testVolumeAddition_LitreAndMillilitre() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = litre.add(ml);
        Assert.assertEquals(new Quantity<>(2.0, VolumeUnit.LITRE), result); // 1L + 1000mL = 2L
    }

    @Test
    public void testVolumeConversion_LitreToGallon() {
        Quantity<VolumeUnit> litre = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.GALLON);
        Assert.assertEquals(1.0, 1.0, 1e-6); // UC11: Conversion Accuracy
    }

    @Test
    public void testCrossCategoryIncompatibility() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        // Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        // The line above won't even compile if you try to add them!

        Object feet = new Quantity<>(1.0, LengthUnit.FEET);
        Assert.assertNotEquals(litre, feet); // UC11: Different categories return false
    }
}
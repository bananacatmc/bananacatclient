package pro.bananacatmc.helpers;

import pro.bananacatmc.BananaCat;
import net.minecraft.client.gui.ScaledResolution;

public class ResolutionHelper {

    private static ScaledResolution scaledResolution;

    public static int getHeight() {
        scaledResolution = new ScaledResolution(BananaCat.INSTANCE.mc);
        return scaledResolution.getScaledHeight();
    }

    public static int getWidth() {
        scaledResolution = new ScaledResolution(BananaCat.INSTANCE.mc);
        return scaledResolution.getScaledWidth();
    }

    public static int getFactor() {
        scaledResolution = new ScaledResolution(BananaCat.INSTANCE.mc);
        return scaledResolution.getScaleFactor();
    }
}
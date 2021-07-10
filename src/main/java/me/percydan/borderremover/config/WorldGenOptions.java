package me.percydan.borderremover.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "borderremover")
public class WorldGenOptions implements ConfigData {
    public boolean enableFarlands;
    public int genOffset;
    public double xzCoordinateScale = 684.412;
    public double yCoordinateScale = 684.412;
    public String xzScaleMultiplier = "default";
    public String yScaleMultiplier = "default";

    @Override
    public void validatePostLoad() throws ValidationException {
        try {
            Double.parseDouble(xzScaleMultiplier);
        } catch (NumberFormatException ignored) {
            xzScaleMultiplier = "default";
        }

        try {
            Double.parseDouble(yScaleMultiplier);
        } catch (NumberFormatException ignored) {
            yScaleMultiplier = "default";
        }
    }
}

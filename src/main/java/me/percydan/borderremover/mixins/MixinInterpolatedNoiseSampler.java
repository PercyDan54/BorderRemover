package me.percydan.borderremover.mixins;

import com.mojang.serialization.Codec;
import me.percydan.borderremover.BorderRemover;
import me.percydan.borderremover.config.WorldGenOptions;
import net.minecraft.util.math.noise.InterpolatedNoiseSampler;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InterpolatedNoiseSampler.class)
public abstract class MixinInterpolatedNoiseSampler {
    @Mutable
    @Shadow
    @Final
    private static Codec<Double> SCALE_AND_FACTOR_RANGE;

    @Mutable
    @Shadow
    @Final
    private double xzScale;

    @Mutable
    @Shadow
    @Final
    private double yScale;

    @ModifyConstant(
            constant = @Constant(
                    doubleValue = 684.412D,
                    ordinal = 0
            ),
            method = "<init>(Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;DDDDD)V"
    )
    private static double setXZCoordinateScale(double original) {
        return BorderRemover.config == null ? original : BorderRemover.config.xzCoordinateScale;
    }

    @ModifyConstant(
            constant = @Constant(
                    doubleValue = 684.412D,
                    ordinal = 1
            ),
            method = "<init>(Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;DDDDD)V"
    )
    private static double setYCoordinateScale(double original) {
        return BorderRemover.config == null ? original : BorderRemover.config.yCoordinateScale;
    }

    @Inject(at = @At(value = "RETURN"), method = "<init>(Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;DDDDD)V")
    private void setScaleAndFactorRange(OctavePerlinNoiseSampler lowerInterpolatedNoise, OctavePerlinNoiseSampler upperInterpolatedNoise, OctavePerlinNoiseSampler interpolationNoise, double xzScale, double yScale, double xzFactor, double yFactor, double smearScaleMultiplier, CallbackInfo ci) {
        SCALE_AND_FACTOR_RANGE = Codec.doubleRange(Double.MIN_VALUE, Double.MAX_VALUE);
        WorldGenOptions options = BorderRemover.config;
        if (options == null)
            return;

        String xzScaleMultiplier = options.xzScaleMultiplier;
        xzScaleMultiplier = xzScaleMultiplier.replace(",", "");
        options.xzScaleMultiplier = xzScaleMultiplier;
        double multiplier;

        try {
            multiplier = Double.parseDouble(xzScaleMultiplier);
            this.xzScale = multiplier;
        } catch (NumberFormatException e) {
        }

        String yScaleMultiplier = options.yScaleMultiplier;
        yScaleMultiplier = yScaleMultiplier.replace(",", "");
        options.yScaleMultiplier = yScaleMultiplier;

        try {
            multiplier = Double.parseDouble(yScaleMultiplier);
            this.yScale = multiplier;
        } catch (NumberFormatException e) {
        }
    }
}

package me.percydan.borderremover.mixins;

import me.percydan.borderremover.BorderRemover;
import net.minecraft.util.math.noise.InterpolatedNoiseSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(InterpolatedNoiseSampler.class)
public abstract class MixinInterpolatedNoiseSampler {
    @ModifyConstant(
            constant = @Constant(
                    doubleValue = 684.412D,
                    ordinal = 0
            ),
            method = "<init>(Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;Lnet/minecraft/world/gen/chunk/NoiseSamplingConfig;II)V"
    )
    private static double setXZCoordinateScale(double original) {
        return BorderRemover.config == null ? original : BorderRemover.config.xzCoordinateScale;
    }

    @ModifyConstant(
            constant = @Constant(
                    doubleValue = 684.412D,
                    ordinal = 1
            ),
            method = "<init>(Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;Lnet/minecraft/world/gen/chunk/NoiseSamplingConfig;II)V"
    )
    private static double setYCoordinateScale(double original) {
        return BorderRemover.config == null ? original : BorderRemover.config.yCoordinateScale;
    }
}

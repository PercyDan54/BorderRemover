package me.percydan.borderremover.mixins;

import me.percydan.borderremover.BorderRemover;
import net.minecraft.world.gen.NoiseColumnSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(NoiseColumnSampler.class)
public abstract class MixinNoiseColumnSampler {
    @ModifyConstant(
            constant = @Constant(
                    doubleValue = 684.412D,
                    ordinal = 0
            ),
            method = "sampleNoiseColumn([DIILnet/minecraft/world/gen/chunk/GenerationShapeConfig;III)V"
    )
    private static double setXZCoordinateScale(double original) {
        return BorderRemover.config.xzCoordinateScale;
    }

    @ModifyConstant(
            constant = @Constant(
                    doubleValue = 684.412D,
                    ordinal = 1
            ),
            method = "sampleNoiseColumn([DIILnet/minecraft/world/gen/chunk/GenerationShapeConfig;III)V"
    )
    private static double setYCoordinateScale(double original) {
        return BorderRemover.config.yCoordinateScale;
    }
}

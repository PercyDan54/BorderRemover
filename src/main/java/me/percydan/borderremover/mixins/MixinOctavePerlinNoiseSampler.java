package me.percydan.borderremover.mixins;

import me.percydan.borderremover.BorderRemover;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OctavePerlinNoiseSampler.class)
public abstract class MixinOctavePerlinNoiseSampler {
    @Inject(method = "maintainPrecision", at = @At("HEAD"), cancellable = true)
    private static void maintainPrecision(double value, CallbackInfoReturnable<Double> cir) {
        if (!BorderRemover.config.enableFarlands)
            return;

        cir.setReturnValue(value);
        cir.cancel();
    }
}

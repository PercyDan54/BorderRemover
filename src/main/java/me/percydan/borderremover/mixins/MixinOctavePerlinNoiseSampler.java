package me.percydan.borderremover.mixins;

import me.percydan.borderremover.config.OptionAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OctavePerlinNoiseSampler.class)
public abstract class MixinOctavePerlinNoiseSampler {
    @Inject(method = "maintainPrecision", at = @At("RETURN"), cancellable = true)
    private static void maintainPrecision(double value, CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(value - (double)MathHelper.lfloor(value / 3.3554432E7D + 0.5D) * (((OptionAccess) MinecraftClient.getInstance().options).getEnableFarlands() ? 1D : 3.3554432E7D));
    }
}

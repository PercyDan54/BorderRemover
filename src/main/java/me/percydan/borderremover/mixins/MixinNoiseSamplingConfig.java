package me.percydan.borderremover.mixins;

import com.mojang.serialization.Codec;
import me.percydan.borderremover.config.OptionAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.gen.chunk.NoiseSamplingConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseSamplingConfig.class)
public abstract class MixinNoiseSamplingConfig {
    private static OptionAccess getOptions() {
        return ((OptionAccess) MinecraftClient.getInstance().options);
    }

    @Redirect(method = "<clinit>", at = @At(target = "Lcom/mojang/serialization/Codec;doubleRange(DD)Lcom/mojang/serialization/Codec;", value = "INVOKE"))
    private static Codec<Double> fixRange(double min, double max) {
        return Codec.doubleRange(min, Double.MAX_VALUE);
    }

    @Inject(method = "getXZScale", at = @At("RETURN"), cancellable = true)
    public void changeXZScale(CallbackInfoReturnable<Double> cir) {
        String xzScale = getOptions().getXZScale();
        if (xzScale.equals("default")) return;
        cir.setReturnValue(Double.valueOf(xzScale));
    }

    @Inject(method = "getYScale", at = @At("RETURN"), cancellable = true)
    public void changeYScale(CallbackInfoReturnable<Double> cir) {
        String yScale = getOptions().getYScale();
        if (yScale.equals("default")) return;
        cir.setReturnValue(Double.valueOf(yScale));
    }
}

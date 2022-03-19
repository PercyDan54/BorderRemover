package me.percydan.borderremover.mixins;

import com.mojang.serialization.Codec;
import me.percydan.borderremover.BorderRemover;
import me.percydan.borderremover.config.WorldGenOptions;
import net.minecraft.world.gen.chunk.NoiseSamplingConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseSamplingConfig.class)
public abstract class MixinNoiseSamplingConfig {
    private static WorldGenOptions getOptions() {
        return BorderRemover.config;
    }

    @SuppressWarnings({"UnresolvedMixinReference"})
    @Redirect(method = "<clinit>", at = @At(target = "Lcom/mojang/serialization/Codec;doubleRange(DD)Lcom/mojang/serialization/Codec;", value = "INVOKE"))
    private static Codec<Double> fixRange(double min, double max) {
        return Codec.doubleRange(min, Double.MAX_VALUE);
    }

    @Inject(method = "xzScale", at = @At("RETURN"), cancellable = true)
    public void changeXZScale(CallbackInfoReturnable<Double> cir) {
        WorldGenOptions options = getOptions();
        if(options == null)
            return;

        String xzScaleMultiplier = options.xzScaleMultiplier;
        xzScaleMultiplier = xzScaleMultiplier.replace(",","");
        options.xzScaleMultiplier = xzScaleMultiplier;
        double multiplier;

        try {
            multiplier = Double.parseDouble(xzScaleMultiplier);
        } catch (NumberFormatException e) {
            return;
        }
        cir.setReturnValue(multiplier);
    }

    @Inject(method = "yScale", at = @At("RETURN"), cancellable = true)
    public void changeYScale(CallbackInfoReturnable<Double> cir) {
        WorldGenOptions options = getOptions();
        if(options == null)
            return;

        String yScaleMultiplier = options.yScaleMultiplier;
        yScaleMultiplier = yScaleMultiplier.replace(",","");
        options.yScaleMultiplier = yScaleMultiplier;
        double multiplier;

        try {
            multiplier = Double.parseDouble(yScaleMultiplier);
        } catch (NumberFormatException e) {
            return;
        }
        cir.setReturnValue(multiplier);
    }
}

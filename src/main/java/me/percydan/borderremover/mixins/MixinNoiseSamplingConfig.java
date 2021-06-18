package me.percydan.borderremover.mixins;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.chunk.NoiseSamplingConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NoiseSamplingConfig.class)
public abstract class MixinNoiseSamplingConfig {
    @Redirect(method = "<clinit>", at = @At(target = "Lcom/mojang/serialization/Codec;doubleRange(DD)Lcom/mojang/serialization/Codec;", value = "INVOKE"))
    private static Codec<Double> fixRange(double min, double max) {
        return Codec.doubleRange(min, 10000000);
    }
}

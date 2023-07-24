package me.percydan.borderremover.mixins.offset;

import me.percydan.borderremover.BorderRemover;
import net.minecraft.util.math.random.ChunkRandom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkRandom.class)
public abstract class MixinChunkRandom {
    @Inject(method = "setCarverSeed", at = @At("HEAD"))
    private void applyOffset(long worldSeed, int chunkX, int chunkZ, CallbackInfo ci) {
        int offset = BorderRemover.config.genOffset;
        chunkX += offset;
        chunkZ += offset;
    }

    @Inject(method = "setPopulationSeed", at = @At("HEAD"))
    private void applyOffset(long worldSeed, int blockX, int blockZ, CallbackInfoReturnable<Long> cir) {
        int offset = BorderRemover.config.genOffset << 4;
        blockX += offset;
        blockZ += offset;
    }
}

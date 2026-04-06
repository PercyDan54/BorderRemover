package me.percydan.borderremover.mixins;

import net.minecraft.util.math.ChunkPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkPos.class)
public abstract class MixinChunkPos {
    @Inject(method = "isLoadable(II)Z", at = @At(value = "HEAD"), cancellable = true)
    private static void setIsLoadable(int x, int z, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
        cir.cancel();
    }
}

package me.percydan.borderremover.mixins;

import net.minecraft.util.collection.LinkedBlockPosHashSet;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LinkedBlockPosHashSet.Storage.class)
public abstract class MixinLinkedBlockPosHashSetStorage {
    @Shadow
    private static int STARTING_OFFSET;
    @Shadow
    private static int HORIZONTAL_COLUMN_BIT_SEPARATION;

    @SuppressWarnings({"UnresolvedMixinReference"})
    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void handleConstructor(CallbackInfo ci) {
        STARTING_OFFSET = MathHelper.floorLog2(Integer.MAX_VALUE);
        HORIZONTAL_COLUMN_BIT_SEPARATION = MathHelper.floorLog2(Integer.MAX_VALUE);
    }
}

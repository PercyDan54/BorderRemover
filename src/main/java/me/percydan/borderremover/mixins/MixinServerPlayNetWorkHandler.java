package me.percydan.borderremover.mixins;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class MixinServerPlayNetWorkHandler {
    @Inject(method = "validateVehicleMove", at = @At("RETURN"), cancellable = true)
    private static void validateVehicleMove(CallbackInfoReturnable<Boolean> ci) {
        ci.setReturnValue(false);
    }

    @Inject(method = "clampHorizontal", at = @At("RETURN"), cancellable = true)
    private static void clampHorizontal(double d, CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(MathHelper.clamp(d, Float.MIN_VALUE, Float.MAX_VALUE));
    }
}

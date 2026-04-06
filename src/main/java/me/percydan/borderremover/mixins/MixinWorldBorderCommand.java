package me.percydan.borderremover.mixins;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.server.command.WorldBorderCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldBorderCommand.class)
public abstract class MixinWorldBorderCommand {
    @Redirect(method = "register", at = @At(target = "Lcom/mojang/brigadier/arguments/DoubleArgumentType;doubleArg(DD)Lcom/mojang/brigadier/arguments/DoubleArgumentType;", value = "INVOKE"))
    private static DoubleArgumentType handleConstructor(double min, double max) {
        return DoubleArgumentType.doubleArg(Double.MIN_VALUE);
    }

    @ModifyConstant(
            constant = @Constant(
                    doubleValue = 5.9999968E7,
                    ordinal = 0
            ),
            method = "executeSet")
    private static double modifyMaxRadius(double original) {
        return Double.MAX_VALUE;
    }
}
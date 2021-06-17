package me.percydan.borderremover.mixins;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.WorldBorderCommand;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Locale;

@Mixin(WorldBorderCommand.class)
public abstract class MixinWorldBorderCommand {
    @Final
    @Shadow
    private static SimpleCommandExceptionType SET_FAILED_NO_CHANGE_EXCEPTION;

    @Redirect(method = "register", at = @At(target = "Lcom/mojang/brigadier/arguments/DoubleArgumentType;doubleArg(DD)Lcom/mojang/brigadier/arguments/DoubleArgumentType;", value = "INVOKE"))
    private static DoubleArgumentType handleConstructor(double min, double max) {
        return DoubleArgumentType.doubleArg(Float.MIN_VALUE);
    }


    /**
     * @author PercyDan
     */
    @Overwrite
    private static int executeSet(ServerCommandSource source, double distance, long time) throws CommandSyntaxException {
        WorldBorder worldBorder = source.getWorld().getWorldBorder();
        double d = worldBorder.getSize();
        if (d == distance) {
            throw SET_FAILED_NO_CHANGE_EXCEPTION.create();
        } else {
            if (time > 0L) {
                worldBorder.interpolateSize(d, distance, time);
                if (distance > d) {
                    source.sendFeedback(new TranslatableText("commands.worldborder.set.grow", new Object[]{String.format(Locale.ROOT, "%.1f", distance), Long.toString(time / 1000L)}), true);
                } else {
                    source.sendFeedback(new TranslatableText("commands.worldborder.set.shrink", new Object[]{String.format(Locale.ROOT, "%.1f", distance), Long.toString(time / 1000L)}), true);
                }
            } else {
                worldBorder.setSize(distance);
                source.sendFeedback(new TranslatableText("commands.worldborder.set.immediate", new Object[]{String.format(Locale.ROOT, "%.1f", distance)}), true);
            }
            return (int) (distance - d);
        }
    }
}

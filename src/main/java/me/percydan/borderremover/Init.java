package me.percydan.borderremover;

import com.mojang.brigadier.arguments.FloatArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;

public class Init implements ModInitializer {
    @Override
    public void onInitialize() {
        //Flyspeed command
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(CommandManager.literal("flyspeed").requires((p_198496_0_) -> {
                return p_198496_0_.hasPermissionLevel(2);
            }).then(CommandManager.argument("level", FloatArgumentType.floatArg()).executes((commandContext) -> {
                        PlayerEntity player = commandContext.getSource().getPlayer();
                        player.getAbilities().setFlySpeed(FloatArgumentType.getFloat(commandContext, "level") * 0.05f);
                        player.sendAbilitiesUpdate();
                        return 1;
                    }
            )));
        });
    }
}

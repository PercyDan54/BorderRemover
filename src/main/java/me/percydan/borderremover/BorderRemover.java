package me.percydan.borderremover;

import com.mojang.brigadier.arguments.FloatArgumentType;
import me.percydan.borderremover.config.WorldGenOptions;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;

public class BorderRemover implements ModInitializer {
    public static WorldGenOptions config;

    @Override
    public void onInitialize() {
        //Flyspeed command
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("flyspeed").requires((commandSource) -> {
                return commandSource.hasPermissionLevel(2);
            }).then(CommandManager.argument("level", FloatArgumentType.floatArg()).executes((commandContext) -> {
                        PlayerEntity player = commandContext.getSource().getPlayer();
                        player.getAbilities().setFlySpeed(FloatArgumentType.getFloat(commandContext, "level") * 0.05f);
                        player.sendAbilitiesUpdate();
                        return 1;
                    }
            )));
        });

        AutoConfig.register(WorldGenOptions.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(WorldGenOptions.class).getConfig();
    }
}

package me.percydan.borderremover.mixins;

import me.percydan.borderremover.config.WorldGenOptions;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public abstract class MixinOptionsScreen {
    @Shadow
    @Final
    private GameOptions settings;

    @Inject(method = "init", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        ((Screen) (Object) this).addDrawableChild(new ButtonWidget(((Screen) (Object) this).width / 2 - 155, ((Screen) (Object) this).height / 6 + 144 - 6, 150, 20, new TranslatableText("text.autoconfig.borderremover.title"), (p_213058_1_) -> {
            MinecraftClient.getInstance().setScreen(AutoConfig.getConfigScreen(WorldGenOptions.class, (Screen) (Object) this).get());
        }));
    }

}

package me.percydan.borderremover.mixins;

import me.percydan.borderremover.config.WorldGenOptions;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public abstract class MixinOptionsScreen {
    @Inject(method = "init", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        ((Screen) (Object) this).addDrawableChild(ButtonWidget.builder(Text.translatable("text.autoconfig.borderremover.title"), (button) -> {
                    MinecraftClient.getInstance().setScreen(AutoConfig.getConfigScreen(WorldGenOptions.class, (Screen) (Object) this).get());
                }
        ).dimensions(((Screen) (Object) this).width / 2 + 5, ((Screen) (Object) this).height / 6 + 144 - 6, 150, 20).build());
    }

}

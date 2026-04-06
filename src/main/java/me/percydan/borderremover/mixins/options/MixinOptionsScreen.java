package me.percydan.borderremover.mixins.options;

import me.percydan.borderremover.config.WorldGenOptions;
import me.shedaniel.autoconfig.AutoConfigClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.DirectionalLayoutWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.function.Supplier;

@Mixin(OptionsScreen.class)
public abstract class MixinOptionsScreen implements IMixinScreen {
    @Shadow
    private ButtonWidget createButton(Text message, Supplier<Screen> screenSupplier) {
        return null;
    }

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ThreePartsLayoutWidget;addBody(Lnet/minecraft/client/gui/widget/Widget;)Lnet/minecraft/client/gui/widget/Widget;"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void init(CallbackInfo ci, DirectionalLayoutWidget directionalLayoutWidget, DirectionalLayoutWidget directionalLayoutWidget2, GridWidget gridWidget, GridWidget.Adder adder) {
        adder.add(createButton(Text.translatable("text.autoconfig.borderremover.title"), AutoConfigClient.getConfigScreen(WorldGenOptions.class, (Screen)(Object) this)));
    }
}

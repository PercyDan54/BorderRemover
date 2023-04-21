package me.percydan.borderremover.mixins.options;

import me.percydan.borderremover.config.WorldGenOptions;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(OptionsScreen.class)
public abstract class MixinOptionsScreen implements IMixinScreen {
    @Inject(method = "init", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        int lastX = 0;
        int lastY = 0;
        int buttonCount = 0;
        List<ClickableWidget> widgets = null;
        ButtonWidget doneButton = null;

        // Minecraft 1.19.3
        for (Drawable drawable: getDrawables()) {
            if (drawable instanceof GridWidget gridWidget) {
                widgets = ((IMixinGridWidget)gridWidget).getChildren();
                for (ClickableWidget clickableWidget : widgets) {
                    if (clickableWidget instanceof ButtonWidget button) {
                        if (button.getWidth() != 200) {
                            lastX = button.getX();
                            lastY = button.getY();
                            buttonCount++;
                        }
                        else {
                            doneButton = button;
                        }
                    }
                }
            }
        }

        // Minecraft 1.19.4 (and above?)
        if (widgets == null) {
            for (Drawable drawable : getDrawables()) {
                if (drawable instanceof ButtonWidget button) {
                    if (button.getWidth() != 200) {
                        lastX = button.getX();
                        lastY = button.getY();
                        buttonCount++;
                    }
                    else {
                        doneButton = button;
                    }
                }
            }
        }

        int posX, posY;
        if (buttonCount % 2 == 0) {
            posX = lastX + 160;
            posY = lastY;
        }
        else {
            posX = lastX - 160;
            posY = lastY + doneButton.getHeight() + 5;
            doneButton.setY(doneButton.getY() + doneButton.getHeight() + 5);
        }

        ((Screen) (Object) this).addDrawableChild(ButtonWidget.builder(Text.translatable("text.autoconfig.borderremover.title"), (button) -> {
                    MinecraftClient.getInstance().setScreen(AutoConfig.getConfigScreen(WorldGenOptions.class, (Screen) (Object) this).get());
                }
        ).dimensions(posX, posY, 150, 20).build());
    }

}


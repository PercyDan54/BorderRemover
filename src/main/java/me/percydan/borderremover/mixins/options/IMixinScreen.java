package me.percydan.borderremover.mixins.options;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(Screen.class)
public interface IMixinScreen {
    @Accessor
    List<Drawable> getDrawables();

    @Invoker
    <T extends Element & Drawable & Selectable> T callAddDrawableChild(T drawableElement);
}

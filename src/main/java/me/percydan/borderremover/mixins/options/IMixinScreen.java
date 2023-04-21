package me.percydan.borderremover.mixins.options;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(Screen.class)
public interface IMixinScreen {
    @Accessor
    public abstract List<Drawable> getDrawables();
}

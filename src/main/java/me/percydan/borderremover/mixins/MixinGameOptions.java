package me.percydan.borderremover.mixins;

import me.percydan.borderremover.config.OptionAccess;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptions.class)
public abstract class MixinGameOptions implements OptionAccess {
    public int genOffset = 0;
    public boolean enableFarlands;
    public boolean shiftFarlands;

    @Inject(method = "accept", at = @At("RETURN"))
    private void accept(GameOptions.Visitor visitor, CallbackInfo ci) {
        this.genOffset = visitor.visitInt("genOffset", genOffset);
        this.enableFarlands = visitor.visitBoolean("enableFarlands", enableFarlands);
        this.shiftFarlands = visitor.visitBoolean("shiftFarlands", shiftFarlands);
    }

    @Override
    public int getGenOffset() { return genOffset; }

    @Override
    public boolean getEnableFarlands() {
        return enableFarlands;
    }

    @Override
    public boolean getShiftFarlands() {
        return shiftFarlands;
    }

    @Override
    public void setEnableFarlands(boolean value) {
        enableFarlands = value;
    }

    @Override
    public void setShiftFarlands(boolean value) {
        shiftFarlands = value;
    }

    @Override
    public void setGenOffset(int value) {
        genOffset = value;
    }
}

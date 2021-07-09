package me.percydan.borderremover.mixins;

import me.percydan.borderremover.config.OptionAccess;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptions.class)
public abstract class MixinGameOptions implements OptionAccess {
    private int genOffset = 0;
    private String YScale = "default";
    private String XZScale = "default";
    private boolean enableFarlands;

    @Inject(method = "accept", at = @At("RETURN"))
    private void accept(GameOptions.Visitor visitor, CallbackInfo ci) {
        genOffset = visitor.visitInt("genOffset", genOffset);
        YScale = visitor.visitString("yScale", YScale);
        XZScale = visitor.visitString("xzScale", XZScale);
        enableFarlands = visitor.visitBoolean("enableFarlands", enableFarlands);
    }

    @Override
    public int getGenOffset() {
        return genOffset;
    }

    @Override
    public void setGenOffset(int value) {
        genOffset = value;
    }

    @Override
    public boolean getEnableFarlands() {
        return enableFarlands;
    }

    @Override
    public void setEnableFarlands(boolean value) {
        enableFarlands = value;
    }

    @Override
    public String getXZScale() {
        return XZScale;
    }

    @Override
    public void setXZScale(String value) {
        XZScale = value;
    }

    @Override
    public String getYScale() {
        return YScale;
    }

    @Override
    public void setYScale(String value) {
        YScale = value;
    }
}

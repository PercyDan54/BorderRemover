package me.percydan.borderremover.config;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

public class WorldGenOptionsScreen extends Screen {
    private static final Option[] OPTIONS = new Option[]{FarlandsOptions.FARLANDS, FarlandsOptions.SHIFT_FARLANDS, FarlandsOptions.GEN_OFFSET};
    private final Screen lastScreen;
    private final GameOptions options;

    public WorldGenOptionsScreen(Screen lastScreen, GameOptions options) {
        super(new TranslatableText("farlands.options.title"));
        this.lastScreen = lastScreen;
        this.options = options;
    }

    protected void init() {
        int i = 0;

        for(Option option : OPTIONS) {
            int j = this.width / 2 - 155 + i % 2 * 160;
            int k = this.height / 6 - 12 + 24 * (i >> 1);
            this.addDrawableChild(option.createButton(this.options, j, k, 150));
            ++i;
        }
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 168, 200, 20, ScreenTexts.DONE, (p_213056_1_) -> {
            this.client.openScreen(this.lastScreen);
        }));
    }

    public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        this.renderBackground(p_230430_1_);
        drawCenteredText(p_230430_1_, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
    }

    class FarlandsOptions{
        private static OptionAccess getOptions(){
            return  ((OptionAccess) MinecraftClient.getInstance().options);
        }

        public static final DoubleOption GEN_OFFSET = new DoubleOption("farlands.options.offset", 0, 8, 1, (p_216658_0_) -> {
            return (double) getOptions().getGenOffset();
        }, (p_216579_0_, p_216579_1_) -> {
            getOptions().setGenOffset(p_216579_1_.intValue());
        }, (p_216664_0_, option) -> {
            int d0 = (int)option.get(p_216664_0_);
            return option.getGenericLabel(new LiteralText(String.format("%dx", d0)));
        });

        public static final CyclingOption<Boolean> FARLANDS = CyclingOption.create("farlands.options.enable", (gameOptions) -> {
            return getOptions().getEnableFarlands();
        }, (gameOptions, option, enableFarlands) -> {
            ((OptionAccess) MinecraftClient.getInstance().options).setEnableFarlands(enableFarlands);
        });

        public static final CyclingOption<Boolean> SHIFT_FARLANDS = CyclingOption.create("farlands.options.shift", (gameOptions) -> {
            return getOptions().getShiftFarlands();
        }, (gameOptions, option, shiftFarlands) -> {
            getOptions().setShiftFarlands(shiftFarlands);
        });
    }
}

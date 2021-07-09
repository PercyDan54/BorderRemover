package me.percydan.borderremover.config;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

public class WorldGenOptionsScreen extends Screen {
    private final Screen lastScreen;
    private final GameOptions options;
    private TextFieldWidget genOffsetTextField;
    private TextFieldWidget yScaleTextField;
    private TextFieldWidget xzScaleTextField;

    public WorldGenOptionsScreen(Screen lastScreen, GameOptions options) {
        super(new TranslatableText("farlands.options.title"));
        this.lastScreen = lastScreen;
        this.options = options;
    }

    protected void init() {
        int j = this.width / 2 - 155;
        int k = this.height / 6 - 12;
        this.addDrawableChild(FarlandsOptions.FARLANDS.createButton(this.options, j, k, 150));

        genOffsetTextField = new TextFieldWidget(this.textRenderer, width / 2 - 155 + 160, height / 6 - 12 + 24, 100, 20, null, null);
        genOffsetTextField.setText(String.valueOf(FarlandsOptions.getOptions().getGenOffset()));
        genOffsetTextField.setChangedListener((value) -> {
            try {
                FarlandsOptions.getOptions().setGenOffset(Integer.parseInt(value));
            } catch (NumberFormatException ignored) {
            }
        });

        xzScaleTextField = new TextFieldWidget(this.textRenderer, genOffsetTextField.x, genOffsetTextField.y + 25, 100, 20, null, null);
        xzScaleTextField.setText(String.valueOf(FarlandsOptions.getOptions().getXZScale()));
        xzScaleTextField.setChangedListener((value) -> {
            try {
                FarlandsOptions.getOptions().setXZScale(String.valueOf(Double.parseDouble(value)));
            } catch (NumberFormatException ignored) {
                if (value.equals("default")) FarlandsOptions.getOptions().setXZScale(value);
            }
        });

        yScaleTextField = new TextFieldWidget(this.textRenderer, xzScaleTextField.x, xzScaleTextField.y + 25, 100, 20, null, null);
        yScaleTextField.setText(String.valueOf(FarlandsOptions.getOptions().getYScale()));
        yScaleTextField.setChangedListener((value) -> {
            try {
                FarlandsOptions.getOptions().setYScale(String.valueOf(Double.parseDouble(value)));
            } catch (NumberFormatException ignored) {
                if (value.equals("default")) FarlandsOptions.getOptions().setYScale(value);
            }
        });

        addSelectableChild(genOffsetTextField);
        addSelectableChild(xzScaleTextField);
        addSelectableChild(yScaleTextField);

        addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 168, 200, 20, ScreenTexts.DONE, (p_213056_1_) -> {
            client.openScreen(lastScreen);
        }));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        drawCenteredText(matrices, textRenderer, this.title, this.width / 2, 15, 16777215);
        drawTextWithShadow(matrices, textRenderer, new TranslatableText("farlands.options.offset"), genOffsetTextField.x - 150, genOffsetTextField.y + 10, 16777215);
        drawStringWithShadow(matrices, textRenderer, "XZ Scale", xzScaleTextField.x - 60, xzScaleTextField.y + 10, 16777215);
        drawStringWithShadow(matrices, textRenderer, "Y Scale", yScaleTextField.x - 55, yScaleTextField.y + 10, 16777215);
        genOffsetTextField.render(matrices, mouseX, mouseY, delta);
        xzScaleTextField.render(matrices, mouseX, mouseY, delta);
        yScaleTextField.render(matrices, mouseX, mouseY, delta);
        super.render(matrices, mouseX, mouseY, delta);
    }

    class FarlandsOptions {
        public static final CyclingOption<Boolean> FARLANDS = CyclingOption.create("farlands.options.enable", (gameOptions) -> {
            return getOptions().getEnableFarlands();
        }, (gameOptions, option, enableFarlands) -> {
            getOptions().setEnableFarlands(enableFarlands);
        });

        private static OptionAccess getOptions() {
            return ((OptionAccess) MinecraftClient.getInstance().options);
        }
    }
}

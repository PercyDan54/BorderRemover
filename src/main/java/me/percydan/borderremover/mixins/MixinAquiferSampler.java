package me.percydan.borderremover.mixins;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.chunk.AquiferSampler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AquiferSampler.Impl.class)
public abstract class MixinAquiferSampler {
    @Shadow
    @Final
    private int startX;

    @Shadow
    @Final
    private int startY;

    @Shadow
    @Final
    private int startZ;

    @Shadow
    @Final
    private int sizeX;

    @Shadow
    @Final
    private int sizeZ;

    @Shadow
    @Final
    private AquiferSampler.FluidLevel[] waterLevels;

    /**
     * @author
     */
    @Overwrite
    private int index(int x, int y, int z) {
        int i = x - startX;
        int j = y - startY;
        int k = z - startZ;
        int dx = (j * sizeZ + k) * sizeX + i;
        return MathHelper.clamp(dx, 0, waterLevels.length - 1);
    }
}

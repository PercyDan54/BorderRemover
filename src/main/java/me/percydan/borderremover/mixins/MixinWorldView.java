package me.percydan.borderremover.mixins;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(WorldView.class)
public interface MixinWorldView extends BlockRenderView {
    /**
     * @author PercyDan
     */
    @Overwrite
    default int getLightLevel(BlockPos pos, int ambientDarkness) {
        return this.getBaseLightLevel(pos, ambientDarkness);
    }
}

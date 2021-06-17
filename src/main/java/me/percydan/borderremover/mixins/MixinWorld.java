package me.percydan.borderremover.mixins;

import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public abstract class MixinWorld implements WorldAccess {
    @Inject(method = "isValidHorizontally", at = @At("RETURN"), cancellable = true)
    private static void isValidHorizontally(CallbackInfoReturnable<Boolean> ci) {
        ci.setReturnValue(true);
    }

    /**
     * @author percydan
     */
    @Overwrite
    public int getTopY(Heightmap.Type heightmap, int x, int z) {
        int k;
        if (this.isChunkLoaded(ChunkSectionPos.getSectionCoord(x), ChunkSectionPos.getSectionCoord(z))) {
            k = this.getChunk(ChunkSectionPos.getSectionCoord(x), ChunkSectionPos.getSectionCoord(z)).sampleHeightmap(heightmap, x & 15, z & 15) + 1;
        } else {
            k = this.getBottomY();
        }

        return k;
    }
}

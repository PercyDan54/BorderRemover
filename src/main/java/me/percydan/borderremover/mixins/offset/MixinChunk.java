package me.percydan.borderremover.mixins.offset;

import me.percydan.borderremover.BorderRemover;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Chunk.class)
public abstract class MixinChunk {
    @ModifyVariable(method = "populateBiomes", at = @At("STORE"))
    private ChunkPos applyOffset(ChunkPos chunkPos) {
        int offset = BorderRemover.config.genOffset;
        return new ChunkPos(chunkPos.x + offset, chunkPos.z + offset);
    }
}

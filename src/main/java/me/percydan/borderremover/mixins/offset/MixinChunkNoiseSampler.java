package me.percydan.borderremover.mixins.offset;

import me.percydan.borderremover.BorderRemover;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.ChunkNoiseSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChunkNoiseSampler.class)
public abstract class MixinChunkNoiseSampler {
    @Redirect(method = "create", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/Chunk;getPos()Lnet/minecraft/util/math/ChunkPos;"))
    private static ChunkPos applyOffset(Chunk chunk) {
        ChunkPos chunkPos = chunk.getPos();
        int offset = BorderRemover.config.genOffset;
        return new ChunkPos(chunkPos.x + offset, chunkPos.z + offset);
    }
}

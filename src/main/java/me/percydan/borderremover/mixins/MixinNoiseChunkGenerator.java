package me.percydan.borderremover.mixins;

import me.percydan.borderremover.BorderRemover;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(NoiseChunkGenerator.class)
public abstract class MixinNoiseChunkGenerator {
    @ModifyVariable(method = "populateNoise(Lnet/minecraft/world/gen/StructureAccessor;Lnet/minecraft/world/chunk/Chunk;II)Lnet/minecraft/world/chunk/Chunk;", at = @At("STORE"))
    private ChunkPos applyOffset(ChunkPos pos) {
        int x = pos.x;
        int z = pos.z;
        int offset = BorderRemover.config.genOffset;

        return new ChunkPos(x + offset, z + offset);
    }
}

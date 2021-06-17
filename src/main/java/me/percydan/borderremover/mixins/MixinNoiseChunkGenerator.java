package me.percydan.borderremover.mixins;

import me.percydan.borderremover.config.OptionAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(NoiseChunkGenerator.class)
public abstract class MixinNoiseChunkGenerator {
    @ModifyVariable(method = "populateNoise(Lnet/minecraft/world/gen/StructureAccessor;Lnet/minecraft/world/chunk/Chunk;II)Lnet/minecraft/world/chunk/Chunk;", at = @At("STORE"))
    private ChunkPos applyOffset(ChunkPos pos){
        int x = pos.x;
        int z = pos.z;

        if(((OptionAccess) MinecraftClient.getInstance().options).getShiftFarlands()){
            x += 784425;
            z += 784425;
        }

        int offset = (int) (Integer.MAX_VALUE * (long)((OptionAccess) MinecraftClient.getInstance().options).getGenOffset() / 16);
        return new ChunkPos(x + offset, z + offset);
    }
}

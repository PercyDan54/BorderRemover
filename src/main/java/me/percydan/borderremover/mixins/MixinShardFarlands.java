package me.percydan.borderremover.mixins;

import me.percydan.borderremover.BorderRemover;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PerlinNoiseSampler.class)
public abstract class MixinShardFarlands {
    @Shadow
    @Final
    public double originX;
    @Shadow
    @Final
    public double originY;
    @Shadow
    @Final
    public double originZ;

    @Inject(method = "sample(DDDDD)D", at = @At("RETURN"), cancellable = true)
    public void sample(double x, double y, double z, double yScale, double yMax, CallbackInfoReturnable<Double> cir) {
        if (BorderRemover.config.shardFarlands) {
            double d = x + originX;
            double e = y + originY;
            double f = z + originZ;
            int i = MathHelper.floor(d);
            int j = MathHelper.floor(e);
            int k = MathHelper.floor(f);
            double g = d - (float) i;
            double h = e - (float) j;
            double l = f - (float) k;
            double n;
            if (yScale != 0.0) {
                double m;
                if (yMax >= 0.0 && yMax < h) {
                    m = yMax;
                } else {
                    m = h;
                }

                n = (double) MathHelper.floor(m / yScale + 1.0000000116860974E-7) * yScale;
            } else {
                n = 0.0;
            }

            cir.setReturnValue(sample(i, j, k, g, h - n, l, h));
        }
    }

    @Inject(method = "sampleDerivative(DDD[D)D", at = @At("RETURN"), cancellable = true)
    public void sampleDerivative(double x, double y, double z, double[] ds, CallbackInfoReturnable<Double> cir) {
        if (BorderRemover.config.shardFarlands) {
            double d = x + originX;
            double e = y + originY;
            double f = z + originZ;
            int i = MathHelper.floor(d);
            int j = MathHelper.floor(e);
            int k = MathHelper.floor(f);
            double g, h, l;
            g = d - (float) i;
            h = e - (float) j;
            l = f - (float) k;

            cir.setReturnValue(sampleDerivative(i, j, k, g, h, l, ds));
        }
    }

    @Shadow
    private double sampleDerivative(int sectionX, int sectionY, int sectionZ, double localX, double localY, double localZ, double[] ds) {
        throw new AssertionError();
    }

    @Shadow
    private double sample(int sectionX, int sectionY, int sectionZ, double localX, double localY, double localZ, double fadeLocalY) {
        throw new AssertionError();
    }
}

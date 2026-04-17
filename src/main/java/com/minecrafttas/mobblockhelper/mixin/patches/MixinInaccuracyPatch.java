package com.minecrafttas.mobblockhelper.mixin.patches;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.entity.projectile.EntityThrowable;

/**
 * Allows for removing the inaccuracy from throwables (is theoretically possible
 * if the stars align on 3 gaussian randoms)
 *
 * @author CittyKat
 */
@Mixin(EntityThrowable.class)
public abstract class MixinInaccuracyPatch {
	//# 1.12.2
//$$	@WrapOperation(method = "shoot(DDDFF)V", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextGaussian()D", remap = false))
	//# 1.11.2
	@WrapOperation(method = "setThrowableHeading", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextGaussian()D", remap = false))
	//# end
	private double redirect_internalShoot(Random rand, Operation<Double> original) {
		if (MobBlockHelper.isTASmodLoaded) {
			return 0;
		}
		return original.call(rand);
	}
}

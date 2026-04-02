package com.minecrafttas.mobblockhelper.mixin.patches;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

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
//	@Redirect(method = "shoot(DDDFF)V", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextGaussian()D", remap = false))
	@Redirect(method = "setThrowableHeading", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextGaussian()D", remap = false))
	private double redirect_internalShoot(Random random) {

		if (MobBlockHelper.isTASmodLoaded) {
			return 0;
		} else {
			return random.nextGaussian();
		}
	}
}

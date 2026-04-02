package com.minecrafttas.mobblockhelper.mixin.entity;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

@Mixin(EntityArrow.class)
public abstract class MixinEntityArrow extends Entity {

	public MixinEntityArrow(World world) {
		super(world);
	}

	@Shadow
	private double damage;
	
	/**
	 * 
	 * @param rand
	 * @param bound
	 * @return Always makes a fully charged bow shot a critical hit
	 */
	@Redirect(method = "onHit(Lnet/minecraft/util/math/RayTraceResult;)V", 
			at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"))
	private int redirectRandom(Random rand, int bound) {
		float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
		int i = MathHelper.ceil(f * this.damage);
		if (MobBlockHelper.isTASmodLoaded) {
			return i / 2 + 2;
		} else {
			return rand.nextInt(i / 2 + 2);
		}
	}
}

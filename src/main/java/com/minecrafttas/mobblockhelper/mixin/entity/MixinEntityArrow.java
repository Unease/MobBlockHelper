package com.minecrafttas.mobblockhelper.mixin.entity;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 * 
 * @author Unease
 */
@Mixin(EntityArrow.class)
public abstract class MixinEntityArrow extends Entity {

	public MixinEntityArrow(World world) {
		super(world);
	}

	@Shadow
	private double damage;
	
	/**
	 * Always makes full bow shots critical
	 */
	@WrapOperation(method = "onHit(Lnet/minecraft/util/math/RayTraceResult;)V", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"))
	private int redirectRandom(Random rand, int bound, Operation<Integer> original) {
		float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
		int i = MathHelper.ceil(f * this.damage);
		if (MobBlockHelper.isTASmodLoaded) {
			return i / 2 + 2;
		}
		return original.call(rand, bound);
	}
}

package com.minecrafttas.mobblockhelper.mixin.entity.mobs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.entity.monster.EntityZombie;

/**
 * @author Unease
 */
@Mixin(EntityZombie.class)
public class MixinEntityZombie {

	/**
	 * Redirect the loot table to my own
	 */
	@WrapOperation(method = "onInitialSpawn(Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/entity/IEntityLivingData;)Lnet/minecraft/entity/IEntityLivingData;", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/monster/EntityZombie;setCanPickUpLoot(Z)V"))
	private void redirectPickUpLoot(EntityZombie zombie, boolean canPickup, Operation<Void> original) {
		if (MobBlockHelper.isTASmodLoaded) {
			zombie.setCanPickUpLoot(true); // For Diamonds to You!
		} else {
			original.call(zombie, canPickup);
		}
	}
}

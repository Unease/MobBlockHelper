package com.minecrafttas.mobblockhelper.mixin.entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.entity.monster.EntityZombie;

/**
 * @author Unease
 */
@Mixin(EntityZombie.class)
public class MixinEntityZombie {

	/**
	 * 
	 * Redirect the loot table to my own
	 */
	@Redirect(method = "onInitialSpawn(Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/entity/IEntityLivingData;)Lnet/minecraft/entity/IEntityLivingData;", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/monster/EntityZombie;setCanPickUpLoot(Z)V"))
	private void redirectPickUpLoot(EntityZombie zombie, boolean canPickup) {
		if (MobBlockHelper.isTASmodLoaded) {
			zombie.setCanPickUpLoot(true); // For Diamonds to You!
		} else {
			zombie.setCanPickUpLoot(canPickup);
		}
	}
}

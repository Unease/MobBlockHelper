package com.minecrafttas.mobblockhelper.mixin.entity.mobs;

import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.util.ResourceLocation;

/**
 * @author Unease
 */
@Mixin(EntityWitherSkeleton.class)
public class MixinEntityWitherSkeleton {

	/**
	 * 
	 * Redirect the loot table to my own
	 */
	@Inject(method = "getLootTable", at = @At("HEAD"), cancellable = true)
	private void onGetLootTable(CallbackInfoReturnable<ResourceLocation> cir) {
		if (MobBlockHelper.isTASmodLoaded) {
			cir.setReturnValue(new ResourceLocation("mobblockhelper", "entities/wither_skeleton"));
		} else {
			cir.setReturnValue(new ResourceLocation("minecraft", "entities/wither_skeleton"));
		}
	}
}

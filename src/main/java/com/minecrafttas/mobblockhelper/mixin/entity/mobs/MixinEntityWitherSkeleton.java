package com.minecrafttas.mobblockhelper.mixin.entity.mobs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
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
	@ModifyReturnValue(method = "getLootTable", at = @At("RETURN"))
	private ResourceLocation returnCustomTable(ResourceLocation loc) {
		if (MobBlockHelper.isTASmodLoaded) {
			return new ResourceLocation("mobblockhelper", "entities/wither_skeleton");
		} else {
			return new ResourceLocation("minecraft", "entities/wither_skeleton"); 
		}
	}
}

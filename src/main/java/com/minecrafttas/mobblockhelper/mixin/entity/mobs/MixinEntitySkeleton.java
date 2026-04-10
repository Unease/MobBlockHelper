package com.minecrafttas.mobblockhelper.mixin.entity.mobs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.ResourceLocation;

/**
 * @author Unease
 */
@Mixin(EntitySkeleton.class)
public class MixinEntitySkeleton {

	/**
	 * 
	 * Redirect the loot table to my own
	 */
	@ModifyReturnValue(method = "getLootTable", at = @At("RETURN"))
	private ResourceLocation returnCustomTable(ResourceLocation loc) {
		if (MobBlockHelper.isTASmodLoaded) {
			return new ResourceLocation("mobblockhelper", "entities/skeleton");
		} else {
			return new ResourceLocation("minecraft", "entities/skeleton"); 
		}
	}
}

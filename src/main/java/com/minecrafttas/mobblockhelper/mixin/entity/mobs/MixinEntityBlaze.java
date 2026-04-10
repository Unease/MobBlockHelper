package com.minecrafttas.mobblockhelper.mixin.entity.mobs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.ResourceLocation;

/**
 * @author Unease
 */
@Mixin(EntityBlaze.class)
public class MixinEntityBlaze {

	/**
	 * Redirect the loot table to my own
	 */
	@ModifyReturnValue(method = "getLootTable", at = @At("RETURN"))
	private ResourceLocation returnCustomTable(ResourceLocation loc) {
		if (MobBlockHelper.isTASmodLoaded) {
			return new ResourceLocation("mobblockhelper", "entities/blaze");
		}
		return new ResourceLocation("minecraft", "entities/blaze");
	}
}

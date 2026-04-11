package com.minecrafttas.mobblockhelper.mixin.patches;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * This mixin alters the behaviour whenever a block is being destroyed
 * 
 * @author Pancake
 * @since v1.0
 * @version v1.1
 */
@Mixin(Block.class)
public class MixinBlock {

	/**
	 * Changes the velocity of a dropped item when wanted
	 * 
	 * @return A Hijacked EntityItem
	 */
	@WrapOperation(method = "spawnAsEntity", at = @At(value = "NEW", target = "Lnet/minecraft/entity/item/EntityItem;<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/item/ItemStack;)Lnet/minecraft/entity/item/EntityItem;"))
	private static EntityItem moveItem(World w, double x, double y, double z, ItemStack stack, Operation<EntityItem> original) {
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		EntityItem it = new EntityItem(w, x, y, z, stack);
		try {
			if (MobBlockHelper.isTASmodLoaded) {
				double pX = player.posX - x;
				double pZ = player.posZ - z;
				if (pX > 0)
					pX = 1;
				if (pX < 0)
					pX = -1;
				if (pZ > 0)
					pZ = 1;
				if (pZ < 0)
					pZ = -1;
				it.motionX = pX * 0.1f;
				it.motionZ = pZ * 0.1f;
				return it;
			}
		} catch (Exception e) {
			// When called in loading screen
		}
		return original.call(w, x, y, z, stack);
	}
}

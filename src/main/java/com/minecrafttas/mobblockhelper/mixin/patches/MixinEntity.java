package com.minecrafttas.mobblockhelper.mixin.patches;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * This Mixin alters the drops of an entity when it dies
 * 
 * @author Pancake
 * @since v1.0
 * @version v1.0
 */
@Mixin(Entity.class)
public class MixinEntity {

	@Shadow
	private double posX;
	@Shadow
	private double posY;
	@Shadow
	private double posZ;

	/**
	 * Changes the velocity of a item dropped from an entity
	 * 
	 * @return Returns a new modified EntityItem with a custom velocity
	 */
	@Redirect(method = "entityDropItem", at = @At(value = "NEW", target = "Lnet/minecraft/entity/item/EntityItem;<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/item/ItemStack;)Lnet/minecraft/entity/item/EntityItem;"))
	public EntityItem moveItem(World w, double x, double y, double z, ItemStack stack) {

		EntityPlayerSP player = Minecraft.getMinecraft().player;
		EntityItem it = new EntityItem(w, posX, posY, posZ, stack);

		if (MobBlockHelper.isTASmodLoaded) {
			double pX = player.posX - posX;
			double pZ = player.posZ - posZ;
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
		}
		return it;
	}
}

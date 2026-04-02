package com.minecrafttas.mobblockhelper.mixin.entity;

import java.util.Arrays;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Unease
 */
@Mixin(EntityLiving.class)
public class MixinEntityLiving {

	/**
	 * This sets the drop chances for the armor and hand slots for any mob to 100%
	 * If you need to target the armor and hand separately you should copy this method and adjust using the
	 * ordinal argument in the @At injection point
	 */
	@Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/Arrays;fill([FF)V"))
	private void redirectArmorAndHandDropChances(float a[], float val, World world) {
		Arrays.fill(a, 1F);
	}
	
	
	/**
	 * Changes the durability of an item when dropped by a mob.
	 * Range can be anywhere from 1 to getMaxDamage -1 but here
	 * I set it to give me 75% remaining just to be realistic
	 */
	@Redirect(method = "dropEquipment(ZI)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;setItemDamage(I)V"))
	private void redirectItemDamage(ItemStack stack, int meta) {
		stack.setItemDamage(((int) stack.getMaxDamage() * 1/4));
	}
}

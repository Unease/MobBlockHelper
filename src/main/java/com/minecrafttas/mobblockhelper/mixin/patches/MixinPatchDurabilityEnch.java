package com.minecrafttas.mobblockhelper.mixin.patches;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

/**
 * This Mixin makes items with unbreaking unbreakable.
 * Original file written by Pancake
 * 
 * @author Unease
 */
@Mixin(EnchantmentDurability.class)
public class MixinPatchDurabilityEnch {

	@ModifyReturnValue(at = @At("RETURN"), method = "negateDamage")
	private static boolean disableDamage(boolean b, ItemStack stack, int level, Random rand) {
		if (MobBlockHelper.isTASmodLoaded) {
			if (level >= 1) {
				return true;
			}
		}
		return stack.getItem() instanceof ItemArmor && rand.nextFloat() < 0.6F ? false : rand.nextInt(level + 1) > 0; // Original return
	}
}

package com.minecrafttas.mobblockhelper.mixin.entity.mobs;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.entity.monster.EntityZombieVillager;

/**
 * @author Unease
 */
@Mixin(EntityZombieVillager.class)
public class MixinEntityZombieVillager {

	/**
	 * Makes the random wait for the conversion as minimum as possible when iron bars are present
	 */
	@Redirect(method = "getConversionProgress", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextFloat()F"))
	private float redirectRandomConversion(Random rand) {
		if (MobBlockHelper.isTASmodLoaded) {
			return 0F;
		} else {
			return rand.nextFloat();
		}
	}
}

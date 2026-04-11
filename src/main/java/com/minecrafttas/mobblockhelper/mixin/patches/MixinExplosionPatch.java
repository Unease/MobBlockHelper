package com.minecrafttas.mobblockhelper.mixin.patches;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.world.Explosion;

/**
 * This Mixin makes explosions drop a specific percent of items
 * 
 * @author Pancake
 * @since v1.1
 * @version v1.1
 */
@Mixin(Explosion.class)
public class MixinExplosionPatch {

	/**
	 * Modify the Explosion Randomizer
	 */
	@ModifyArg(method = "doExplosionB", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;dropBlockAsItemWithChance(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;FI)V"), index = 3)
	public float hijackExplosion(float orig) {
		if (MobBlockHelper.isTASmodLoaded) {
			return 1F;
		}
		return orig;
	}
}

package com.minecrafttas.mobblockhelper.mixin.block;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.block.BlockCrops;

@Mixin(BlockCrops.class)
public class MixinBlockCrops {

	@Redirect(method = "dropBlockAsItemWithChance(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;FI)V", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"))
	private int redirectRandom(Random rand, int i) {
		if (MobBlockHelper.isTASmodLoaded) {
			return 0; // Always drops the max amount from crops
		}
		return rand.nextInt(i);
	}
}

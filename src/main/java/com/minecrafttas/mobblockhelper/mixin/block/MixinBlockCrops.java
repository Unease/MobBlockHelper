package com.minecrafttas.mobblockhelper.mixin.block;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.block.BlockCrops;

/**
 * @author Unease
 */
@Mixin(BlockCrops.class)
public class MixinBlockCrops {

	@WrapOperation(method = "dropBlockAsItemWithChance(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;FI)V", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"))
	private int redirectRandom(Random rand, int i, Operation<Integer> original) {
		if (MobBlockHelper.isTASmodLoaded) {
			return 0; // Always drops the max amount from crops
		}
		return original.call(rand, i);
	}
}

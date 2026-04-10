package com.minecrafttas.mobblockhelper.mixin.block;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.block.BlockDeadBush;

/**
 * @author Unease
 */
@Mixin(BlockDeadBush.class)
public class MixinBlockDeadBush {

	@WrapOperation(method = "quantityDropped(Ljava/util/Random;)I", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"))
	private int redirectRandomSticks(Random rand, int i, Operation<Integer> original) {
		if (MobBlockHelper.isTASmodLoaded) {
			return 2; // Always drops the max amount of sticks
		}
		return original.call(rand, i);
	}
}

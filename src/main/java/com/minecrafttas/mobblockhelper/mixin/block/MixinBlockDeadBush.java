package com.minecrafttas.mobblockhelper.mixin.block;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.block.BlockDeadBush;

@Mixin(BlockDeadBush.class)
public class MixinBlockDeadBush {

	@Redirect(method = "quantityDropped(Ljava/util/Random;)I", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"))
	private int redirectRandomSticks(Random rand, int i) {
		if (MobBlockHelper.isTASmodLoaded) {
			return 2; // Always drops the max amount of sticks
		}
		return rand.nextInt(i);
	}
}

package com.minecrafttas.mobblockhelper.mixin.block;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.block.BlockGravel;

@Mixin(BlockGravel.class)
public class MixinBlockGravel {

	@Redirect(method = "getItemDropped(Lnet/minecraft/block/state/IBlockState;Ljava/util/Random;I)Lnet/minecraft/item/Item;", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"))
	private int redirectRandomFlint(Random rand, int i) {
		if (MobBlockHelper.isTASmodLoaded) {
			return 0; // Always drops flint
		}
		return rand.nextInt(i);
	}
}

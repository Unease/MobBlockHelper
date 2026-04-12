package com.minecrafttas.mobblockhelper.mixin.entity.mobs;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.entity.passive.EntityChicken;

@Mixin(EntityChicken.class)
public class MixinEntityChicken {

	@WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"))
	private int redirectInitialChickenLayTime(Random rand, int bound, Operation<Integer> original) {
		if (MobBlockHelper.isTASmodLoaded) {
			return 0; // Make the initial lay time exactly 5 minutes
		}
		return original.call(rand, bound);
	}

	@WrapOperation(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"))
	private int redirectChickenLayTimeAfterInitialEgg(Random rand, int bound, Operation<Integer> original) {
		if (MobBlockHelper.isTASmodLoaded) {
			return 0; // Make the lay time exactly 5 minutes after the first egg (I don't know why you would need this lol)
		}
		return original.call(rand, bound);
	}
}

package com.minecrafttas.mobblockhelper.mixin.entity.mobs;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.entity.passive.EntityOcelot;

@Mixin(EntityOcelot.class)
public class MixinEntityOcelot {

	//# 1.12.2
//$$	@WrapOperation(method = "processInteract(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/util/EnumHand;)Z", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 0))
//$$	private int redirectRandom0(Random rand, int bound, Operation<Integer> original) {
//$$		if (MobBlockHelper.isTASmodLoaded) {
//$$			return 0;
//$$		}
//$$		return original.call(rand, bound);
//$$	}
	//# end
}

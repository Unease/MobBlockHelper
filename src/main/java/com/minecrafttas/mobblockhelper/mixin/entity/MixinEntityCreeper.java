package com.minecrafttas.mobblockhelper.mixin.entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;

@Mixin(EntityCreeper.class)
public class MixinEntityCreeper {

    @Inject(method = "getLootTable", at = @At("HEAD"), cancellable = true)
    private void onGetLootTable(CallbackInfoReturnable<ResourceLocation> cir) {
		if (MobBlockHelper.isTASmodLoaded) {
			cir.setReturnValue(new ResourceLocation("mobblockhelper", "entities/creeper"));
		}
		cir.setReturnValue(new ResourceLocation("minecraft", "entities/creeper"));
    }
}

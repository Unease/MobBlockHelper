package com.minecrafttas.mobblockhelper.mixin.entity.mobs;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.minecrafttas.mobblockhelper.MobBlockHelper;

import net.minecraft.entity.monster.EntityElderGuardian;

/**
 * Explains the Elder Guardian's mining fatigue logic based on the internal
 * Minecraft timer. *
 * <p>
 * The Elder Guardian inflicts mining fatigue using the following modulus check:
 * 
 * <pre>{@code
 * (this.ticksExisted + this.getEntityId()) % 1200 == 0
 * }</pre>
 * 
 * *
 * <p>
 * Because this check only returns {@code true} once every 1,200 ticks (60
 * seconds), the timing of the effect depends entirely on the combination of the
 * Guardian's entity ID and its current age.
 * </p>
 * *
 * <h3>Examples of Timing Variance:</h3>
 * <ul>
 * <li><b>Guardian A (ID 1201):</b> At 1 tick of existence, it won't trigger for
 * another 1,199 ticks.</li>
 * <li><b>Guardian B (ID 1100):</b> At 1 tick of existence, it will trigger in
 * only 99 ticks.</li>
 * </ul>
 * *
 * <p>
 * Since entity IDs increment sequentially, the "cooldown" before the first
 * infliction is semi-random based on how many other mobs have spawned in the
 * world.
 * </p>
 * * <blockquote> <b>TAS Optimization:</b> Since the TAS enters and clears the
 * monument in under 60 seconds, we can manipulate the entity ID count to ensure
 * the modulus check never hits zero during our window, effectively remaining
 * immune to mining fatigue. </blockquote>
 * 
 * @author Unease
 */
@Mixin(EntityElderGuardian.class)
public class MixinEntityElderGuardian {

	@WrapOperation(method = "updateAITasks", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/monster/EntityElderGuardian;ticksExisted:I", opcode = Opcodes.GETFIELD))
	private int redirectTicksExisted(EntityElderGuardian entity, Operation<Integer> original) {
		if (MobBlockHelper.isTASmodLoaded) {
			return 0;
		}
		return original.call(entity);
	}

	@WrapOperation(method = "updateAITasks", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/monster/EntityElderGuardian;getEntityId()I"))
	private int redirectGetEntityID(EntityElderGuardian entity, Operation<Integer> original) {
		if (MobBlockHelper.isTASmodLoaded) {
			return 1201;
		}
		return original.call(entity);
	}
}

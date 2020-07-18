package tfar.curiosities.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.CreeperSwellGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.curiosities.A;
import tfar.curiosities.Curiosities;

@Mixin(CreeperSwellGoal.class)
public class CreeperSwellGoalMixin {
	@Shadow private LivingEntity creeperAttackTarget;

	@Shadow @Final private CreeperEntity swellingCreeper;

	@Inject(at = @At("RETURN"), method = "tick")
	private void init(CallbackInfo info) {
		if (creeperAttackTarget instanceof PlayerEntity) {
			A.getCurio(Curiosities.creeper_amulet,(PlayerEntity)creeperAttackTarget).ifPresent(stack -> swellingCreeper.setCreeperState(-1));
		}
	}
}

package net.astormofsorts.agotmod.mixin;

import net.astormofsorts.agotmod.util.TemperatureHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {

    @Shadow
    public abstract boolean hurt(@NotNull DamageSource pSource, float pAmount);

    protected PlayerMixin(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Unique
    private static final int COOLDOWN_TME = 100;
    @Unique
    private static final float DAMAGE_AMOUNT = 1.0F;
    @Unique
    private static final int TEMP_TO_DMG_MULTIPLIER = 1;

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        float temperature = TemperatureHelper.getTemperature(this.level(), this.blockPosition());
        if (TemperatureHelper.isCold(temperature) && this.getRandom().nextInt(COOLDOWN_TME) == 0) {
            this.hurt(this.damageSources().freeze(), DAMAGE_AMOUNT + Math.abs(temperature - TemperatureHelper.COLD_TEMP));
        } else if (TemperatureHelper.isHot(temperature) && this.getRandom().nextInt(COOLDOWN_TME) == 0) {
            this.hurt(this.damageSources().onFire(), DAMAGE_AMOUNT + Math.abs(temperature - TemperatureHelper.HOT_TEMP));
        }
    }

    @Override
    public boolean isFreezing() {
        return (super.isFreezing() || TemperatureHelper.isCold(TemperatureHelper.getTemperature(this.level(), this.blockPosition()))) && this.canFreeze();
    }
}

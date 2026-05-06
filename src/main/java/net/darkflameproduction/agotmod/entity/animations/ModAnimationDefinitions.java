package net.darkflameproduction.agotmod.entity.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

public class ModAnimationDefinitions {

    public static final RawAnimation DIE = RawAnimation.begin().thenPlay("misc.die");
    public static final RawAnimation ATTACK = RawAnimation.begin().thenPlay("misc.attack");
    public static final RawAnimation SPAWN = RawAnimation.begin().thenPlay("misc.spawn");
    public static final RawAnimation WALK = RawAnimation.begin().thenPlay("misc.walk");
    public static final RawAnimation RUN = RawAnimation.begin().thenPlay("misc.run");
    public static final RawAnimation IDLE = RawAnimation.begin().thenPlay("misc.idle");
    public static final RawAnimation FLY = RawAnimation.begin().thenPlay("misc.flying");
    public static final RawAnimation SIT = RawAnimation.begin().thenPlay("misc.sit");
    public static final RawAnimation INTERRACT = RawAnimation.begin().thenPlay("misc.interract");

    public static <T extends LivingEntity & GeoAnimatable> AnimationController<T> ModdedDeathController(T animatable) {
        return new AnimationController<>(animatable, "Die", 0, state -> state.getAnimatable().isDeadOrDying() ? state.setAndContinue(DIE) : PlayState.STOP);
    }

    public static <T extends LivingEntity & GeoAnimatable> AnimationController<T> ModdedSummonController(T animatable) {
        return new AnimationController<>(animatable, "Spawn", 0, state -> {
            if (animatable.tickCount < 100)
                return state.setAndContinue(SPAWN);

            state.getController().forceAnimationReset();

            return PlayState.STOP;
        });
    }

    public static <T extends LivingEntity & GeoAnimatable> AnimationController<T> ModdedAttackController(T animatable) {
        return new AnimationController<>(animatable, "Attack", 0, state -> {
            if (state.getAnimatable().swinging) {
                return state.setAndContinue(ATTACK);
            }
            state.getController().forceAnimationReset();
            return PlayState.STOP;
        });
    }
}
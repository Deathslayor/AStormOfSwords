package net.darkflameproduction.agotmod.entity.animations;

import software.bernie.geckolib.animatable.processing.AnimationController;
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

    public static AnimationController<?> ModdedDeathController() {
        return new AnimationController<>("Die", 0, animationTest -> animationTest.setAndContinue(DIE));
    }

    public static AnimationController<?> ModdedSummonController() {
        return new AnimationController<>("Spawn", 0, animationTest -> animationTest.setAndContinue(SPAWN));
    }

    public static AnimationController<?> ModdedAttackController() {
        return new AnimationController<>("Attack", 0, animationTest -> animationTest.setAndContinue(ATTACK));
    }

    public static AnimationController<?> ModdedWalkController() {
        return new AnimationController<>("Walk", 5, animationTest -> animationTest.setAndContinue(WALK));
    }

    public static AnimationController<?> ModdedRunController() {
        return new AnimationController<>("Run", 5, animationTest -> animationTest.setAndContinue(RUN));
    }

    public static AnimationController<?> ModdedIdleController() {
        return new AnimationController<>("Idle", 5, animationTest -> animationTest.setAndContinue(IDLE));
    }

    public static AnimationController<?> ModdedFlyController() {
        return new AnimationController<>("Fly", 5, animationTest -> animationTest.setAndContinue(FLY));
    }

    public static AnimationController<?> ModdedSitController() {
        return new AnimationController<>("Sit", 0, animationTest -> animationTest.setAndContinue(SIT));
    }

    public static AnimationController<?> ModdedInterractController() {
        return new AnimationController<>("Interract", 0, animationTest -> animationTest.setAndContinue(INTERRACT));
    }
}
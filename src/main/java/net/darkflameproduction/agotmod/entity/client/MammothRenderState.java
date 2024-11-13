package net.darkflameproduction.agotmod.entity.client;

import net.darkflameproduction.agotmod.entity.variant.MammothVariant;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class MammothRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public MammothVariant variant = MammothVariant.DEFAULT;
}

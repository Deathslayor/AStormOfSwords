package net.darkflameproduction.agotmod.particle;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, AGoTMod.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WEIRWOOD_LEAVES =
            PARTICLE_TYPES.register("weirwood_leaves", () -> new SimpleParticleType(false));

    private ModParticles() {
    }

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}


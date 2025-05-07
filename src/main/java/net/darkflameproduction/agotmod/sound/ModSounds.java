
package net.darkflameproduction.agotmod.sound;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    //Sounds Deferred Register
    public static final DeferredRegister<SoundEvent> SOUNDS_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, AGoTMod.MOD_ID);


    public static final DeferredHolder<SoundEvent, SoundEvent> MAMMOTH_SOUNDS = registerSoundEvents("mammoth_sounds");
    public static final DeferredHolder<SoundEvent, SoundEvent> MAMMOTH_ATTACK = registerSoundEvents("mammoth_attack");
    public static final DeferredHolder<SoundEvent, SoundEvent> MAMMOTH_HURT = registerSoundEvents("mammoth_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> MAMMOTH_AMBIENT = registerSoundEvents("mammoth_ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> MAMMOTH_DEATH = registerSoundEvents("mammoth_death");
    public static final DeferredHolder<SoundEvent, SoundEvent> WINTER_WIND = registerSoundEvents("winter_wind");
    public static final DeferredHolder<SoundEvent, SoundEvent> FOREST_WIND = registerSoundEvents("forest_wind");

    //Helper Method to register the sounds
    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvents(String name) {
        ResourceLocation id = AGoTMod.id(name);
        return SOUNDS_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUNDS_EVENTS.register(eventBus);
    }
}
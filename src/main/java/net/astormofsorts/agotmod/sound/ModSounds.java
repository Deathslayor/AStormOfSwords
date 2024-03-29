package net.astormofsorts.agotmod.sound;

import net.astormofsorts.agotmod.AGoTMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModSounds {
    //Sounds Deferred Register
    public static final DeferredRegister<SoundEvent> SOUNDS_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, AGoTMod.MOD_ID);
    public static final RegistryObject<SoundEvent> MAMMOTH_SOUNDS = registerSoundEvents("mammoth_sounds");

    public static final RegistryObject<SoundEvent> MAMMOTH_IDLE = registerSoundEvents("mammoth_idle");
    public static final RegistryObject<SoundEvent> MAMMOTH_ATTACK = registerSoundEvents("mammoth_attack");
    public static final RegistryObject<SoundEvent> MAMMOTH_DAMAGED = registerSoundEvents("mammoth_damaged");
    public static final RegistryObject<SoundEvent> MAMMOTH_BABY_IDLE = registerSoundEvents("mammoth_baby_idle");
    public static final RegistryObject<SoundEvent> MAMMOTH_BABY_DAMAGED = registerSoundEvents("mammoth_baby_damaged");


    //Helper Method to register the sounds
    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        ResourceLocation id = new ResourceLocation(AGoTMod.MOD_ID, name);
        return SOUNDS_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }


    public static void register(IEventBus eventBus) {
        SOUNDS_EVENTS.register(eventBus);
    }

}





































































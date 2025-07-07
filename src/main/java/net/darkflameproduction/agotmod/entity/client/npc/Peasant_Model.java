package net.darkflameproduction.agotmod.entity.client.npc;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class Peasant_Model extends DefaultedEntityGeoModel<Peasant_Entity> {

    public Peasant_Model() {
        super(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "peasant"));
    }
}
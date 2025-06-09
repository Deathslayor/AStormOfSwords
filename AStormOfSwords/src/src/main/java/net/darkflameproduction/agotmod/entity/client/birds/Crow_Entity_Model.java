package net.darkflameproduction.agotmod.entity.client.birds;



import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.birds.Crow_Entity;
import net.darkflameproduction.agotmod.entity.custom.norththewall.Mammoth_Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class Crow_Entity_Model extends DefaultedEntityGeoModel<Crow_Entity> {


    public Crow_Entity_Model() {
        super(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "crow"),true);
    }





}

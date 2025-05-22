package net.darkflameproduction.agotmod.entity.client.npc;



import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class Northern_Peasant_Model extends DefaultedEntityGeoModel<Northern_Peasant_Entity> {


    public Northern_Peasant_Model() {
        super(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "northern_peasant"),true);
    }





}

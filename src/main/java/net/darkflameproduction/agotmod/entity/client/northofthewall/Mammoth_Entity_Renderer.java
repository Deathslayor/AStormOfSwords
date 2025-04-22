package net.darkflameproduction.agotmod.entity.client.northofthewall;



import net.darkflameproduction.agotmod.entity.custom.norththewall.Mammoth_Entity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Mammoth_Entity_Renderer extends GeoEntityRenderer<Mammoth_Entity> {


    public Mammoth_Entity_Renderer(EntityRendererProvider.Context context) {
        super(context, new Mammoth_Entity_Model());
    }
}

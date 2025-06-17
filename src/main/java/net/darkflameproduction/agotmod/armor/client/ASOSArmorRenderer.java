package net.darkflameproduction.agotmod.armor.client;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.darkflameproduction.agotmod.armor.custom.ASOSArmorItem;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.List;
import java.util.Set;

public abstract class ASOSArmorRenderer<R extends HumanoidRenderState & GeoRenderState> extends GeoArmorRenderer<ASOSArmorItem, R> {
    public ASOSArmorRenderer(ASOSArmorModel model) {
        // Using DefaultedItemGeoModel like this puts our 'location' as item/armor/example armor in the assets folders.
        super(model);
    }

    // Capture the full set effect state for later
    @Override
    public void addRenderData(ASOSArmorItem animatable, @NotNull RenderData relatedObject, R renderState) {
        Set<Item> wornArmor = new ObjectOpenHashSet<>(4);

        boolean fullSetEffect = false;

        // We don't want armor stands to do the idle animation
        if (!(relatedObject.entity() instanceof ArmorStand)) {
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR)
                    wornArmor.add(relatedObject.entity().getItemBySlot(slot).getItem());
            }

            fullSetEffect = wornArmor.containsAll(fullArmorSet());
        }

        renderState.addGeckolibData(ASOSArmorItem.HAS_FULL_SET_EFFECT, fullSetEffect);
    }

    protected abstract List<Item> fullArmorSet();
}
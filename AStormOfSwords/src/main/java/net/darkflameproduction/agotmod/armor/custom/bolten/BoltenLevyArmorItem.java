// This code belongs to the package net.stormofsorts.agotmod.custom
package net.darkflameproduction.agotmod.armor.custom.bolten;

// Importing necessary classes from other packages

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.darkflameproduction.agotmod.armor.client.bolten.BoltenLevyArmorRenderer;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentModel;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Set;
import java.util.function.Consumer;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.player.Player;

// Stark1ArmorItem class extending ArmorItem and implementing GeoItem
public class BoltenLevyArmorItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final ArmorType armorType;

    public BoltenLevyArmorItem(ArmorMaterial armorMaterial, ArmorType type, Properties properties) {
        super(armorMaterial, type, properties);
        this.armorType = type;
    }
    
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        
        // We'll handle the skin layers in a special client event handler instead
        // This approach won't work directly in the item class
    }
    
    /**
     * Gets the armor type for this item.
     * Can be used by client code to determine which skin layers to hide.
     */
    public ArmorType getArmorType() {
        return this.armorType;
    }

    // Create our armor model/renderer for Fabric and return it
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public <E extends LivingEntity, S extends HumanoidRenderState> @NotNull HumanoidModel<?> getGeoArmorRenderer(@Nullable E livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, EquipmentModel.LayerType type, HumanoidModel<S> original) {
                if (this.renderer == null) {
                    this.renderer = new BoltenLevyArmorRenderer();
                }

                return this.renderer;
            }
        });
    }

    // Let's add our animation controller
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, 20, state -> {
            // Apply our generic idle animation.
            // Whether it plays or not is decided down below.
            state.getController().setAnimation(DefaultAnimations.IDLE);

            // Let's gather some data from the state to use below
            // This is the entity that is currently wearing/holding the item
            Entity entity = state.getData(DataTickets.ENTITY);

            // We'll just have ArmorStands always animate, so we can return here
            if (entity instanceof ArmorStand)
                return PlayState.CONTINUE;

            // For this example, we only want the animation to play if the entity is wearing all pieces of the armor
            // Let's collect the armor pieces the entity is currently wearing
            Set<Item> wornArmor = new ObjectOpenHashSet<>();

            if (entity instanceof LivingEntity) {
                for (ItemStack stack : ((LivingEntity) entity).getArmorSlots()) {
                    // We can stop immediately if any of the slots are empty
                    if (stack.isEmpty())
                        return PlayState.STOP;

                    wornArmor.add(stack.getItem());
                }
            }

            // Check each of the pieces match our set
            boolean isFullSet = wornArmor.containsAll(ObjectArrayList.of(
                    ModItems.BOLTEN_LEVY_HELMET.get(),
                    ModItems.BOLTEN_LEVY_CHESTPLATE.get(),
                    ModItems.BOLTEN_LEVY_LEGGINGS.get(),
                    ModItems.BOLTEN_LEVY_BOOTS.get()));

            // Play the animation if the full set is being worn, otherwise stop
            return isFullSet ? PlayState.CONTINUE : PlayState.STOP;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}

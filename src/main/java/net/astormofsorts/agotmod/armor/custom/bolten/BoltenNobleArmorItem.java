// This code belongs to the package net.stormofsorts.agotmod.custom
package net.astormofsorts.agotmod.armor.custom.bolten;

// Importing necessary classes from other packages
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.astormofsorts.agotmod.armor.client.bolten.BoltenNobleArmorRenderer;
import net.astormofsorts.agotmod.armor.client.manderly.ManderlyNobleArmorRenderer;
import net.astormofsorts.agotmod.armor.custom.ModArmorMaterials;
import net.astormofsorts.agotmod.item.ModItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.antlr.v4.runtime.misc.OrderedHashSet;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Set;
import java.util.function.Consumer;

// Stark1ArmorItem class extending ArmorItem and implementing GeoItem
public class BoltenNobleArmorItem extends ArmorItem implements GeoItem {
    // Instance of AnimatableInstanceCache to manage animations
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    // Constructor for Stark1ArmorItem
    public BoltenNobleArmorItem(ModArmorMaterials modArmorMaterials, Type type, Properties properties) {
        super(modArmorMaterials, type, properties);
    }

    // Initialize client-side rendering
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                // Lazy initialization of the renderer
                if (this.renderer == null)
                    this.renderer = new BoltenNobleArmorRenderer();

                // Prepare the renderer for rendering
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                // Return the renderer
                return this.renderer;
            }
        });
    }

    // Register animation controllers
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<GeoAnimatable>(this, 20, state -> {
            // Apply generic idle animation
            state.setAnimation(DefaultAnimations.IDLE);

            // Get entity data
            Entity entity = state.getData(DataTickets.ENTITY);

            // Always animate ArmorStands
            if (entity instanceof ArmorStand)
                return PlayState.CONTINUE;

            // Collect armor pieces worn by the entity
            Set<Item> wornArmor = new OrderedHashSet<>();

            for (ItemStack stack : entity.getArmorSlots()) {
                // Stop immediately if any slot is empty
                if (stack.isEmpty())
                    return PlayState.STOP;

                wornArmor.add(stack.getItem());
            }

            // Check if the entity is wearing a full set of armor
            boolean isFullSet = wornArmor.containsAll(ObjectArrayList.of(
                    ModItems.BOLTEN_NOBLE_HELMET.get(),
                    ModItems.BOLTEN_NOBLE_CHESTPLATE.get(),
                    ModItems.BOLTEN_NOBLE_LEGGINGS.get(),
                    ModItems.BOLTEN_NOBLE_BOOTS.get()));

            // Continue playing the animation if the full set is worn, otherwise stop
            return isFullSet ? PlayState.CONTINUE : PlayState.STOP;
        }));
    }

    // Get the AnimatableInstanceCache
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}

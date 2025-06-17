package net.darkflameproduction.agotmod.armor.custom;

import net.darkflameproduction.agotmod.armor.client.ASOSArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.constant.dataticket.DataTicket;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public abstract class ASOSArmorItem extends Item implements GeoItem {
    // Create a custom DataTicket to check for the full set being worn
    // We will add this in our renderer
    public static final DataTicket<Boolean> HAS_FULL_SET_EFFECT = DataTicket.create("agotmod_has_full_set_effect", Boolean.class);

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public ASOSArmorItem(Properties properties) {
        super(properties);
    }

    // Create our armor model/renderer for Fabric and return it
    @Override
    public void createGeoRenderer(@NotNull Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<ASOSArmorItem, ?> renderer;

            @Override
            public <S extends HumanoidRenderState> @NotNull GeoArmorRenderer<?, ?> getGeoArmorRenderer(@org.jetbrains.annotations.Nullable S renderState, ItemStack itemStack, EquipmentSlot equipmentSlot, EquipmentClientInfo.LayerType type, @org.jetbrains.annotations.Nullable HumanoidModel<S> original) {
                if (this.renderer == null)
                    this.renderer = createArmorRenderer();

                return this.renderer;
            }
        });
    }

    // Let's add our animation controller
    @Override
    public void registerControllers(AnimatableManager.@NotNull ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(ASOSArmorItem.class.getSimpleName(), 20, animTest -> {
            // Play the animation if the full set is being worn, otherwise stop
            if (Boolean.TRUE.equals(animTest.getData(HAS_FULL_SET_EFFECT)))
                return animTest.setAndContinue(DefaultAnimations.IDLE);

            return PlayState.STOP;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    protected abstract ASOSArmorRenderer<?> createArmorRenderer();
}
package net.astormofsorts.agotmod.datagen;

import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.map.MapUtils;
import net.astormofsorts.agotmod.worldgen.biome.MapBiomeData;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class MapProvider implements DataProvider {
    private static final BufferedImage ORGINAL_MAP_IMAGE = getOriginalMapImage();

    private static BufferedImage getOriginalMapImage() {
        URL orignalMapUrl = AGoTMod.class.getResource("/assets/agotmod/map.png");
        try {
            return ImageIO.read(Objects.requireNonNull(orignalMapUrl));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load biome map located at: " + (orignalMapUrl != null ? orignalMapUrl.getPath() : "invalid location"), e);
        }
    }

    public static final String BIOME_MAP_PATH = "assets/agotmod/map_biome.png";
    public static final String HEIGHT_MAP_PATH = "assets/agotmod/map_height.png";

    private final PackOutput packOutput;
    private final CompletableFuture<HolderLookup.Provider> lookupProvider;

    public MapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        this.packOutput = packOutput;
        this.lookupProvider = lookupProvider;
    }

    @SuppressWarnings({"UnstableApiUsage", "deprecation"})
    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput cache) {
        return CompletableFuture.runAsync(() -> {
            HolderLookup.Provider provider = lookupProvider.join();
            HolderGetter<Biome> biomeRegistry = provider.lookupOrThrow(Registries.BIOME);
            List<MapBiomeData> biomeData = ModDimensionProvider.getBiomeData(biomeRegistry);
            Map<Color, Integer> heights = MapBiomeData.toHeightsMap(biomeData);

            try {
                BufferedImage validMap = MapUtils.removeInvalidColors(ORGINAL_MAP_IMAGE, heights);
                {
                    Path output = packOutput.getOutputFolder().resolve(BIOME_MAP_PATH);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(validMap, "PNG", baos);
                    HashingOutputStream hashStream = new HashingOutputStream(Hashing.sha1(), baos);
                    cache.writeIfNeeded(output, baos.toByteArray(), hashStream.hash());
                }

                BufferedImage heightmap = MapUtils.generateHeightMap(validMap, heights);
                {
                    Path output = packOutput.getOutputFolder().resolve(HEIGHT_MAP_PATH);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(heightmap, "PNG", baos);
                    HashingOutputStream hashStream = new HashingOutputStream(Hashing.sha1(), baos);
                    cache.writeIfNeeded(output, baos.toByteArray(), hashStream.hash());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public @NotNull String getName() {
        return "Generate Heightmap";
    }
}

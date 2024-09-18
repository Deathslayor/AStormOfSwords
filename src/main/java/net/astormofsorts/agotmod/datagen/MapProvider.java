package net.astormofsorts.agotmod.datagen;

import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.map.MapBiome;
import net.astormofsorts.agotmod.map.MapUtils;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class MapProvider implements DataProvider {
    public static final BufferedImage ORGINAL_MAP_IMAGE = getOriginalMapImage();
    @Nullable
    public static final BufferedImage OVERWRITE_BIOME_MAP_IMAGE = getOverwriteBiomeMapImage();

    private static BufferedImage getOriginalMapImage() {
        URL orignalMapUrl = AGoTMod.class.getResource("/assets/agotmod/map.png");
        try {
            return ImageIO.read(Objects.requireNonNull(orignalMapUrl));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load biome map located at: " + (orignalMapUrl != null ? orignalMapUrl.getPath() : "invalid location"), e);
        }
    }
    @Nullable
    private static BufferedImage getOverwriteBiomeMapImage() {
        URL orignalMapUrl = AGoTMod.class.getResource("/assets/agotmod/overwrite_biomes.png");
        try {
            if (orignalMapUrl != null) {
                return ImageIO.read(orignalMapUrl);
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load biome map located at: " + orignalMapUrl.getPath(), e);
        }
    }

    public static final String BIOME_MAP_PATH = "assets/agotmod/map_biome.png";

    private final PackOutput packOutput;

    public MapProvider(PackOutput packOutput) {
        this.packOutput = packOutput;
    }

    @SuppressWarnings({"UnstableApiUsage", "deprecation"})
    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput cache) {
        return CompletableFuture.runAsync(() -> {
            Map<Integer, Integer> heights = MapBiome.toHeightMap(MapBiome.BIOME_LIST);
            try {
                BufferedImage validOriginal = MapUtils.approachColors(ORGINAL_MAP_IMAGE, heights.keySet().stream().map(Color::new).toList());
                BufferedImage biomeMap = MapUtils.generateBiomeMap(validOriginal);
                BufferedImage overwrittenBiomeMap = MapUtils.acceptOverwriteMap(biomeMap, OVERWRITE_BIOME_MAP_IMAGE);
                {
                    Path output = packOutput.getOutputFolder().resolve(BIOME_MAP_PATH);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    HashingOutputStream hashStream = new HashingOutputStream(Hashing.sha1(), baos);
                    ImageIO.write(overwrittenBiomeMap, "PNG", hashStream);
                    cache.writeIfNeeded(output, baos.toByteArray(), hashStream.hash());
                }
            } catch (Exception e) {
                // should be fine since it's async
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public @NotNull String getName() {
        return "Generate Maps for map based chunk generation";
    }
}

package net.darkflameproduction.agotmod.entity.custom.npc.system.culture;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;

/**
 * Stores the culture assigned to a peasant and all rolled skin indices.
 * Indices are rolled exactly once on culture assignment and never change.
 * Variant counts are defined here (not in the renderer) so the server can roll them safely.
 */
public class CultureSystem {

    // ── Variant counts per group/gender/age ───────────────────────────────────
    // Order: body, eyes, legs, shirt, hair, boots, tunic, hood

    private static final int[] NORTH_MALE_COUNTS         = {6, 1, 1, 1, 24, 2, 4, 3};
    private static final int[] NORTH_FEMALE_COUNTS       = {6, 1, 1, 1,  1, 2, 4, 1};
    private static final int[] NORTH_CHILD_MALE_COUNTS   = {3, 1, 1, 1,  7, 2, 4, 1};
    private static final int[] NORTH_CHILD_FEMALE_COUNTS = {1, 1, 1, 1,  1, 1, 1, 1};

    private static final int[] WILDLING_MALE_COUNTS         = {6, 1, 1, 1, 24, 2, 4, 3};
    private static final int[] WILDLING_FEMALE_COUNTS       = {6, 1, 1, 1,  1, 2, 4, 1};
    private static final int[] WILDLING_CHILD_MALE_COUNTS   = {3, 1, 1, 1,  7, 2, 4, 1};
    private static final int[] WILDLING_CHILD_FEMALE_COUNTS = {1, 1, 1, 1,  1, 1, 1, 1};

    // Color palette sizes (must match renderer arrays)
    private static final int EYES_COLORS_COUNT       = 11;
    private static final int HAIR_COLORS_COUNT       = 23;
    private static final int SHIRT_COLORS_COUNT      = 27;
    private static final int PANTS_COLORS_COUNT      = 14;
    private static final int TUNIC_HOOD_COLORS_COUNT = 14;

    // ── Instance state ────────────────────────────────────────────────────────

    private Culture culture = Culture.NONE;

    private int bodyVariant    = -1;
    private int eyesVariant    = -1;
    private int legsVariant    = -1;
    private int shirtVariant   = -1;
    private int hairVariant    = -1;
    private int bootsVariant   = -1;
    private int tunicVariant   = -1;
    private int hoodVariant    = -1;

    private int eyesColorIdx   = -1;
    private int hairColorIdx   = -1;
    private int shirtColorIdx  = -1;
    private int pantsColorIdx  = -1;
    private int tunicColorIdx  = -1;
    private int hoodColorIdx   = -1;

    public boolean hasCulture() {
        return culture != Culture.NONE;
    }

    public Culture getCulture() {
        return culture;
    }

    public CultureGroup getGroup() {
        return culture.group;
    }

    /**
     * Assigns a culture and rolls all skin/color indices from server-side counts.
     * Safe to call on the server — no client classes involved.
     */
    public void assignCulture(Culture culture, RandomSource random, boolean female, boolean child) {
        this.culture = culture;
        int[] counts = getVariantCounts(culture.group, female, child);

        this.bodyVariant   = random.nextInt(Math.max(1, counts[0]));
        this.eyesVariant   = random.nextInt(Math.max(1, counts[1]));
        this.legsVariant   = random.nextInt(Math.max(1, counts[2]));
        this.shirtVariant  = random.nextInt(Math.max(1, counts[3]));
        this.hairVariant   = random.nextInt(Math.max(1, counts[4]));
        this.bootsVariant  = random.nextInt(Math.max(1, counts[5]));
        this.tunicVariant  = random.nextInt(Math.max(1, counts[6]));
        this.hoodVariant   = random.nextInt(Math.max(1, counts[7]));

        this.eyesColorIdx  = random.nextInt(EYES_COLORS_COUNT);
        this.hairColorIdx  = random.nextInt(HAIR_COLORS_COUNT);
        this.shirtColorIdx = random.nextInt(SHIRT_COLORS_COUNT);
        this.pantsColorIdx = random.nextInt(PANTS_COLORS_COUNT);
        this.tunicColorIdx = random.nextInt(TUNIC_HOOD_COLORS_COUNT);
        this.hoodColorIdx  = random.nextInt(TUNIC_HOOD_COLORS_COUNT);
    }

    public static int[] getVariantCounts(CultureGroup group, boolean female, boolean child) {
        return switch (group) {
            case WILDLING -> child
                    ? (female ? WILDLING_CHILD_FEMALE_COUNTS : WILDLING_CHILD_MALE_COUNTS)
                    : (female ? WILDLING_FEMALE_COUNTS       : WILDLING_MALE_COUNTS);
            default -> child
                    ? (female ? NORTH_CHILD_FEMALE_COUNTS   : NORTH_CHILD_MALE_COUNTS)
                    : (female ? NORTH_FEMALE_COUNTS         : NORTH_MALE_COUNTS);
        };
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    public int getBodyVariant()   { return Math.max(0, bodyVariant); }
    public int getEyesVariant()   { return Math.max(0, eyesVariant); }
    public int getLegsVariant()   { return Math.max(0, legsVariant); }
    public int getShirtVariant()  { return Math.max(0, shirtVariant); }
    public int getHairVariant()   { return Math.max(0, hairVariant); }
    public int getBootsVariant()  { return Math.max(0, bootsVariant); }
    public int getTunicVariant()  { return Math.max(0, tunicVariant); }
    public int getHoodVariant()   { return Math.max(0, hoodVariant); }
    public int getEyesColorIdx()  { return Math.max(0, eyesColorIdx); }
    public int getHairColorIdx()  { return Math.max(0, hairColorIdx); }
    public int getShirtColorIdx() { return Math.max(0, shirtColorIdx); }
    public int getPantsColorIdx() { return Math.max(0, pantsColorIdx); }
    public int getTunicColorIdx() { return Math.max(0, tunicColorIdx); }
    public int getHoodColorIdx()  { return Math.max(0, hoodColorIdx); }

    // ── Save / Load ───────────────────────────────────────────────────────────

    public void saveData(CompoundTag tag) {
        tag.putString("Culture", culture.name());
        tag.putInt("CultureBodyVariant",   bodyVariant);
        tag.putInt("CultureEyesVariant",   eyesVariant);
        tag.putInt("CultureLegsVariant",   legsVariant);
        tag.putInt("CultureShirtVariant",  shirtVariant);
        tag.putInt("CultureHairVariant",   hairVariant);
        tag.putInt("CultureBootsVariant",  bootsVariant);
        tag.putInt("CultureTunicVariant",  tunicVariant);
        tag.putInt("CultureHoodVariant",   hoodVariant);
        tag.putInt("CultureEyesColorIdx",  eyesColorIdx);
        tag.putInt("CultureHairColorIdx",  hairColorIdx);
        tag.putInt("CultureShirtColorIdx", shirtColorIdx);
        tag.putInt("CulturePantsColorIdx", pantsColorIdx);
        tag.putInt("CultureTunicColorIdx", tunicColorIdx);
        tag.putInt("CultureHoodColorIdx",  hoodColorIdx);
    }

    public void loadData(CompoundTag tag) {
        if (tag.contains("Culture")) {
            try { culture = Culture.valueOf(tag.getString("Culture")); }
            catch (IllegalArgumentException e) { culture = Culture.NONE; }
        }
        bodyVariant   = tag.contains("CultureBodyVariant")   ? tag.getInt("CultureBodyVariant")   : -1;
        eyesVariant   = tag.contains("CultureEyesVariant")   ? tag.getInt("CultureEyesVariant")   : -1;
        legsVariant   = tag.contains("CultureLegsVariant")   ? tag.getInt("CultureLegsVariant")   : -1;
        shirtVariant  = tag.contains("CultureShirtVariant")  ? tag.getInt("CultureShirtVariant")  : -1;
        hairVariant   = tag.contains("CultureHairVariant")   ? tag.getInt("CultureHairVariant")   : -1;
        bootsVariant  = tag.contains("CultureBootsVariant")  ? tag.getInt("CultureBootsVariant")  : -1;
        tunicVariant  = tag.contains("CultureTunicVariant")  ? tag.getInt("CultureTunicVariant")  : -1;
        hoodVariant   = tag.contains("CultureHoodVariant")   ? tag.getInt("CultureHoodVariant")   : -1;
        eyesColorIdx  = tag.contains("CultureEyesColorIdx")  ? tag.getInt("CultureEyesColorIdx")  : -1;
        hairColorIdx  = tag.contains("CultureHairColorIdx")  ? tag.getInt("CultureHairColorIdx")  : -1;
        shirtColorIdx = tag.contains("CultureShirtColorIdx") ? tag.getInt("CultureShirtColorIdx") : -1;
        pantsColorIdx = tag.contains("CulturePantsColorIdx") ? tag.getInt("CulturePantsColorIdx") : -1;
        tunicColorIdx = tag.contains("CultureTunicColorIdx") ? tag.getInt("CultureTunicColorIdx") : -1;
        hoodColorIdx  = tag.contains("CultureHoodColorIdx")  ? tag.getInt("CultureHoodColorIdx")  : -1;
    }
}

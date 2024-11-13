package net.darkflameproduction.agotmod.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum MammothVariant {
    DEFAULT(0),
    DARK(1),
    BLACK(2),
    WHITE(3);


    private static final MammothVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(MammothVariant::getId)).toArray(MammothVariant[]::new);
    private final int id;

    MammothVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static MammothVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}

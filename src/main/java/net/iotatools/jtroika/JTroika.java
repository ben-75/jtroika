package net.iotatools.jtroika;

import java.util.Arrays;

public class JTroika {

    private static final int NUM_ROUNDS = 24;
    private static final int TROIKA_RATE = 243;

    private static final int COLUMNS = 9;
    private static final int ROWS = 3;
    private static final int SLICES = 27;
    private static final int SLICESIZE = COLUMNS * ROWS;
    private static final int STATESIZE = COLUMNS * ROWS * SLICES;
    private static final int NUM_SBOXES = SLICES * ROWS * COLUMNS / 3;

    private static final int PADDING = 0x1;

    private static final int[][] round_constants = new int[][]{
            new int[]{2, 2, 2, 2, 1, 2, 0, 1, 0, 1, 1, 0, 2, 0, 1, 0, 1, 1, 0, 0, 1, 2, 1, 1, 1, 0, 0, 2, 0, 2, 1, 0, 2, 2, 2, 1, 0, 2, 2, 0, 0, 1, 2, 2, 1, 0, 1, 0, 1, 2, 2, 2, 0, 1, 2, 2, 1, 1, 2, 1, 1, 2, 0, 2, 0, 2, 0, 0, 0, 0, 2, 1, 1, 2, 1, 0, 1, 0, 2, 1, 1, 0, 0, 2, 2, 2, 2, 0, 1, 1, 2, 1, 2, 2, 0, 1, 2, 2, 2, 0, 1, 0, 2, 2, 0, 2, 1, 1, 2, 1, 2, 1, 0, 0, 2, 1, 0, 0, 1, 2, 2, 1, 1, 1, 0, 1, 0, 2, 2, 0, 2, 2, 2, 0, 2, 2, 1, 0, 0, 0, 2, 1, 0, 0, 1, 1, 1, 2, 2, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 2, 2, 1, 0, 1, 0, 2, 0, 1, 2, 0, 1, 2, 2, 2, 2, 1, 0, 0, 0, 0, 2, 1, 0, 2, 1, 1, 2, 0, 2, 1, 0, 0, 0, 1, 0, 2, 1, 2, 0, 1, 2, 1, 0, 2, 0, 2, 1, 0, 0, 1, 2, 0, 2, 2, 2, 0, 1, 0, 2, 0, 1, 0, 2, 1, 2, 1, 2, 2, 1, 1, 2, 0, 2, 2, 1, 0, 0, 2, 0, 2, 1, 0, 1},
            new int[]{1, 1, 1, 0, 2, 2, 0, 2, 0, 1, 0, 2, 1, 1, 0, 0, 1, 1, 1, 2, 0, 1, 1, 2, 0, 1, 1, 1, 2, 0, 2, 2, 2, 0, 2, 1, 1, 2, 1, 0, 2, 1, 0, 2, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 2, 0, 0, 0, 2, 1, 1, 0, 1, 2, 0, 1, 2, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 2, 2, 0, 2, 0, 2, 1, 0, 2, 1, 0, 0, 1, 2, 2, 0, 0, 0, 0, 1, 0, 2, 2, 2, 1, 1, 0, 1, 0, 2, 1, 2, 2, 2, 1, 0, 2, 2, 0, 2, 0, 1, 2, 1, 0, 1, 0, 0, 1, 1, 0, 1, 2, 2, 2, 0, 0, 1, 0, 0, 1, 2, 1, 1, 1, 2, 0, 0, 0, 2, 1, 0, 2, 1, 2, 2, 1, 2, 1, 0, 0, 0, 2, 0, 0, 0, 2, 2, 1, 2, 2, 0, 0, 1, 2, 2, 1, 0, 0, 2, 1, 2, 2, 2, 0, 1, 1, 1, 1, 2, 0, 1, 1, 2, 2, 1, 0, 1, 2, 0, 2, 2, 1, 0, 1, 2, 1, 0, 1, 0, 1, 1, 2, 1, 1, 2, 2, 2, 1, 0, 2, 0, 0, 0, 1, 1, 2, 1, 0, 2, 0, 1, 1, 1, 2},
            new int[]{0, 2, 0, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2, 2, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 2, 2, 0, 1, 0, 2, 2, 1, 2, 2, 2, 2, 1, 2, 1, 1, 0, 0, 1, 0, 2, 0, 2, 0, 1, 2, 0, 0, 2, 2, 2, 1, 1, 0, 0, 2, 0, 2, 2, 2, 2, 1, 2, 1, 0, 2, 0, 2, 0, 2, 0, 2, 2, 0, 2, 2, 1, 2, 1, 2, 0, 0, 0, 0, 1, 0, 2, 1, 1, 2, 1, 0, 1, 0, 2, 0, 1, 0, 0, 2, 2, 2, 2, 2, 1, 1, 0, 1, 2, 2, 0, 0, 2, 2, 1, 0, 1, 2, 2, 1, 0, 2, 1, 1, 2, 1, 2, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 2, 0, 2, 0, 0, 1, 0, 0, 1, 0, 2, 1, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 1, 0, 2, 0, 0, 1, 2, 0, 1, 1, 2, 2, 0, 2, 2, 0, 0, 2, 2, 1, 2, 0, 0, 0, 1, 0, 2, 1, 0, 1, 2, 1, 1, 0, 2, 0, 0, 2, 1, 1, 0, 1, 1, 2, 0, 0, 1, 1, 1, 0, 0, 2, 2, 2, 2, 1, 1, 2, 2},
            new int[]{1, 2, 2, 0, 2, 2, 0, 1, 0, 0, 0, 2, 0, 0, 0, 2, 1, 0, 2, 2, 0, 0, 1, 2, 1, 0, 0, 1, 0, 1, 2, 2, 1, 2, 1, 0, 0, 1, 1, 2, 0, 0, 2, 2, 1, 0, 1, 2, 2, 2, 0, 2, 1, 1, 2, 1, 2, 1, 1, 0, 2, 1, 0, 0, 1, 2, 0, 1, 1, 0, 0, 1, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 2, 0, 0, 0, 2, 1, 0, 0, 2, 0, 1, 1, 2, 1, 0, 1, 1, 0, 1, 2, 2, 0, 2, 2, 0, 0, 0, 1, 2, 2, 0, 0, 0, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 2, 2, 0, 1, 1, 2, 2, 2, 2, 2, 0, 2, 2, 2, 1, 1, 0, 0, 1, 0, 0, 2, 2, 2, 1, 2, 0, 0, 0, 1, 2, 2, 2, 0, 1, 2, 1, 1, 2, 2, 1, 1, 2, 0, 1, 0, 0, 1, 0, 2, 0, 2, 0, 1, 0, 0, 0, 2, 2, 2, 1, 1, 1, 0, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 0, 0, 0, 0, 0, 2, 2, 0, 2, 2, 1, 0, 0, 2, 2, 0, 0},
            new int[]{0, 1, 1, 1, 1, 2, 0, 1, 1, 1, 1, 1, 0, 1, 2, 0, 2, 1, 0, 0, 2, 0, 1, 0, 1, 2, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 2, 0, 0, 0, 1, 0, 2, 2, 1, 0, 0, 1, 1, 0, 2, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 2, 0, 2, 0, 2, 1, 2, 1, 1, 0, 1, 1, 2, 2, 2, 2, 0, 1, 0, 0, 2, 1, 1, 0, 1, 2, 1, 0, 1, 1, 1, 1, 0, 1, 1, 2, 2, 0, 1, 0, 2, 0, 0, 2, 1, 2, 2, 1, 2, 2, 0, 0, 1, 2, 0, 0, 0, 0, 2, 1, 2, 2, 0, 2, 1, 0, 2, 1, 2, 0, 2, 0, 2, 0, 0, 0, 2, 1, 1, 1, 2, 1, 0, 1, 2, 1, 1, 2, 1, 0, 2, 2, 1, 1, 0, 0, 0, 2, 0, 1, 1, 2, 1, 2, 2, 2, 0, 1, 2, 2, 0, 1, 0, 1, 1, 2, 0, 2, 2, 2, 1, 1, 0, 2, 2, 1, 0, 2, 2, 2, 1, 1, 1, 1, 1, 0, 1, 2, 2, 2, 2, 0, 0, 2, 0, 1, 2, 1, 0, 1, 1, 0, 0, 1, 0, 1, 2, 2, 0, 0, 2, 0, 0, 1, 1, 2, 2, 1, 0, 0, 0, 2, 1, 2, 1, 0, 1, 1, 2, 2, 1, 2},
            new int[]{2, 2, 2, 0, 2, 1, 0, 0, 0, 1, 0, 1, 0, 2, 0, 1, 2, 1, 0, 1, 2, 2, 2, 1, 0, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0, 0, 2, 1, 2, 1, 2, 1, 1, 2, 0, 1, 2, 2, 1, 2, 0, 0, 2, 0, 0, 2, 0, 0, 1, 2, 0, 0, 0, 0, 0, 0, 2, 2, 0, 2, 1, 0, 0, 0, 2, 2, 0, 0, 2, 0, 1, 2, 2, 2, 0, 1, 0, 0, 1, 0, 2, 1, 1, 2, 1, 0, 0, 0, 2, 0, 1, 0, 0, 2, 1, 2, 2, 0, 1, 1, 0, 1, 1, 2, 0, 2, 2, 2, 0, 0, 0, 2, 2, 1, 0, 2, 1, 1, 1, 2, 2, 1, 1, 0, 0, 1, 2, 1, 1, 0, 2, 1, 2, 0, 2, 1, 0, 1, 1, 0, 2, 1, 1, 2, 0, 2, 0, 0, 1, 0, 1, 0, 2, 1, 1, 0, 2, 0, 1, 2, 2, 0, 1, 1, 1, 2, 1, 0, 2, 2, 2, 2, 1, 1, 0, 2, 0, 1, 2, 0, 2, 2, 1, 1, 2, 1, 0, 0, 1, 0, 1, 2, 0, 0, 2, 1, 0, 0, 1, 1, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 0, 2, 1, 0, 1, 2, 0, 2, 2, 1, 0},
            new int[]{0, 2, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 2, 2, 1, 0, 1, 0, 0, 2, 1, 1, 1, 1, 2, 2, 0, 1, 1, 1, 2, 0, 1, 1, 2, 2, 2, 1, 1, 2, 0, 2, 2, 1, 1, 2, 2, 0, 2, 1, 0, 1, 2, 0, 1, 2, 0, 2, 0, 2, 1, 0, 0, 0, 0, 1, 1, 2, 2, 0, 1, 2, 0, 1, 1, 2, 1, 2, 2, 0, 0, 0, 2, 2, 0, 1, 0, 2, 1, 1, 2, 2, 0, 2, 1, 2, 0, 1, 0, 1, 2, 0, 2, 2, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 2, 0, 1, 2, 0, 2, 1, 0, 2, 2, 0, 0, 0, 1, 2, 0, 0, 1, 0, 1, 1, 1, 2, 0, 2, 2, 0, 1, 0, 1, 1, 2, 1, 0, 0, 2, 1, 1, 0, 2, 0, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 2, 0, 0, 0, 1, 1, 1, 2, 0, 1, 2, 1, 1, 1, 1, 1, 2, 0, 0, 1, 0, 2, 0, 0, 1, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 1, 1, 0, 0, 0, 0, 0, 2, 2, 2, 1, 2, 2, 0, 0, 2, 2, 2, 2, 0, 0, 2, 1, 0, 2, 2, 0, 1, 1, 0, 1, 0, 0, 1, 0, 2, 2, 0, 0, 2, 0, 0},
            new int[]{0, 2, 1, 0, 1, 0, 0, 0, 1, 2, 1, 0, 2, 2, 0, 2, 1, 2, 1, 2, 0, 1, 0, 0, 2, 2, 2, 1, 1, 0, 1, 0, 1, 2, 2, 2, 2, 1, 0, 2, 1, 1, 2, 1, 0, 2, 1, 0, 0, 1, 0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 2, 1, 0, 0, 0, 1, 0, 2, 1, 1, 0, 1, 2, 1, 0, 2, 0, 1, 1, 0, 1, 1, 2, 0, 2, 1, 2, 0, 0, 0, 2, 2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 0, 0, 0, 0, 2, 1, 0, 0, 1, 1, 2, 0, 2, 1, 0, 1, 0, 1, 2, 2, 1, 2, 0, 2, 2, 1, 1, 2, 0, 0, 1, 1, 0, 1, 2, 0, 2, 2, 2, 1, 0, 0, 1, 0, 1, 0, 2, 2, 1, 1, 0, 0, 1, 2, 2, 1, 1, 2, 1, 2, 0, 2, 2, 0, 2, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 2, 1, 1, 2, 2, 2, 1, 0, 2, 0, 1, 0, 1, 1, 2, 1, 0, 2, 1, 1, 1, 0, 2, 0, 2, 0, 0, 1, 2, 2, 1, 2, 2, 1, 0, 2, 2, 2, 0, 0, 0, 0, 1, 0, 1, 2, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 2, 2, 0, 2},
            new int[]{1, 0, 1, 2, 1, 1, 0, 0, 2, 0, 2, 1, 1, 0, 1, 2, 1, 0, 2, 2, 1, 1, 0, 1, 1, 2, 0, 1, 1, 2, 1, 0, 0, 2, 2, 0, 2, 2, 0, 2, 1, 1, 2, 0, 0, 0, 0, 0, 2, 1, 0, 2, 2, 1, 0, 0, 2, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 2, 0, 2, 1, 0, 0, 2, 2, 2, 0, 2, 2, 0, 1, 1, 2, 2, 1, 0, 0, 0, 2, 2, 2, 1, 0, 1, 1, 2, 2, 2, 2, 2, 1, 2, 0, 2, 1, 1, 0, 0, 2, 0, 1, 1, 2, 1, 1, 2, 1, 0, 1, 2, 2, 0, 0, 0, 0, 2, 2, 1, 2, 2, 1, 1, 0, 2, 2, 1, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 2, 0, 0, 0, 1, 1, 0, 2, 0, 2, 2, 1, 1, 1, 0, 1, 2, 2, 0, 1, 2, 2, 2, 0, 1, 2, 2, 2, 0, 2, 1, 1, 2, 0, 2, 1, 1, 0, 2, 1, 0, 2, 1, 2, 1, 1, 1, 0, 0, 0, 0, 2, 2, 0, 2, 2, 2, 2, 0, 2, 2, 0, 0, 0, 2, 0, 1, 0, 0, 0, 1, 1, 2, 0, 1, 1, 0, 2, 1, 1, 2, 2, 0, 2, 0, 1},
            new int[]{0, 1, 0, 1, 2, 0, 1, 1, 1, 1, 2, 1, 1, 0, 0, 2, 1, 0, 1, 0, 0, 1, 2, 1, 1, 0, 2, 2, 0, 0, 2, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 2, 0, 1, 2, 0, 2, 1, 2, 2, 2, 1, 0, 0, 1, 2, 2, 0, 1, 2, 1, 1, 0, 2, 2, 2, 2, 0, 1, 0, 1, 1, 1, 2, 0, 1, 2, 1, 1, 0, 1, 1, 2, 0, 0, 1, 0, 1, 0, 0, 2, 2, 2, 2, 0, 1, 2, 0, 1, 2, 2, 0, 1, 2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 2, 1, 0, 2, 2, 2, 1, 1, 0, 1, 0, 0, 1, 2, 2, 2, 1, 0, 2, 0, 0, 2, 2, 1, 2, 1, 0, 2, 0, 0, 2, 1, 0, 2, 2, 0, 2, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 1, 0, 0, 0, 0, 2, 2, 0, 2, 1, 0, 2, 0, 2, 2, 0, 0, 2, 0, 0, 2, 2, 0, 0, 1, 0, 0, 0, 0, 2, 0, 1, 2, 0, 0, 2, 0, 2, 0, 1, 0, 0, 2, 0, 0, 2, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 2, 2, 0, 2, 0, 2, 1, 1, 2, 1, 2, 0, 1, 2, 2, 1},
            new int[]{0, 0, 1, 1, 0, 0, 2, 0, 1, 1, 0, 1, 0, 2, 1, 0, 1, 2, 0, 0, 2, 2, 0, 0, 2, 1, 0, 2, 0, 2, 0, 1, 0, 1, 0, 0, 2, 2, 1, 1, 1, 1, 2, 0, 1, 2, 1, 2, 2, 0, 1, 2, 0, 0, 1, 1, 0, 2, 2, 0, 0, 2, 2, 1, 0, 1, 1, 0, 1, 0, 2, 1, 1, 2, 0, 0, 0, 2, 2, 0, 1, 0, 2, 2, 1, 2, 2, 0, 2, 1, 2, 1, 1, 0, 0, 2, 0, 2, 2, 2, 0, 1, 2, 1, 0, 2, 0, 2, 1, 2, 0, 1, 2, 0, 2, 2, 2, 2, 1, 0, 0, 0, 1, 0, 2, 0, 2, 1, 1, 2, 1, 0, 2, 2, 2, 2, 1, 0, 0, 2, 0, 1, 2, 0, 2, 1, 1, 1, 0, 1, 0, 0, 1, 2, 1, 2, 2, 0, 2, 0, 0, 2, 1, 1, 0, 2, 0, 1, 0, 0, 1, 1, 1, 1, 2, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 2, 0, 1, 0, 0, 2, 0, 0, 2, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 2, 0, 1, 2, 0, 2, 0, 0, 2, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 2, 2, 1, 1, 0, 2, 0, 0, 2, 1, 1, 2, 1, 1},
            new int[]{2, 0, 0, 1, 1, 0, 0, 0, 0, 2, 2, 2, 1, 0, 2, 2, 0, 2, 2, 2, 2, 1, 0, 1, 0, 0, 0, 2, 0, 2, 1, 2, 2, 0, 2, 2, 0, 2, 2, 2, 0, 2, 0, 0, 0, 0, 0, 2, 1, 0, 1, 0, 1, 0, 0, 2, 1, 0, 2, 2, 1, 2, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 2, 0, 2, 0, 1, 0, 0, 2, 2, 2, 2, 1, 1, 1, 0, 1, 2, 2, 0, 2, 2, 2, 2, 0, 1, 2, 2, 0, 0, 2, 0, 1, 2, 0, 2, 2, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 2, 2, 2, 0, 0, 1, 0, 1, 1, 2, 1, 1, 1, 2, 0, 1, 0, 2, 0, 0, 2, 1, 2, 0, 1, 2, 2, 0, 0, 1, 2, 1, 0, 0, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 0, 0, 0, 0, 2, 0, 0, 0, 2, 1, 2, 0, 2, 0, 0, 1, 2, 1, 2, 1, 1, 1, 0, 2, 2, 1, 1, 2, 0, 2, 2, 1, 1, 1, 2, 0, 2, 1, 0, 1, 2, 2, 1, 2, 1, 2, 0, 2, 1, 2, 0, 0, 2, 1, 1, 1, 2, 2, 1, 2, 0, 1, 1, 2, 1, 1, 0, 0, 1, 0, 2},
            new int[]{2, 0, 0, 1, 2, 0, 0, 2, 1, 0, 1, 2, 2, 0, 2, 0, 1, 0, 2, 1, 2, 2, 0, 1, 1, 1, 2, 0, 2, 0, 2, 2, 2, 1, 1, 2, 1, 1, 2, 0, 2, 2, 2, 0, 0, 0, 0, 2, 1, 0, 2, 1, 1, 1, 0, 2, 1, 0, 0, 0, 1, 2, 2, 1, 0, 0, 1, 2, 1, 2, 2, 0, 1, 1, 0, 2, 1, 1, 0, 2, 2, 2, 0, 1, 0, 1, 1, 1, 1, 2, 1, 2, 1, 1, 0, 1, 0, 1, 0, 1, 2, 0, 1, 0, 2, 1, 2, 1, 1, 0, 0, 1, 2, 0, 2, 2, 0, 1, 2, 0, 2, 0, 1, 0, 0, 2, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 2, 1, 1, 0, 2, 0, 2, 0, 1, 1, 1, 1, 1, 2, 2, 1, 1, 2, 1, 0, 0, 1, 1, 0, 2, 0, 0, 2, 1, 0, 1, 0, 1, 2, 0, 0, 1, 0, 2, 2, 1, 1, 0, 2, 2, 0, 2, 1, 1, 2, 1, 1, 1, 0, 0, 2, 1, 0, 0, 0, 2, 2, 2, 1, 1, 0, 1, 2, 2, 2, 2, 2, 2, 1, 0, 1, 2, 1, 0, 0, 0, 2, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 0, 0, 0, 1, 0, 0, 0, 0, 2, 1, 1, 1, 0, 0},
            new int[]{2, 0, 2, 1, 1, 2, 2, 2, 1, 0, 2, 2, 1, 0, 1, 1, 2, 1, 0, 1, 1, 1, 2, 0, 2, 0, 2, 2, 0, 1, 1, 2, 1, 1, 2, 0, 0, 2, 2, 2, 0, 0, 0, 2, 2, 0, 2, 2, 1, 1, 1, 2, 2, 0, 0, 0, 1, 2, 2, 1, 1, 2, 1, 1, 1, 2, 2, 0, 2, 0, 0, 0, 2, 1, 1, 2, 0, 1, 0, 1, 2, 1, 1, 0, 2, 0, 1, 1, 1, 1, 0, 1, 1, 2, 1, 2, 1, 0, 2, 0, 0, 2, 0, 1, 2, 2, 0, 2, 0, 0, 0, 1, 0, 2, 2, 0, 1, 0, 1, 1, 0, 2, 1, 0, 2, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 2, 0, 0, 0, 2, 0, 1, 1, 2, 2, 2, 1, 2, 0, 1, 2, 2, 1, 1, 2, 0, 1, 0, 0, 2, 0, 2, 0, 2, 0, 1, 0, 1, 0, 2, 1, 2, 1, 1, 1, 1, 2, 2, 0, 2, 2, 0, 2, 0, 1, 1, 2, 0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 1, 0, 1, 1, 2, 1, 1, 0, 2, 1, 2, 0, 2, 0, 0, 1, 1, 0, 2, 1, 1, 1, 0, 2, 1, 0, 1, 0, 1, 2, 2, 1, 0, 0, 2, 2, 1, 1, 2, 0, 1, 1, 1, 2, 1},
            new int[]{2, 0, 2, 0, 2, 1, 1, 0, 1, 1, 1, 1, 2, 2, 1, 1, 0, 0, 1, 0, 1, 1, 0, 2, 1, 2, 0, 0, 1, 0, 0, 1, 0, 2, 1, 2, 2, 0, 0, 0, 0, 2, 0, 2, 0, 2, 1, 1, 0, 2, 0, 2, 1, 2, 2, 1, 1, 1, 2, 2, 2, 2, 0, 0, 2, 2, 1, 1, 1, 0, 1, 1, 0, 2, 1, 2, 2, 2, 0, 0, 0, 1, 0, 2, 0, 1, 1, 1, 1, 1, 0, 2, 2, 1, 2, 1, 0, 0, 2, 1, 1, 1, 0, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 0, 1, 1, 0, 2, 2, 1, 1, 2, 2, 2, 0, 1, 1, 1, 2, 0, 1, 1, 1, 2, 2, 1, 1, 2, 0, 2, 1, 1, 1, 0, 2, 0, 2, 1, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0, 1, 0, 0, 2, 1, 1, 2, 0, 1, 0, 0, 1, 1, 1, 0, 2, 0, 1, 0, 0, 1, 1, 2, 1, 2, 1, 1, 0, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 2, 0, 2, 0, 1, 1, 0, 2, 1, 0, 2, 1, 1, 2, 0, 1, 0, 0, 0},
            new int[]{0, 1, 0, 2, 0, 1, 0, 2, 0, 1, 0, 2, 2, 1, 1, 2, 2, 1, 1, 2, 1, 1, 2, 0, 1, 0, 2, 0, 0, 0, 0, 2, 0, 1, 2, 2, 0, 1, 0, 2, 0, 1, 0, 2, 2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 2, 0, 0, 0, 2, 0, 0, 1, 0, 2, 1, 1, 2, 0, 0, 2, 0, 2, 0, 1, 0, 2, 2, 0, 0, 2, 1, 1, 1, 2, 1, 0, 1, 0, 1, 1, 2, 1, 0, 2, 2, 2, 1, 0, 2, 0, 2, 0, 1, 2, 2, 1, 0, 2, 2, 1, 1, 0, 2, 0, 1, 0, 1, 1, 2, 1, 1, 2, 1, 1, 1, 0, 2, 0, 0, 0, 0, 0, 2, 2, 1, 2, 0, 1, 0, 0, 2, 2, 1, 0, 1, 0, 1, 0, 1, 2, 1, 1, 2, 2, 1, 2, 1, 1, 1, 0, 0, 1, 0, 0, 2, 0, 2, 2, 2, 0, 0, 0, 1, 0, 2, 0, 2, 1, 1, 1, 1, 0, 2, 2, 2, 2, 1, 2, 0, 2, 1, 1, 2, 0, 2, 0, 1, 1, 2, 1, 0, 2, 1, 1, 1, 2, 2, 0, 2, 0, 0, 1, 2, 1, 1, 2, 0, 1, 0, 2, 2, 1, 0, 0, 2, 0, 1, 2, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 2, 0, 0, 2, 0},
            new int[]{2, 1, 2, 2, 2, 0, 0, 0, 2, 2, 2, 0, 1, 1, 1, 1, 2, 2, 2, 1, 2, 2, 1, 0, 1, 1, 1, 2, 0, 0, 0, 1, 2, 0, 1, 1, 2, 2, 1, 1, 2, 0, 0, 2, 2, 1, 0, 2, 0, 2, 2, 0, 2, 1, 1, 0, 2, 2, 0, 0, 0, 2, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 2, 0, 2, 0, 0, 2, 0, 0, 0, 2, 1, 1, 0, 0, 0, 0, 1, 2, 1, 1, 1, 2, 2, 0, 1, 2, 1, 0, 2, 1, 1, 2, 2, 0, 1, 2, 0, 0, 1, 0, 1, 2, 2, 0, 0, 2, 2, 0, 1, 1, 2, 2, 1, 0, 2, 0, 2, 2, 2, 1, 0, 1, 0, 2, 2, 0, 2, 2, 1, 2, 2, 2, 1, 0, 0, 0, 1, 0, 0, 1, 2, 1, 1, 2, 1, 0, 0, 0, 2, 1, 0, 0, 0, 2, 1, 2, 2, 1, 0, 1, 2, 2, 1, 2, 0, 0, 1, 2, 1, 2, 0, 0, 1, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 1, 1, 0, 2, 0, 0, 1, 2, 2, 2, 2, 1, 2, 0, 2, 2, 2, 1, 0, 2, 0, 1, 1, 0, 2, 2, 1, 0, 2, 1, 2, 0, 1, 1, 1, 1, 0, 0, 2, 1, 0, 2, 1},
            new int[]{0, 2, 2, 1, 1, 0, 0, 0, 0, 0, 1, 1, 2, 1, 2, 2, 0, 0, 1, 1, 2, 0, 1, 0, 2, 1, 2, 1, 2, 2, 0, 1, 2, 0, 2, 2, 1, 0, 2, 2, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 2, 0, 1, 0, 0, 0, 2, 1, 1, 0, 0, 1, 0, 2, 2, 1, 1, 1, 2, 0, 0, 2, 1, 1, 2, 2, 1, 2, 2, 0, 1, 1, 0, 1, 0, 0, 0, 2, 2, 2, 0, 0, 2, 0, 2, 2, 2, 2, 1, 1, 0, 0, 2, 0, 2, 0, 2, 2, 1, 2, 1, 0, 2, 1, 2, 0, 1, 0, 2, 2, 0, 0, 2, 1, 0, 1, 2, 1, 0, 1, 0, 1, 0, 2, 1, 1, 2, 2, 2, 1, 2, 2, 0, 1, 0, 1, 1, 2, 0, 0, 2, 2, 1, 1, 0, 2, 2, 2, 0, 2, 1, 2, 1, 1, 1, 2, 1, 0, 2, 2, 2, 0, 2, 1, 0, 2, 0, 1, 2, 1, 0, 2, 0, 0, 2, 1, 0, 1, 2, 0, 2, 0, 0, 1, 0, 2, 1, 0, 1, 1, 0, 2, 0, 2, 0, 0, 2, 0, 0, 1, 2, 2, 1, 0, 0, 0, 0, 2, 2, 2, 0, 1, 1, 2, 0, 2, 2, 2, 1, 2, 2, 2, 2, 1, 0, 2, 2, 0, 0, 1, 0, 2, 1},
            new int[]{0, 1, 0, 1, 2, 0, 2, 0, 0, 2, 2, 1, 1, 0, 1, 1, 0, 0, 2, 1, 2, 1, 0, 0, 0, 2, 1, 1, 2, 2, 2, 1, 2, 2, 1, 1, 0, 1, 1, 2, 0, 0, 0, 2, 1, 0, 0, 2, 2, 2, 1, 2, 1, 0, 1, 1, 2, 2, 2, 0, 2, 2, 2, 0, 2, 1, 1, 1, 0, 0, 2, 1, 0, 2, 1, 2, 2, 2, 1, 1, 0, 0, 0, 2, 0, 1, 2, 2, 1, 2, 2, 2, 0, 1, 0, 2, 0, 0, 0, 1, 1, 2, 1, 2, 2, 0, 1, 1, 1, 2, 0, 1, 0, 2, 2, 2, 1, 1, 2, 0, 1, 2, 1, 2, 2, 2, 0, 2, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 2, 1, 0, 0, 0, 0, 0, 2, 2, 0, 0, 1, 2, 0, 0, 2, 2, 0, 1, 2, 2, 0, 2, 0, 2, 0, 2, 0, 2, 2, 0, 1, 2, 1, 2, 1, 2, 0, 0, 2, 0, 1, 1, 2, 1, 1, 2, 0, 0, 1, 2, 2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 0, 0, 0, 2, 2, 0, 1, 1, 1, 1, 1, 2, 2, 0, 2, 2, 1, 0, 0, 1, 1, 2, 0, 0, 1, 1, 1, 0, 1, 2, 2, 2, 2, 1, 1, 2, 0, 1, 2},
            new int[]{1, 0, 2, 2, 0, 2, 0, 0, 1, 2, 0, 1, 0, 0, 1, 0, 2, 2, 0, 0, 1, 0, 0, 0, 2, 1, 0, 1, 2, 0, 0, 2, 2, 1, 0, 2, 1, 0, 2, 0, 2, 1, 1, 0, 0, 0, 0, 2, 2, 2, 1, 1, 2, 2, 0, 2, 2, 2, 2, 2, 0, 1, 2, 0, 0, 2, 0, 0, 1, 2, 0, 0, 2, 0, 0, 0, 2, 2, 0, 2, 0, 0, 0, 1, 2, 2, 0, 0, 1, 0, 1, 1, 2, 2, 2, 1, 2, 0, 1, 0, 2, 1, 1, 2, 0, 1, 0, 1, 2, 0, 1, 0, 2, 0, 1, 1, 1, 0, 0, 1, 2, 2, 1, 2, 1, 2, 2, 0, 2, 2, 0, 0, 2, 1, 0, 2, 0, 0, 0, 1, 0, 1, 0, 0, 2, 0, 1, 1, 0, 1, 2, 0, 1, 0, 1, 2, 0, 0, 1, 0, 0, 1, 1, 1, 0, 2, 2, 0, 0, 0, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 2, 0, 0, 1, 0, 0, 1, 0, 1, 2, 2, 2, 0, 0, 0, 0, 1, 1, 2, 1, 1, 1, 1, 0, 1, 1, 2, 0, 0, 2, 0, 2, 0, 0, 2, 2, 2, 0, 0, 2, 1, 0, 0, 2, 2, 1, 1, 0, 1, 0, 1, 1, 2, 1, 2, 1, 0, 2, 1, 0, 2, 0, 1},
            new int[]{2, 2, 0, 0, 0, 0, 2, 1, 0, 2, 2, 1, 1, 0, 2, 1, 0, 0, 1, 1, 2, 1, 1, 0, 0, 1, 0, 1, 2, 0, 0, 1, 2, 0, 0, 1, 1, 0, 2, 2, 2, 0, 2, 2, 1, 0, 1, 1, 2, 1, 0, 0, 1, 1, 2, 0, 2, 0, 2, 1, 0, 1, 2, 2, 1, 1, 2, 2, 0, 2, 1, 2, 0, 2, 0, 1, 2, 0, 2, 2, 1, 1, 1, 1, 0, 0, 1, 0, 1, 2, 2, 0, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0, 0, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 2, 0, 2, 1, 0, 0, 0, 0, 1, 1, 1, 2, 0, 1, 2, 0, 1, 1, 1, 1, 2, 0, 0, 0, 0, 2, 1, 0, 1, 2, 2, 1, 0, 2, 1, 0, 2, 1, 2, 0, 1, 0, 0, 0, 0, 0, 2, 1, 0, 1, 0, 2, 0, 0, 2, 1, 0, 2, 2, 2, 2, 0, 0, 1, 0, 0, 1, 2, 0, 1, 1, 2, 0, 0, 0, 2, 0, 0, 2, 2, 2, 2, 1, 2, 0, 0, 0, 2, 2, 0, 2, 0, 1, 2, 1, 2, 2, 0, 0, 1, 1, 0, 1, 1, 0, 2, 1, 2, 1, 0, 0, 0, 0, 1, 0, 2, 2, 2, 1, 2, 0, 1, 0, 2, 1, 2},
            new int[]{2, 0, 1, 0, 1, 2, 0, 2, 0, 2, 2, 1, 1, 1, 0, 1, 1, 2, 0, 1, 2, 2, 2, 0, 0, 2, 2, 0, 0, 2, 1, 1, 1, 0, 2, 0, 1, 0, 1, 1, 2, 2, 1, 2, 1, 1, 1, 0, 2, 1, 0, 0, 2, 0, 2, 2, 1, 0, 0, 1, 1, 0, 2, 0, 1, 1, 1, 0, 1, 0, 1, 2, 1, 2, 1, 2, 0, 2, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 2, 0, 1, 0, 0, 2, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 2, 0, 0, 0, 2, 1, 0, 0, 1, 2, 0, 1, 2, 1, 0, 1, 0, 2, 0, 0, 0, 1, 2, 2, 2, 2, 2, 0, 1, 1, 2, 2, 1, 0, 0, 1, 2, 2, 2, 1, 0, 1, 1, 0, 2, 2, 1, 2, 1, 2, 0, 0, 1, 1, 1, 0, 2, 1, 1, 2, 2, 1, 1, 2, 1, 0, 1, 0, 1, 0, 2, 0, 0, 2, 2, 2, 1, 2, 2, 2, 0, 0, 2, 2, 2, 0, 0, 1, 1, 1, 0, 2, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0, 2, 1, 2, 2, 1, 0, 0, 0, 2, 1, 2, 0, 0, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 0, 0, 2, 1, 0},
            new int[]{0, 0, 2, 2, 1, 1, 1, 0, 1, 2, 2, 2, 1, 2, 2, 2, 0, 1, 2, 1, 2, 0, 0, 1, 1, 2, 0, 1, 1, 1, 2, 2, 1, 2, 2, 0, 2, 1, 1, 1, 0, 0, 0, 2, 0, 2, 1, 2, 2, 2, 2, 2, 0, 2, 2, 2, 0, 1, 0, 0, 1, 0, 0, 2, 1, 2, 1, 0, 0, 0, 0, 1, 1, 2, 2, 2, 1, 2, 0, 1, 1, 2, 1, 1, 2, 0, 1, 0, 2, 2, 0, 0, 0, 2, 0, 1, 2, 1, 0, 1, 1, 2, 0, 1, 0, 1, 2, 2, 0, 2, 2, 0, 1, 1, 1, 2, 2, 0, 0, 0, 2, 2, 1, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 2, 1, 0, 0, 2, 0, 1, 1, 2, 0, 2, 1, 1, 0, 1, 2, 2, 2, 1, 2, 1, 1, 0, 1, 2, 1, 2, 0, 2, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 1, 2, 1, 0, 1, 2, 0, 2, 2, 2, 2, 2, 2, 1, 0, 1, 0, 2, 0, 0, 0, 2, 1, 2, 2, 2, 2, 0, 1, 2, 1, 2, 0, 1, 0, 1, 2, 0, 1},
            new int[]{1, 1, 0, 1, 1, 1, 0, 0, 2, 1, 2, 0, 0, 1, 2, 2, 1, 1, 2, 1, 2, 2, 2, 2, 0, 2, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 2, 0, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0, 1, 1, 2, 1, 0, 1, 2, 1, 2, 0, 2, 0, 2, 2, 1, 1, 1, 1, 1, 1, 2, 0, 1, 2, 2, 0, 0, 0, 1, 2, 0, 0, 2, 2, 1, 1, 1, 2, 0, 2, 0, 2, 1, 2, 2, 1, 2, 1, 1, 2, 2, 2, 0, 0, 0, 2, 0, 0, 1, 1, 1, 1, 1, 2, 0, 0, 2, 1, 1, 0, 0, 1, 2, 2, 0, 1, 1, 1, 2, 0, 2, 2, 2, 2, 2, 1, 1, 2, 1, 0, 2, 0, 0, 2, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 1, 0, 0, 2, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 2, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 2, 2, 0, 0, 1, 1, 0, 0, 1, 2, 2, 1, 2, 1, 0, 2, 0, 2, 2, 0, 0, 2, 2, 0, 2, 2, 0, 0, 1, 0, 2, 0, 0, 0, 0, 1, 2, 0, 2, 2, 0, 1, 0, 1, 2, 0, 1, 0, 0, 2, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 2, 2, 2, 0}
    };

    private static final int[] sbox_lookup = new int[]{
            6, 25, 17, 5, 15, 10, 4, 20, 24,
            0, 1, 2, 9, 22, 26, 18, 16, 14,
            3, 13, 23, 7, 11, 12, 8, 21, 19};

    private static final int[] shift_rows_param = new int[]{0, 1, 2};

    private static final int[] shift_lanes_param = new int[]{
            19, 13, 21, 10, 24, 15, 2, 9, 3,
            14, 0, 6, 5, 1, 25, 22, 23, 20,
            7, 17, 26, 12, 8, 18, 16, 11, 4};

    /**
     * @param messageTrytes message to hash
     * @return the hash of messageTrytes formatted as 81 trytes using 24 rounds and hash length:243
     * @throws IllegalArgumentException if one of the characters of messageTrytes is not a valid Tryte
     */
    public static String hash(String messageTrytes) {
        return hash(messageTrytes, NUM_ROUNDS);
    }

    /**
     * @param messageTrytes message to hash
     * @param round number of round
     * @return the hash of messageTrytes formatted as 81 trytes using *round* rounds and hash length:243
     * @throws IllegalArgumentException if one of the characters of messageTrytes is not a valid Tryte
     */
    public static String hash(String messageTrytes, int round) {
        return hash(messageTrytes, round, 243);
    }

    /**
     * @param messageTrytes message to hash
     * @param round number of round
     * @param hashLengthInTrits length of the hash
     * @return the hash of messageTrytes formatted as 81 trytes using *round* rounds and hash length: *hashLengthInTrits*
     * @throws IllegalArgumentException if one of the characters of messageTrytes is not a valid Tryte
     */
    public static String hash(String messageTrytes, int round, int hashLengthInTrits) {
        int[] messageTrits = trytesToIntArray(messageTrytes);
        int[] out = new int[hashLengthInTrits];
        troikaVarRounds(out, hashLengthInTrits, messageTrits, messageTrits.length, round);
        return intArrayToTrytes(out);
    }

    /**
     * @param messageTrits message to hash formatted in trits (a valid trit is 0, 1 or 2)
     * @return the hash of messageTrytes formatted as 81 trytes using 24 rounds and hash length:243
     */
    public static String hash(int[] messageTrits) {
        return hash(messageTrits, NUM_ROUNDS);
    }

    /**
     * @param messageTrits message to hash formatted in trits (a valid trit is 0, 1 or 2)
     * @param round number of round
     * @return the hash of messageTrytes formatted as 81 trytes using *round* rounds and hash length:243
     */
    public static String hash(int[] messageTrits, int round) {
        return hash(messageTrits, round, 243);
    }

    /**
     * @param messageTrits message to hash formatted in trits (a valid trit is 0, 1 or 2)
     * @param round number of round
     * @param hashLengthInTrits length of the hash
     * @return the hash of messageTrytes formatted as *hashLengthInTrits/3* trytes using *round* rounds and hash length: *hashLengthInTrits*
     * @throws IllegalArgumentException if hashLengthInTrits is not a multiple of 3
     */
    public static String hash(int[] messageTrits, int round, int hashLengthInTrits) {
        if(hashLengthInTrits%3 != 0){
            throw new IllegalArgumentException(hashLengthInTrits + " is not a valid length");
        }
        int[] out = new int[hashLengthInTrits];
        troikaVarRounds(out, hashLengthInTrits, messageTrits, messageTrits.length, round);
        return intArrayToTrytes(out);
    }

    /**
     * @param messageTrytes message to hash
     * @return the hash of messageTrytes formatted as 243 trits using 24 rounds and hash length:243
     * @throws IllegalArgumentException if one of the characters of messageTrytes is not a valid Tryte
     */
    public static int[] hashAsTrits(String messageTrytes) {
        return hashAsTrits(messageTrytes, NUM_ROUNDS);
    }

    /**
     * @param messageTrytes message to hash
     * @param round number of round
     * @return the hash of messageTrytes formatted as 243 trits using *round* rounds and hash length:243
     * @throws IllegalArgumentException if one of the characters of messageTrytes is not a valid Tryte
     */
    public static int[] hashAsTrits(String messageTrytes, int round) {
        return hashAsTrits(messageTrytes, round, 243);
    }

    /**
     * @param messageTrytes message to hash
     * @param round number of round
     * @param hashLengthInTrits length of the hash
     * @return the hash of messageTrytes formatted as *hashLengthInTrits* trits using *round* rounds and hash length: *hashLengthInTrits*
     * @throws IllegalArgumentException if one of the characters of messageTrytes is not a valid Tryte
     */
    public static int[] hashAsTrits(String messageTrytes, int round, int hashLengthInTrits) {
        int[] messageTrits = trytesToIntArray(messageTrytes);
        int[] out = new int[hashLengthInTrits];
        troikaVarRounds(out, hashLengthInTrits, messageTrits, messageTrits.length, round);
        return out;
    }

    /**
     * @param messageTrits message to hash formatted in trits (a valid trit is 0, 1 or 2)
     * @return the hash of messageTrytes formatted as 243 trits using 24 rounds and hash length:243
     */
    public static int[] hashAsTrits(int[] messageTrits) {
        return hashAsTrits(messageTrits, NUM_ROUNDS);
    }

    /**
     * @param messageTrits message to hash formatted in trits (a valid trit is 0, 1 or 2)
     * @param round number of round
     * @return the hash of messageTrytes formatted as 243 trits using *round* rounds and hash length:243
     */
    public static int[] hashAsTrits(int[] messageTrits, int round) {
        return hashAsTrits(messageTrits, round, 243);
    }

    /**
     * @param messageTrits message to hash formatted in trits (a valid trit is 0, 1 or 2)
     * @param round number of round
     * @param hashLengthInTrits length of the hash
     * @return the hash of messageTrytes formatted as *hashLengthInTrits* trits using *round* rounds and hash length: *hashLengthInTrits*
     */
    public static int[] hashAsTrits(int[] messageTrits, int round, int hashLengthInTrits) {
        int[] out = new int[hashLengthInTrits];
        troikaVarRounds(out, hashLengthInTrits, messageTrits, messageTrits.length, round);
        return out;
    }

    public static void troika(int[] out, long outlen,
                              int[] in, long inlen) {
        troikaVarRounds(out, outlen, in, inlen, NUM_ROUNDS);
    }

    private static void troikaVarRounds(int[] out, long outlen,
                                        int[] in, long inlen,
                                        long num_rounds) {
        int[] state = new int[STATESIZE];
        Arrays.fill(state, 0);
        troikaAbsorb(state, TROIKA_RATE, in, inlen, num_rounds);
        troikaSqueeze(out, outlen, TROIKA_RATE, state, num_rounds);
    }

    public static void printTroikaSlice(int[] state, int slice) {
        System.err.println(String.format("#### Slice %d ####", slice));
        for (int row = 0; row < ROWS; ++row) {
            for (int column = 0; column < COLUMNS; ++column) {
                System.err.print(String.format("%d ", state[slice * SLICES + row * COLUMNS + column]));
            }
            System.err.println();
        }
        System.err.println("------------------");
        for (int i = 0; i < COLUMNS; i++) {
            System.err.print(String.format("%d ", (state[slice * SLICES + 0 * COLUMNS + i] + state[slice * SLICES + 1 * COLUMNS + i] + state[slice * SLICES + 2 * COLUMNS + i]) % 3));
        }
        System.err.println();
    }

    public static void printTroikaState(int[] state) {
        for (int slice = 0; slice < SLICES; ++slice) {
            printTroikaSlice(state, slice);
        }
    }

    private static void subTrytes(int[] state) {
        int sbox_idx;

        for (sbox_idx = 0; sbox_idx < NUM_SBOXES; ++sbox_idx) {
            int sbox_input = state[3 * sbox_idx] * 9 + state[3 * sbox_idx + 1] * 3 + state[3 * sbox_idx + 2];
            int sbox_output = sbox_lookup[sbox_input];
            state[3 * sbox_idx + 2] = sbox_output % 3;
            sbox_output /= 3;
            state[3 * sbox_idx + 1] = sbox_output % 3;
            sbox_output /= 3;
            state[3 * sbox_idx] = sbox_output % 3;
        }
    }

    private static void shiftRows(int[] state) {
        int slice, row, col, old_idx, new_idx;

        int[] new_state = new int[STATESIZE];

        for (slice = 0; slice < SLICES; ++slice) {
            for (row = 0; row < ROWS; ++row) {
                for (col = 0; col < COLUMNS; ++col) {
                    old_idx = SLICESIZE * slice + COLUMNS * row + col;
                    new_idx = SLICESIZE * slice + COLUMNS * row +
                            (col + 3 * shift_rows_param[row]) % COLUMNS;
                    new_state[new_idx] = state[old_idx];
                }
            }
        }

        System.arraycopy(new_state, 0, state, 0, STATESIZE);
    }

    private static void shiftLanes(int[] state) {
        int slice, row, col, old_idx, new_idx, new_slice;

        int[] new_state = new int[STATESIZE];

        for (slice = 0; slice < SLICES; ++slice) {
            for (row = 0; row < ROWS; ++row) {
                for (col = 0; col < COLUMNS; ++col) {
                    old_idx = SLICESIZE * slice + COLUMNS * row + col;
                    new_slice = (slice + shift_lanes_param[col + COLUMNS * row]) % SLICES;
                    new_idx = SLICESIZE * (new_slice) + COLUMNS * row + col;
                    new_state[new_idx] = state[old_idx];
                }
            }
        }

        System.arraycopy(new_state, 0, state, 0, STATESIZE);
    }

    private static void addColumnParity(int[] state) {
        int slice, row, col, col_sum, idx, sum_to_add;

        int[] parity = new int[SLICES * COLUMNS];

        // First compute parity for each column
        for (slice = 0; slice < SLICES; ++slice) {
            for (col = 0; col < COLUMNS; ++col) {
                col_sum = 0;
                for (row = 0; row < ROWS; ++row) {
                    col_sum += state[SLICESIZE * slice + COLUMNS * row + col];
                }
                parity[COLUMNS * slice + col] = col_sum % 3;
            }
        }

        // Add parity
        for (slice = 0; slice < SLICES; ++slice) {
            for (row = 0; row < ROWS; ++row) {
                for (col = 0; col < COLUMNS; ++col) {
                    idx = SLICESIZE * slice + COLUMNS * row + col;
                    sum_to_add = parity[(col - 1 + 9) % 9 + COLUMNS * slice] +
                            parity[(col + 1) % 9 + COLUMNS * ((slice + 1) % SLICES)];
                    state[idx] = (state[idx] + sum_to_add) % 3;
                }
            }
        }
    }

    private static void addRoundConstant(int[] state, int round) {
        int slice, col, idx;

        for (slice = 0; slice < SLICES; ++slice) {
            for (col = 0; col < COLUMNS; ++col) {
                idx = SLICESIZE * slice + col;
                state[idx] = (state[idx] +
                        round_constants[round][slice * COLUMNS + col]) % 3;
            }
        }
    }

    private static void troikaPermutation(int[] state, long num_rounds) {
        int round;

        assert (num_rounds <= NUM_ROUNDS);

        for (round = 0; round < num_rounds; round++) {
            subTrytes(state);
            shiftRows(state);
            shiftLanes(state);
            addColumnParity(state);
            addRoundConstant(state, round);
        }
    }

    private static void troikaAbsorb(int[] state, int rate, int[] message,
                                     long message_length,
                                     long num_rounds) {
        int trit_idx;
        int base_idx = 0;
        while (message_length >= rate) {
            // Copy message block over the state
            for (trit_idx = 0; trit_idx < rate; ++trit_idx) {
                state[trit_idx] = message[base_idx + trit_idx];
            }
            troikaPermutation(state, num_rounds);
            message_length -= rate;
            base_idx += rate;
        }

        // Pad last block
        int[] last_block = new int[TROIKA_RATE];
        Arrays.fill(last_block, 0);

        // Copy over last incomplete message block
        for (trit_idx = 0; trit_idx < message_length; ++trit_idx) {
            last_block[trit_idx] = message[base_idx + trit_idx];
        }

        // Apply padding
        last_block[trit_idx] = PADDING;

        // Insert last message block
        for (trit_idx = 0; trit_idx < rate; ++trit_idx) {
            state[trit_idx] = last_block[trit_idx];
        }
    }

    private static void troikaSqueeze(int[] hash, long hash_length,
                                      int rate, int[] state,
                                      long num_rounds) {
        int trit_idx;
        int base_idx = 0;
        while (hash_length >= rate) {
            troikaPermutation(state, num_rounds);
            // Extract rate output
            for (trit_idx = 0; trit_idx < rate; ++trit_idx) {
                hash[base_idx + trit_idx] = state[trit_idx];
            }
            base_idx += rate;
            hash_length -= rate;
        }

        // Check if there is a last incomplete block
        if (hash_length % rate > 0) {
            troikaPermutation(state, num_rounds);
            for (trit_idx = 0; trit_idx < hash_length; ++trit_idx) {
                hash[base_idx + trit_idx] = state[trit_idx];
            }
        }
    }


    public static final void main(String[] args) {
        int[] out = new int[243];
        int[] in = new int[]{0, 1, 0};
        troikaVarRounds(out, 243, in, 1, 3);
        System.out.println(intArrayToTrytes(out));

        out = new int[243];
        troikaVarRounds(out, 243, new int[]{0}, 1, 24);
        System.out.println(intArrayToString(out));

    }

    private static final char[] CHARS = "0ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private static String intArrayToString(int[] trits) {
        StringBuilder sb = new StringBuilder();
        for (int i : trits) sb.append(i);
        return sb.toString();
    }

    private static int[] trytesToIntArray(String trytes) {
        int[] resp = new int[3 * trytes.length()];
        int i = 0;
        for (char c : trytes.toCharArray()) {
            int[] tryte_ints = tryteToIntArray(c);
            resp[i] = tryte_ints[0];
            resp[i + 1] = tryte_ints[1];
            resp[i + 2] = tryte_ints[2];
            i = i + 3;
        }
        return resp;
    }


    private static int[] tryteToIntArray(char c) {
        switch (c) {
            case '0':
                return TRYTE_0;
            case 'A':
                return TRYTE_A;
            case 'B':
                return TRYTE_B;
            case 'C':
                return TRYTE_C;
            case 'D':
                return TRYTE_D;
            case 'E':
                return TRYTE_E;
            case 'F':
                return TRYTE_F;
            case 'G':
                return TRYTE_G;
            case 'H':
                return TRYTE_H;
            case 'I':
                return TRYTE_I;
            case 'J':
                return TRYTE_J;
            case 'K':
                return TRYTE_K;
            case 'L':
                return TRYTE_L;
            case 'M':
                return TRYTE_M;
            case 'N':
                return TRYTE_N;
            case 'O':
                return TRYTE_O;
            case 'P':
                return TRYTE_P;
            case 'Q':
                return TRYTE_Q;
            case 'R':
                return TRYTE_R;
            case 'S':
                return TRYTE_S;
            case 'T':
                return TRYTE_T;
            case 'U':
                return TRYTE_U;
            case 'V':
                return TRYTE_V;
            case 'W':
                return TRYTE_W;
            case 'X':
                return TRYTE_X;
            case 'Y':
                return TRYTE_Y;
            case 'Z':
                return TRYTE_Z;
        }
        throw new IllegalArgumentException(c + " is not a valid tryte");
    }

    private static final int[] TRYTE_0 = {0x00, 0x00, 0x00};
    private static final int[] TRYTE_A = {0x00, 0x00, 0x01};
    private static final int[] TRYTE_B = {0x00, 0x00, 0x02};
    private static final int[] TRYTE_C = {0x00, 0x01, 0x00};
    private static final int[] TRYTE_D = {0x00, 0x01, 0x01};
    private static final int[] TRYTE_E = {0x00, 0x01, 0x02};
    private static final int[] TRYTE_F = {0x00, 0x02, 0x00};
    private static final int[] TRYTE_G = {0x00, 0x02, 0x01};
    private static final int[] TRYTE_H = {0x00, 0x02, 0x02};
    private static final int[] TRYTE_I = {0x01, 0x00, 0x00};
    private static final int[] TRYTE_J = {0x01, 0x00, 0x01};
    private static final int[] TRYTE_K = {0x01, 0x00, 0x02};
    private static final int[] TRYTE_L = {0x01, 0x01, 0x00};
    private static final int[] TRYTE_M = {0x01, 0x01, 0x01};
    private static final int[] TRYTE_N = {0x01, 0x01, 0x02};
    private static final int[] TRYTE_O = {0x01, 0x02, 0x00};
    private static final int[] TRYTE_P = {0x01, 0x02, 0x01};
    private static final int[] TRYTE_Q = {0x01, 0x02, 0x02};
    private static final int[] TRYTE_R = {0x02, 0x00, 0x00};
    private static final int[] TRYTE_S = {0x02, 0x00, 0x01};
    private static final int[] TRYTE_T = {0x02, 0x00, 0x02};
    private static final int[] TRYTE_U = {0x02, 0x01, 0x00};
    private static final int[] TRYTE_V = {0x02, 0x01, 0x01};
    private static final int[] TRYTE_W = {0x02, 0x01, 0x02};
    private static final int[] TRYTE_X = {0x02, 0x02, 0x00};
    private static final int[] TRYTE_Y = {0x02, 0x02, 0x01};
    private static final int[] TRYTE_Z = {0x02, 0x02, 0x02};

    private static String intArrayToTrytes(int[] trits) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < trits.length) {
            sb.append(CHARS[trits[i] * 9 + trits[i + 1] * 3 + trits[i + 2]]);
            i = i + 3;
        }
        return sb.toString();
    }
}

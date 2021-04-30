package com.github.zare88.qafeleomr;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class QafeleOmrStatusFormatter {

    public static final String BASE_BLOCK = "▁";
    public static final String COMPLETE_BLOCK = "▉";
    public static final int ARRAY_SIZE = 20;

    public String formatProgress(int[] yearProgress) {
        Objects.requireNonNull(yearProgress);
        if(yearProgress.length > ARRAY_SIZE){
            throw new IllegalArgumentException();
        }
        return Arrays.stream(yearProgress)
                .mapToObj(value -> value == 1 ? COMPLETE_BLOCK : BASE_BLOCK )
                .collect(Collectors.joining());
    }

    public String formatPercent(int percent) {
        return percent + "%";
    }
}
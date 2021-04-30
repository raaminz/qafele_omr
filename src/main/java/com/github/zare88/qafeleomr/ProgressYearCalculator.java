package com.github.zare88.qafeleomr;

public class ProgressYearCalculator {

    public static final int MAX_YEAR_DAY = 365;
    public static final int BLOCK_SIZE = 20;

    public int calculateDaysPassedPercentage(int daysPassed) {
        if(daysPassed <= 0 || daysPassed > 366){
            throw new IllegalArgumentException("Invalid input");
        }
        return  (daysPassed * 100) / MAX_YEAR_DAY ;
    }

    public int[] getYearProgress(int currentDay) {
        if(currentDay <= 0 || currentDay > 366){
            throw new IllegalArgumentException();
        }
        float eachBlock = MAX_YEAR_DAY / (float) BLOCK_SIZE;
        float completedFloat = currentDay / eachBlock;
        int[] result = new int[BLOCK_SIZE];
        for (int i = 0; i < (int)Math.floor(completedFloat); i++) {
            result[i] = 1;
        }
        return result;
    }
}

package com.github.zare88.qafeleomr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProgressYearCalculatorTest {

    ProgressYearCalculator calculator;

    @BeforeEach
    void beforeEach() {
        calculator = new ProgressYearCalculator();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 367})
    void invalidDayOfYear_calculatePercentage_throwException(int invalidValue) {
        Assertions.assertThrows(IllegalArgumentException.class
                , () -> calculator.calculateDaysPassedPercentage(invalidValue)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 366})
    void validDayOfYear_calculatePercentage_successful(int invalidValue) {
        var result = calculator.calculateDaysPassedPercentage(invalidValue);
        Assertions.assertTrue(result >= 0);
    }

    @Test
    void calculateDaysPassedPercentage_successful() {
        int actualPercentage = calculator.calculateDaysPassedPercentage(200);
        Assertions.assertEquals(54, actualPercentage);
    }

    @Test
    void getYearProgress_returnAnArrayWithSize20() {
        int[] array = calculator.getYearProgress(1);
        Assertions.assertEquals(20, array.length);
    }

    @Test
    void dayIs200_getYearProgress_returnArrayHas10FullBlocks() {
        int[] array = calculator.getYearProgress(200);
        Assertions.assertArrayEquals(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                , array);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 367})
    void invalidInputs_getYearProgress_throwException(int value) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculator.getYearProgress(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 366})
    void inputs_getYearProgress_successful(int value) {
        var result = calculator.getYearProgress(value);
        Assertions.assertNotNull(result);
    }

}

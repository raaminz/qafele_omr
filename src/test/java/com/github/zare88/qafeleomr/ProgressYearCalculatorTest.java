package com.github.zare88.qafeleomr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ProgressYearCalculatorTest {

    private ProgressYearCalculator calculator;

    @BeforeEach
    public void beforeEach() {
        calculator = new ProgressYearCalculator();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 367})
    public void invalidDayOfYear_calculatePercentage_throwException(int invalidValue) {
        Assertions.assertThrows(IllegalArgumentException.class
                , () -> calculator.calculateDaysPassedPercentage(invalidValue)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 366})
    public void validDayOfYear_calculatePercentage_successful(int invalidValue) {
         calculator.calculateDaysPassedPercentage(invalidValue);
    }

    @Test
    public void whenCalculatePercentageThenCorrectResult() {
        int actualPercentage = calculator.calculateDaysPassedPercentage(200);
        Assertions.assertEquals(54, actualPercentage);
    }

    @Test
    public void whenGetYearProgressThenReturnAnArrayWithSize20() {
        int[] array = calculator.getYearProgress(1);
        Assertions.assertEquals(20, array.length);
    }

    @Test
    public void givenDayIs200WhenGetYearProgressThenReturnArrayHas10FullBlocks() {
        int[] array = calculator.getYearProgress(200);
        Assertions.assertArrayEquals(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                , array);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 367})
    public void givenInvalidInputsWhenGetYearProgressThenThrowException(int value) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculator.getYearProgress(value));
    }

}

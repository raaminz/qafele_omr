package com.github.zare88.qafeleomr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QafeleOmrStatusFormatterTest {

    private QafeleOmrStatusFormatter formatter;

    @BeforeEach
    public void beforeEach() {
        formatter = new QafeleOmrStatusFormatter();
    }

    @Test
    public void givenNullParameterWhenFormatThenThrowException(){
        Assertions.assertThrows(NullPointerException.class,
                () -> formatter.formatProgress(null)
        );
    }

    @Test
    public void givenUpperLimitArrayParameterWhenFormatThenThrowException(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> formatter.formatProgress(new int[21])
        );
    }

    @Test
    public void givenFourBlocksFilledWhenFormatThenReturn10BlockCharacter(){
        int[] yearProgress = new int[]{
                1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,0 ,0 ,0 ,0, 0, 0 ,0 ,0 ,0, 0};
        String expected = "▉▉▉▉▉▉▉▉▉▉▁▁▁▁▁▁▁▁▁▁";
        String actual = formatter.formatProgress(yearProgress);
        Assertions.assertEquals(expected , actual);
    }

    @Test
    public void formatPercent_returnString(){
        String actualString = formatter.formatPercent(25);
        Assertions.assertEquals("25%", actualString );
    }

}

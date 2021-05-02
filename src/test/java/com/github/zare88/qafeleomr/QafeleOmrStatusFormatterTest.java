package com.github.zare88.qafeleomr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QafeleOmrStatusFormatterTest {

    QafeleOmrStatusFormatter formatter;

    @BeforeEach
    void beforeEach() {
        formatter = new QafeleOmrStatusFormatter();
    }

    @Test
    void nullParameter_formatProgress_throwException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> formatter.formatProgress(null)
        );
    }

    @Test
    void upperLimitArrayParameter_formatProgress_throwException() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> formatter.formatProgress(new int[21])
        );
    }

    @Test
    void fourBlocksFilled_formatProgress_return10BlockCharacter() {
        int[] yearProgress = new int[]{
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        String expected = "▉▉▉▉▉▉▉▉▉▉▁▁▁▁▁▁▁▁▁▁";
        String actual = formatter.formatProgress(yearProgress);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void formatPercent_returnString() {
        String actualString = formatter.formatPercent(25);
        Assertions.assertEquals("25%", actualString);
    }

}

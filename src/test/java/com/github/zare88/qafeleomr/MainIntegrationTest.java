package com.github.zare88.qafeleomr;

import com.github.zare88.qafeleomr.exception.QafeleOmrTwitterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class MainIntegrationTest {

    @Mock
    JalaliDateUtil jalaliDateUtil;

    @Mock
    QafeleOmrTwitterService twitterService;

    @InjectMocks
    Main main;

    @BeforeEach
    void beforeEach() {
        Mockito.when(jalaliDateUtil.getTodayDayOfYear()).thenReturn(32);
        Mockito.when(jalaliDateUtil.getCurrentYear()).thenReturn(1400);
    }

    @Test
    void todayIs1stOfOrdibehesht_getTweetStatus_messageIsCorrect() {
        String actualStatus = main.getTweetStatus();
        String expectedStatus = "▉▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁ 8% / 1400";
        Assertions.assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void todayIs1stOfOrdibehesht_sendStatus_messageSent() throws QafeleOmrTwitterException {
        main.updateStatus();
        String expectedStatus = "▉▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁ 8% / 1400";
        Mockito.verify(twitterService).tweet(expectedStatus);
    }
}
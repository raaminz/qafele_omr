package com.github.zare88.qafeleomr;

import com.github.zare88.qafeleomr.exception.QafeleOmrTwitterException;
import org.junit.jupiter.api.Assertions;
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
public class MainIntegrationTest {

    @Mock
    private JalaliDateUtil jalaliDateUtil;

    @Mock(name = "twitterService")
    private QafeleOmrTwitterService twitterService;

    @InjectMocks
    private Main main;

    @Test
    public void todayIs1stOfOrdibehesht_getTweetStatus_messageIsCorrect(){
        Mockito.when(jalaliDateUtil.getTodayDayOfYear()).thenReturn(32);
        String actualStatus = main.getTweetStatus();

        String expectedStatus = "▉▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁ 8%";
        Assertions.assertEquals(expectedStatus , actualStatus);
    }
    @Test
    public void todayIs1stOfOrdibehesht_sendStatus_messageSent() throws QafeleOmrTwitterException {
        Mockito.when(jalaliDateUtil.getTodayDayOfYear()).thenReturn(32);
        main.updateStatus();

        String expectedStatus = "▉▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁ 8%";

        Mockito.verify(twitterService).tweet(expectedStatus);
    }

}

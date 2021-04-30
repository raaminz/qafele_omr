package com.github.zare88.qafeleomr;

import com.ibm.icu.util.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class JalaliDateUtilTest{

    @Test
    public void sixthOfOrdibehesht_getDayOfYear_return37(){
        JalaliDateUtil dateUtil = new JalaliDateUtil();
        int actualDayOfYear = dateUtil.getDayOfYear(1 , 6);
        Assertions.assertEquals(37 , actualDayOfYear);
    }

}

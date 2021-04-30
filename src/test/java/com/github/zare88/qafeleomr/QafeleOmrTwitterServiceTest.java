package com.github.zare88.qafeleomr;

import com.github.zare88.qafeleomr.exception.QafeleOmrTwitterException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import twitter4j.*;

import java.util.Date;
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class QafeleOmrTwitterServiceTest {

    @Mock
    private Twitter twitter;

    private QafeleOmrTwitterService service;

    @BeforeEach
    public void beforeEach() {
        service = new QafeleOmrTwitterService(twitter);
    }

    @Test
    public void updateStatus_successful() throws Exception {
        service.tweet("SALAM");
        Mockito.verify(twitter).updateStatus("SALAM");
    }

    @Test
    public void emptyString_updateStatus_throwsException(){
        Assertions.assertThrows(IllegalArgumentException.class , ()-> service.tweet(null));
        Assertions.assertThrows(IllegalArgumentException.class , ()-> service.tweet(""));
    }

}

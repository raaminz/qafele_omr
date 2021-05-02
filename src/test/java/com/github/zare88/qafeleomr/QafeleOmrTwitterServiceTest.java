package com.github.zare88.qafeleomr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import twitter4j.Twitter;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class QafeleOmrTwitterServiceTest {

    @Mock
    Twitter twitter;

    QafeleOmrTwitterService service;

    @BeforeEach
    void beforeEach() {
        service = new QafeleOmrTwitterService(twitter);
    }

    @Test
    void updateStatus_successful() throws Exception {
        service.tweet("SALAM");
        Mockito.verify(twitter).updateStatus("SALAM");
    }

    @Test
    void emptyString_updateStatus_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.tweet(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.tweet(""));
    }
}
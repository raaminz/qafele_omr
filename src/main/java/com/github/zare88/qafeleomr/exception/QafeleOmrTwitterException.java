package com.github.zare88.qafeleomr.exception;

import twitter4j.TwitterException;

public class QafeleOmrTwitterException extends Exception {

    public QafeleOmrTwitterException(TwitterException e) {
        super(e);
    }
}

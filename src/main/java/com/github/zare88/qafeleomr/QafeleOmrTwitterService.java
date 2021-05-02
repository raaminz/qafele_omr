package com.github.zare88.qafeleomr;

import com.github.zare88.qafeleomr.exception.QafeleOmrTwitterException;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import java.util.Objects;

public class QafeleOmrTwitterService {

    private final Twitter twitter;

    public QafeleOmrTwitterService(String consumerKey, String consumerSecret,
                                   String token, String tokenSecret) {
        this.twitter = initTwitterBot(consumerKey, consumerSecret, token, tokenSecret);
    }

    QafeleOmrTwitterService(Twitter twitter) {
        this.twitter = twitter;
    }

    public void tweet(String status) throws QafeleOmrTwitterException {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        try {
            twitter.updateStatus(status);
        } catch (TwitterException e) {
            throw new QafeleOmrTwitterException(e);
        }
    }

    private Twitter initTwitterBot(String consumerKey, String consumerSecret,
                                   String token, String tokenSecret) {
        var twitterObj = TwitterFactory.getSingleton();
        twitterObj.setOAuthConsumer(Objects.requireNonNull(consumerKey),
                Objects.requireNonNull(consumerSecret));
        var accessToken = new AccessToken(Objects.requireNonNull(token),
                Objects.requireNonNull(tokenSecret));
        twitterObj.setOAuthAccessToken(accessToken);
        return twitterObj;
    }
}

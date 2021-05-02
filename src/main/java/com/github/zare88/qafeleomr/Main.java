package com.github.zare88.qafeleomr;

import com.github.zare88.qafeleomr.exception.QafeleOmrTwitterException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    private final JalaliDateUtil jalaliDateUtil;
    private final QafeleOmrTwitterService twitterService;

    private static String getKey(String key){
        return System.getenv().get(key);
    }
    public Main(){
        this.jalaliDateUtil = new JalaliDateUtil();
        this.twitterService = new QafeleOmrTwitterService(getKey("CONSUMER_KEY"), getKey("CONSUMER_SECRET"),
                getKey("TOKEN"), getKey("TOKEN_SECRET"));
    }

    Main(JalaliDateUtil jalaliDateUtil , QafeleOmrTwitterService twitterService){
        this.jalaliDateUtil = jalaliDateUtil;
        this.twitterService = twitterService;
    }

    public String getTweetStatus() {
        int today = jalaliDateUtil.getTodayDayOfYear();
        ProgressYearCalculator calculator = new ProgressYearCalculator();

        QafeleOmrStatusFormatter formatter = new QafeleOmrStatusFormatter();
        String percentStr = formatter.formatPercent(calculator.calculateDaysPassedPercentage(today));
        String progressYearStr = formatter.formatProgress(calculator.getYearProgress(today));

        return "%s %s".formatted(progressYearStr ,percentStr);
    }

    public void updateStatus() throws QafeleOmrTwitterException {
        String status = getTweetStatus();
        LOG.log(Level.INFO , () -> "Today's status is %s".formatted(status));
        twitterService.tweet(status);
    }

    public static void main(String[] args) {
        try {
            LOG.info("QafeleOmr tweeter sender is trying to produce the status and sent it.");
            new Main().updateStatus();
            LOG.log(Level.INFO , "Status updated successfully. Done.");
        } catch (QafeleOmrTwitterException e) {
            LOG.log(Level.SEVERE,"Trouble when sending today's tweet" , e);
        }
    }
}

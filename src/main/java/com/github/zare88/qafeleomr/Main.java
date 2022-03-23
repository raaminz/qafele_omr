package com.github.zare88.qafeleomr;

import com.github.zare88.qafeleomr.exception.QafeleOmrTwitterException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    private final JalaliDateUtil jalaliDateUtil;
    private final QafeleOmrTwitterService twitterService;

    public Main() {
        this.jalaliDateUtil = new JalaliDateUtil();
        this.twitterService = new QafeleOmrTwitterService(getKey("CONSUMER_KEY"), getKey("CONSUMER_SECRET"),
                getKey("TOKEN"), getKey("TOKEN_SECRET"));
    }

    Main(JalaliDateUtil jalaliDateUtil, QafeleOmrTwitterService twitterService) {
        this.jalaliDateUtil = jalaliDateUtil;
        this.twitterService = twitterService;
    }

    private static String getKey(String key) {
        return System.getenv().get(key);
    }

    public static void main(String[] args) {
        try {
            DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
            if (dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.WEDNESDAY) {
                LOG.info("QafeleOmr tweeter sender is trying to produce the status and sent it.");
                new Main().updateStatus();
                LOG.log(Level.INFO, "Status updated successfully. Done.");
            } else {
                LOG.info("QafeleOmr only send tweets on Wednesdays and Sundays");
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Trouble when sending tweet of day", e);
        }
    }

    public String getTweetStatus() {
        int today = jalaliDateUtil.getTodayDayOfYear();
        var calculator = new ProgressYearCalculator();
        var formatter = new QafeleOmrStatusFormatter();
        String percentStr = formatter.formatPercent(calculator.calculateDaysPassedPercentage(today));
        String progressYearStr = formatter.formatProgress(calculator.getYearProgress(today));
        return "%s %s / %d".formatted(progressYearStr, percentStr, jalaliDateUtil.getCurrentYear());
    }

    public void updateStatus() throws QafeleOmrTwitterException {
        String status = getTweetStatus();
        LOG.log(Level.INFO, () -> "Today's status is %s".formatted(status));
        twitterService.tweet(status);
    }
}

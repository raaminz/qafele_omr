package com.github.zare88.qafeleomr;

import com.github.zare88.qafeleomr.exception.QafeleOmrTwitterException;

public class Main {

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
        twitterService.tweet(status);
    }

    public static void main(String[] args) {
        try {
            new Main().updateStatus();
        } catch (QafeleOmrTwitterException e) {
            e.printStackTrace();
        }
    }
}

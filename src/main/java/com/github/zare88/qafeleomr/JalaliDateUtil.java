package com.github.zare88.qafeleomr;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;

public class JalaliDateUtil {

    private Calendar getCalendar() {
        return Calendar.getInstance(new ULocale("fa_IR"));
    }

    /**
     * @param month starts from 0
     */
    public int getDayOfYear(int month, int dayOfMonth) {
        var calendar = getCalendar();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return calendar.get(java.util.Calendar.DAY_OF_YEAR);
    }

    public int getCurrentYear() {
        return getCalendar().get(java.util.Calendar.YEAR);
    }

    public int getTodayDayOfYear() {
        return getCalendar().get(java.util.Calendar.DAY_OF_YEAR);
    }

}

package lteii._10rooms.model;


import java.util.Calendar;
import java.util.GregorianCalendar;

import lteii._10rooms.utils.Utils;

public class Date {


    private final int day;
    private final int month;
    private final int year;
    private final int hour;
    private final int minute;
    private final int second;

    public Date(int day, int month, int year, int hour, int minute, int second) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public static Date now() {
        final GregorianCalendar calendar = new GregorianCalendar();
        return new Date(calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND));
    }


    @Override
    public String toString() {
        return Utils.formatUInteger(day, 2)
                +"/"+Utils.formatUInteger(month, 2)
                +"/"+Utils.formatUInteger(year, 4)
                +" "+Utils.formatUInteger(hour, 2)
                +"/"+Utils.formatUInteger(minute, 2)
                +"/"+Utils.formatUInteger(second, 2);
    }

}

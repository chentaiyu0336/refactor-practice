package com.twu.refactoring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {
    private final String dateAndTimeString;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES = new HashMap<String, TimeZone>();

    static {
        KNOWN_TIME_ZONES.put("UTC", TimeZone.getTimeZone("UTC"));
    }

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateAndTimeString - should be in format ISO 8601 format
     *                          examples -
     *                          2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateAndTimeString) {
        this.dateAndTimeString = dateAndTimeString;
    }

    public Date parse() {
        int year, month, date, hour, minute;

        year=praseTime("Year",0,4,2012,2000);
        month=praseTime("Month",5,7,12,1);
        date=praseTime("Date",8,10,31,1);



        if (dateAndTimeString.substring(11, 12).equals("Z")) {
            hour = 0;
            minute = 0;
        } else {
            hour=praseTime("Hour",11,13,23,0);
            minute=praseTime("Minute",14,16,59,0);

        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month - 1, date, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public int praseTime(String timeType,int startIndex,int endIndex,int max,int min) {
        int value;
        int len=endIndex-startIndex;
        try {
            String timeString = dateAndTimeString.substring(startIndex, endIndex);
            value = Integer.parseInt(timeString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(timeType+" string is less than "+len+" characters");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(timeType+" is not an integer");
        }
        if (value < min || value > max)
            throw new IllegalArgumentException(timeType+" cannot be less than "+min+" or more than "+max);
        return value;
    }
}

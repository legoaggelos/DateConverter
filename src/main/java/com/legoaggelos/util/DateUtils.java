package com.legoaggelos.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.zone.ZoneRulesException;
import java.util.Locale;

import static com.legoaggelos.util.DateFormattingUtils.addCharToCharArray;
import static com.legoaggelos.util.DateFormattingUtils.arrayToString;

public class DateUtils {
    public static String convertDateTimeToString(ZonedDateTime now) {
        String result = now.toString().split("\\.")[0];
        String[] removeT = result.split("T");
        return removeT[0] + "@" + removeT[1];
    }
    public static String convertDateTimeToStringNew(ZonedDateTime now) {
        /*String[] result = now.toString().split(":");
        String remaining = arrayToString(result).split("\\[")[0].split("\\.")[0].replace('T','@');
        char[] charArray = remaining.toCharArray();
        for (int i = 0; i <3.1; i += 3) {
            charArray=addCharToCharArray(charArray,remaining.indexOf("@")+3+i,':');
        }
        String temp = arrayToString(charArray);
        temp=temp.split("-")[0]+"-"+temp.split("-")[1]+"-"+temp.split("-")[2];
        return temp.replace(":","-");*/
        Locale usLocale = new Locale("en", "US");

        ZonedDateTime today = now;

        DateTimeFormatter myDateTimeFormatter = DateTimeFormatter
                .ofPattern("uuuu-MM-dd@HH-mm-ss");
        return today.format(myDateTimeFormatter);
    }
    public static ZoneId customZoneId(String timezone) throws DateTimeException {
        timezone=timezone.trim().toUpperCase();
        if(timezone.equals("PST")||timezone.equals("PT")){
            return ZoneId.of("UTC-8");
        }
        if(timezone.equals("PDT")){
            return ZoneId.of("UTC-7");
        }
        if(timezone.equals("EST")){
            return ZoneId.of("UTC-5");
        }
        if(timezone.equals("EDT")){
            return ZoneId.of("UTC-4");
        }
        if(timezone.equals("CDT")||timezone.equals("CT")){
            return ZoneId.of("UTC-5");
        }
        if(timezone.equals("CST")){
            return ZoneId.of("UTC-6");
        }
        if(timezone.equals("MST")){
            return ZoneId.of("UTC-7");
        }
        if(timezone.equals("MDT")){
            return ZoneId.of("UTC-6");
        }
        if(timezone.equals("AEST")){
            return ZoneId.of("UTC+10");
        }
        if(timezone.equals("AEDT")){
            return ZoneId.of("UTC+11");
        }
        if(timezone.equals("CET")){
            return ZoneId.of("UTC+1");
        }
        return ZoneId.of(timezone);
    }
}

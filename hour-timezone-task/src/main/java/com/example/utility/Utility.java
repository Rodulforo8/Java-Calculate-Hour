package com.example.utility;

import com.example.models.ErrorDetail;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Service
public class Utility {

    public final static String DEFAULT_TIME_FORMAT =  "HH:mm:ss";

	 public static final TimeZone utcTZ = TimeZone.getTimeZone("UTC");

     public static long toLocalTime(long time, TimeZone to) {
         return convertTime(time, utcTZ, to);
     }

     public static long toUTC(long time, TimeZone from) {
         return convertTime(time, from, utcTZ);
     }

     public static long convertTime(long time, TimeZone from, TimeZone to) {
         return time + getTimeZoneOffset(time, from, to);
     }

     private static long getTimeZoneOffset(long time, TimeZone from, TimeZone to) {
         int fromOffset = from.getOffset(time);
         int toOffset = to.getOffset(time);
         int diff = 0;

         if (fromOffset >= 0){
             if (toOffset > 0){
                 toOffset = -1*toOffset;
             } else {
                 toOffset = Math.abs(toOffset);
             }
             diff = (fromOffset+toOffset)*-1;
         } else {
             if (toOffset <= 0){
                 toOffset = -1*Math.abs(toOffset);
             }
             diff = (Math.abs(fromOffset)+toOffset);
         }
         return diff;
     }

    public ErrorDetail sendErrorDetail(String message, String field){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setMessage(message);
        errorDetail.setField(field);
        return errorDetail;
    }

    public boolean validateTimezone(String timezone){
        if(ZoneId.getAvailableZoneIds().contains(timezone)) return true;
        try{
            ZoneOffset.of(timezone);
            return true;
        }
        catch (DateTimeException e){};
        return false;
    }

    public boolean validateTime(String time){
        return validateTime(time,DEFAULT_TIME_FORMAT);
    }


    public boolean validateTime(String time, String format){
        try{
            new SimpleDateFormat(format).parse(time);
            return  true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    public Date addHoursToJavaUtilDate( int hour, int minute, int seconds, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, seconds);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
    
    public Calendar addHoursToJavaUtcDate( int hour, int minute, int seconds, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, seconds);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar;
    }

    public Date getTime(String time, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(time);
    }

    public ZoneOffset getZoneOffset(String timezone){
        if(ZoneId.getAvailableZoneIds().contains(timezone)) return ZoneId.of(timezone).getRules().getOffset(Instant.now());
        return ZoneOffset.of(timezone);
    }

    public String getTimeFormatted(Date time, String format) throws ParseException {
        return new SimpleDateFormat(format).format(time);
    }

}

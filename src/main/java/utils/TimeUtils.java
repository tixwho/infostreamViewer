package utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    
    public static long getTimeStamp(String formattedTime) {
        long time = (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"))
            .parse(formattedTime, new ParsePosition(0)).getTime() / 1000;
        return time;
    }
    
    public static String currentFormattedDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}

package jam.Util;

import java.time.Duration;
import java.time.Month;
import java.util.Calendar;

public class DateUtil {
    public static String CalenderToString(Calendar cal){
        int Day =  cal.get(Calendar.DATE);
        int Month =  cal.get(Calendar.MONTH);
        int Year = cal.get(Calendar.YEAR);

        return Integer.toString(Day)+ "/" + Integer.toString(Month)+ "/" + Integer.toString(Year);
    }
    public static String DurationToString(Duration dur){
        long Hours =  dur.toHours();
        long Mins =  dur.toMinutes() - (Hours*60);

        return Long.toString(Hours) + ":" + Long.toString(Mins);
    }
}

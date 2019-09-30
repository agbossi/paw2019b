package ar.edu.itba.paw.persistence;

import java.sql.Timestamp;
import java.util.Calendar;

public class DateHelper {

    public static Calendar dateToCalendar(Timestamp date){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date.getTime());
        return c;
    }

}


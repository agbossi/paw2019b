package ar.edu.itba.paw.persistence;

import java.sql.Timestamp;
import java.util.Calendar;

public class DateHelper {

    // Private constructor to prevent instantiation
    private DateHelper(){
        throw new UnsupportedOperationException();
    }

    public static Calendar dateToCalendar(Timestamp date){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date.getTime());
        return c;
    }

}


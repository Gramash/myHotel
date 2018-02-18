package Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {


    public static String formatDate(String date, String initDateFormat, String endDateFormat) {

        Date initDate = null;
        try {
            initDate = new SimpleDateFormat(initDateFormat).parse(date);
        } catch (ParseException | NullPointerException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
        return formatter.format(initDate);
    }

    public static boolean datesOverlap(String startDateStr1, String endDateStr1, String startDateStr2, String endDateStr2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate1 = sdf.parse(startDateStr1);
        Date startDate2 = sdf.parse(startDateStr2);
        Date endDate1 = sdf.parse(endDateStr1);
        Date endDate2 = sdf.parse(endDateStr2);
        if (((startDate1.before(endDate2)) &&
                (startDate2.equals(endDate1) || (startDate2).before(endDate1)))) {
            return true;
        }

        return false;
    }

    public static float countDays(String firstDate, String secondDate) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        long diff = 0;
        try {
            Date date1 = myFormat.parse(firstDate);
            Date date2 = myFormat.parse(secondDate);
            diff = date2.getTime() - date1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff / (1000 * 60 * 60 * 24);
    }

    public static java.sql.Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        date = calendar.getTime();
        return new java.sql.Date(date.getTime());
    }

    public static boolean isBeforeToday(String dateStr) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = df.parse(dateStr);
        Calendar c = Calendar.getInstance();

// set the calendar to start of today
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

// and get that as a Date
        Date today = c.getTime();

        c.setTime(date);
        Date date2Check = c.getTime();
        if (date2Check.before(today)) {
            System.out.println("Doctor Who?");
            return true;
        }
        return false;
    }

    public static Date stringToDate(String dateStr){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String dateToString (Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static void main(String[] args) {
        Date date = stringToDate("2008-01-01");
        System.out.println(dateToString(date));
    }


}

package Utils;

import JavaBeans.UserAccount;
import config.SecurityConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {


    public static String formatDate (String date, String initDateFormat, String endDateFormat){

        Date initDate = null;
        try {
            initDate = new SimpleDateFormat(initDateFormat).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
        return formatter.format(initDate);
    }

    public static boolean datesOverlap (String startDateStr1, String endDateStr1, String startDateStr2, String endDateStr2){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate1 = sdf.parse(startDateStr1);
            Date startDate2 = sdf.parse(startDateStr2);
            Date endDate1 = sdf.parse(endDateStr1);
            Date endDate2 = sdf.parse(endDateStr2);
            if((startDate1.equals(endDate2)||(startDate1.before(endDate2)) &&
                    (startDate2.equals(endDate1)||(startDate2).before(endDate1)))){
                return true;
            }
        } catch (ParseException e) {
            System.out.println("cant parse Dates");
            e.printStackTrace();
        }
        return false;
    }

    public static float countDays (String firstDate, String secondDate ) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        long diff=0;
        try {
            Date date1 = myFormat.parse(firstDate);
            Date date2 = myFormat.parse(secondDate);
            diff = date2.getTime() - date1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff / (1000*60*60*24);
    }
}

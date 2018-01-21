package Utils;

import JavaBeans.UserAccount;
import config.SecurityConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserUtils {

    public boolean emailIsValid (){
        return false;
    }

    public static boolean hasPermission (UserAccount user) {
        return user.getAccessLevel().equals(SecurityConfig.ROLE_MANAGER);
    }

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
}

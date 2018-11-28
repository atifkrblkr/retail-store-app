package org.ak.billing.helpers;

import org.ak.billing.constants.ApplicationConstants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Utility {

    public static void println(Object o) {
        if ((Boolean) ApplicationConstants.SHOW_LOGS.getApplicationConstant()) {
            System.out.println(o);
        }
    }

    public static String getFormattedDate(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
                (String) ApplicationConstants.DATE_TIME_FORMAT.getApplicationConstant()
        );
        return localDateTime.format(dateTimeFormatter);
    }

    public static String getCSVFromList(List list){
        String csv = "";
        if(list!=null && !list.isEmpty()){
            for(Object object : list){
                csv += object.toString()+", ";
            }
            csv = csv.substring(0, csv.lastIndexOf(", "));
        }
        return csv;
    }

}

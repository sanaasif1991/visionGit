package Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarUtil {
	public String getDate(String Date) throws ParseException {
		 SimpleDateFormat day = new SimpleDateFormat("d", Locale.US);
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 String actualDate = Date;
		 Date formatter = sdf.parse(actualDate);
		 String date = day.format(formatter);
		 System.out.println("Day :" + date);  
       return date;
	    }
	public String getMonth(String Date) throws ParseException {
		 SimpleDateFormat month_date = new SimpleDateFormat("MMMM yyyy", Locale.US);
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 String actualDate = Date;
		 Date formatter = sdf.parse(actualDate);
		 String month_name = month_date.format(formatter);
		 System.out.println("Month :" + month_name);  //Mar 2016
       return month_name;	
    }
}

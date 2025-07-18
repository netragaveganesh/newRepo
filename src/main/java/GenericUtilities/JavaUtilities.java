package GenericUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtilities {

	public int generateRandomNumber() {
		Random r= new Random();
		int randomNum=r.nextInt(1000);
		return randomNum;
	}
	
	
	public String getSystemCurrentDate() {
		Date d= new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String currentDate=sdf.format(d);
		return currentDate;
	}
	
	public String getDateAfterSpecificDays(int days) {
		Date d= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate=sdf.format(d);
		Calendar cal=sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String endDate=sdf.format(cal.getTime());
		return endDate;
		
	}
}

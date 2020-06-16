package com.kdj.mlink.common;

//import java.sql.Date;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.TimeZone;
import org.eclipse.swt.widgets.DateTime;

public class YDMATimeUtil {

	public static String getQuestDate(DateTime dateTime) {
		int month = dateTime.getMonth() + 1;
		String monthStr = Integer.toString(month);
		if (month < 10) {
			monthStr = "0" + month;
		}
		int day = dateTime.getDay();
		String dayStr = Integer.toString(day);
		if (day < 10) {
			dayStr = "0" + day;
		}
		String dateStr = dateTime.getYear() + "/" + monthStr + "/" + dayStr;

		return dateStr;
	}

	public static String getOrddtDate(DateTime dateTime) {
		int month = dateTime.getMonth() + 1;
		String monthStr = Integer.toString(month);
		if (month < 10) {
			monthStr = "0" + month;
		}
		int day = dateTime.getDay();
		String dayStr = Integer.toString(day);
		if (day < 10) {
			dayStr = "0" + day;
		}
		String dateStr = dateTime.getYear() + monthStr + dayStr;

		return dateStr;
	}

	public static String getStatisDate(DateTime dateTime) {
		int month = dateTime.getMonth();
		String monthStr = Integer.toString(month);
		if (month < 10) {
			monthStr = "0" + month;
		}
		int day = dateTime.getDay();
		String dayStr = Integer.toString(day);
		if (day < 10) {
			dayStr = "0" + day;
		}
		String dateStr = dateTime.getYear() + monthStr + dayStr;

		return dateStr;
	}

	public static String getRoRddtDate(DateTime dateTime) {
		int month = dateTime.getMonth() + 1;
		String monthStr = Integer.toString(month);
		if (month < 10) {
			monthStr = "0" + month;
		}
		int day = dateTime.getDay();
		String dayStr = Integer.toString(day);
		if (day < 10) {
			dayStr = "0" + day;
		}
		String dateStr = dateTime.getYear() + "-" + monthStr + "-" + dayStr;

		return dateStr;
	}

	public static String getClaimmDate(DateTime dateTime) {
		int month = dateTime.getMonth() + 1;
		String monthStr = Integer.toString(month);
		if (month < 10) {
			monthStr = "0" + month;
		}
		int day = dateTime.getDay();
		String dayStr = Integer.toString(day);
		if (day < 10) {
			dayStr = "0" + day;
		}
		String dateStr = dateTime.getYear() + monthStr + dayStr;

		return dateStr;
	}

	public static String getStatistcal(DateTime dateTime) {
		int month = dateTime.getMonth() + 1;
		String monthStr = Integer.toString(month);
		if (month < 10) {
			monthStr = "0" + month;
		}
		int day = dateTime.getDay();
		String dateStr = dateTime.getYear() + "년 " + monthStr + "월";

		return dateStr;
	}

	public static String getClaimOrddtDate(DateTime dateTime) {
		// 사방엣 주문일자 포맷으로 전환함.
		int month = dateTime.getMonth() + 1;
		String monthStr = Integer.toString(month);
		if (month < 10) {
			monthStr = "0" + month;
		}

		int day = dateTime.getDay();
		String dayStr = Integer.toString(day);
		if (day < 10) {
			dayStr = "0" + day;
		}

		int hour = dateTime.getHours();
		String hourStr = Integer.toString(hour);
		if (hour < 10) {
			hourStr = "0" + hourStr;
		}

		int min = dateTime.getMinutes();
		String minStr = Integer.toString(min);
		if (min < 10) {
			minStr = "0" + minStr;
		}

		int sec = dateTime.getSeconds();
		String secStr = Integer.toString(sec);
		if (sec < 10) {
			secStr = "0" + secStr;
		}

		String dateStr = dateTime.getYear() + monthStr + dayStr + hourStr + minStr + secStr;

		return dateStr;
	}

	public static String getClaimOrddtMinutes(String dateTime) {
		// 사방엣 주문일자 포맷으로 전환함.

		int hour = Integer.parseInt(dateTime.substring(11, 13));
		if (hour < 12) {
			dateTime = "오전 " + dateTime.subSequence(11, dateTime.length());
		} else {
			dateTime = "오후" + dateTime.subSequence(11, dateTime.length());
		}

		return dateTime;
	}

	public static void setOrddtDate(DateTime dateTime) {
		String currentTiem = getCurrentTime();
		int year = Integer.parseInt(currentTiem.substring(0, 4));
		int month = Integer.parseInt(currentTiem.substring(4, 6));
		int day = Integer.parseInt(currentTiem.substring(6, 8));

		dateTime.setYear(year);
		dateTime.setMonth(month - 1);
		dateTime.setDay(day);

	}

	public static String getCurrentTime() {
		long time = System.currentTimeMillis();
		SimpleDateFormat simpleTime = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentTime = simpleTime.format(new Date(time));
		return currentTime;
	}

	public static String getCurrentDate() {
		long time = System.currentTimeMillis();
		SimpleDateFormat simpleTime = new SimpleDateFormat("yyyy.MM.dd");
		String currentDate = simpleTime.format(new Date(time));
		return currentDate;
	}
	
	public static String getCurrentDateSchedulerMin() {
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("current: " + df.format(cal.getTime()));

        cal.add(Calendar.MONTH, -1);

        long time = cal.getTimeInMillis();
		SimpleDateFormat simpleTime = new SimpleDateFormat("yyyy-MM-dd");		
		String currentDate = simpleTime.format(new Date(time));
		return currentDate;
        
	}
	
	public static String getCurrentDateScheduler() {
		long time = System.currentTimeMillis();
		SimpleDateFormat simpleTime = new SimpleDateFormat("yyyy-MM-dd");		
		String currentDate = simpleTime.format(new Date(time));
		return currentDate;
	}

	public static String getCurrentDateHanjin() {
		long time = System.currentTimeMillis();
		SimpleDateFormat simpleTime = new SimpleDateFormat("yyyyMMdd");
		String currentDate = simpleTime.format(new Date(time));
		return currentDate;
	}

	public static String getCurrentTimeByNewFormat() {
		long time = System.currentTimeMillis();
		SimpleDateFormat simpleTime = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
		String currentTime = simpleTime.format(new Date(time));
		return currentTime;
	}

	public static String getCurrentTimeByYDFormat() {
		long time = System.currentTimeMillis();
		SimpleDateFormat simpleTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleTime.format(new Date(time));
		return currentTime;
	}

	public static String getCurrentTimeByFreeFormat(String format) {
		long time = System.currentTimeMillis();
		SimpleDateFormat simpleTime = new SimpleDateFormat(format);
		String currentTime = simpleTime.format(new Date(time));
		return currentTime;
	}

	public static String getCurrentTimeOnly() {
		long time = System.currentTimeMillis();
		SimpleDateFormat simpleTime = new SimpleDateFormat("HHmmss");
		String currentTime = simpleTime.format(new Date(time));
		return currentTime;
	}

	public static String getCurrentTimeOnlyKaKao() {
		long time = System.currentTimeMillis();
		SimpleDateFormat simpleTime = new SimpleDateFormat("HH:mm");
		String currentTime = simpleTime.format(new Date(time));
		return currentTime;
	}

	public static String getCurrentTimeScheduler(DateTime dateTime) {
		int hour_1 = dateTime.getHours();
		String hour_1Str = Integer.toString(hour_1);
		if (hour_1 < 10) {
			hour_1Str = "0" + hour_1Str;
		}
		int min_1 = dateTime.getMinutes();
		String min_1Str = Integer.toString(min_1);
		if (min_1 < 10) {
			min_1Str = "0" + min_1Str;
		}

		String exeTime_1 = hour_1Str + ":" + min_1Str;

		return exeTime_1;
	}

	public static String[] getZMonthDiffDay(int offset, String format) {
		String[] days = new String[2];

		// 현재날짜
		java.util.Date d = new java.util.Date();
		SimpleDateFormat df = new SimpleDateFormat(format);
		String currentDate = df.format(d);

		// offset 날짜
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, +offset);
		java.util.Date currentTime = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String offsetDate = formatter.format(currentTime);

		days[0] = currentDate; // 기준시간
		days[1] = offsetDate; // +-차이나는 시간

		return days;
	}

	public static String[] getZMonthBaseStkDay(int offset, String format, String maxDate) {

		String[] days = new String[2];

		try {

			// 현재날짜(to)
			java.util.Date d = new java.util.Date();
			SimpleDateFormat df = new SimpleDateFormat(format);
			String currentDate = df.format(d);

			// 기초재고 일자 (from)
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			java.util.Date fromDate = formatter.parse(maxDate); // parse()를 통해 Date형으로 변환.

			Calendar cal = Calendar.getInstance();
			cal.setTime(fromDate);
			cal.add(Calendar.DATE, offset);
			java.util.Date currentTime = cal.getTime();
			String offsetDate = formatter.format(currentTime);

			days[0] = currentDate; // 현재일자
			days[1] = offsetDate; // 기초재고일자 이후 +1일 일자

		} catch (Exception e) {
			e.printStackTrace();
			// MessageDialog.openInformation(getShell(), TITLE, e.getMessage());
		}

		return days;
	}

	public static String getOffset(String dateFrom, String dateTo) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// date1, date2 두 날짜를 parse()를 통해 Date형으로 변환.
		java.util.Date fromDate = format.parse(dateFrom);
		java.util.Date toDate = format.parse(dateTo);

		// Date로 변환된 두 날짜를 계산한 뒤 그 리턴값으로 long type 변수를 초기화 하고 있다.
		// 연산결과 -950400000. long type 으로 return
		int calDate = (int) (toDate.getTime() - fromDate.getTime());

		// Date.getTime() 은 해당날짜를 기준으로1970년 00:00:00 부터 몇 초가 흘렀는지를 반환
		// 이제 24*60*60*1000(각 시간값에 따른 차이점) 을 나눠주면 일수가 나온다.
//		int day = (int) (calDate / (60 * 60 * 24));
//		int hour = (int) ((calDate - day * 60 * 60 * 24) / (60 * 60)); 
		int seconds = (int) (calDate / 1000); // 초
		// int minutes = (int) ((calDate / (1000*60)) % 60); //분

		String date = String.valueOf(seconds);

//		if(calDateDays<0) {
//			return -1;
//		}
//		calDateDays = Math.abs(calDateDays);

		// System.out.println("두 날짜의 날짜 차이: " + calDateDays);

		return date;
	}

	public static String getTime(String dateFrom) throws Exception {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		// date1, date2 두 날짜를 parse()를 통해 Date형으로 변환.
		java.util.Date fromDate = format.parse(dateFrom);


		long time = fromDate.getTime();

		String date = String.valueOf(time);

		return date;
	}
	public static String getDate(String dateFrom) throws Exception {
		long milis = Long.parseLong(dateFrom);
		String convertTime = "";
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milis);
		
		Date numDate = calendar.getTime();
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		convertTime = format.format(numDate);


		return convertTime;
	}
	
	public static long getDayOffset(String dateFrom, String dateTo) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// date1, date2 두 날짜를 parse()를 통해 Date형으로 변환.
		java.util.Date fromDate = format.parse(dateFrom);
		java.util.Date toDate = format.parse(dateTo);

		// Date로 변환된 두 날짜를 계산한 뒤 그 리턴값으로 long type 변수를 초기화 하고 있다.
		// 연산결과 -950400000. long type 으로 return
		long calDate = toDate.getTime() - fromDate.getTime();

		// Date.getTime() 은 해당날짜를 기준으로1970년 00:00:00 부터 몇 초가 흘렀는지를 반환
		// 이제 24*60*60*1000(각 시간값에 따른 차이점) 을 나눠주면 일수가 나온다.
		long calDateDays = calDate / (24 * 60 * 60 * 1000);
		if (calDateDays < 0) {
			return -1;
		}
		calDateDays = Math.abs(calDateDays);

		// System.out.println("두 날짜의 날짜 차이: " + calDateDays);

		return calDateDays;
	}

	public static String convertSabangNetClaimDateToClmdt(String date, boolean shortFlag) {
		if (date.length() >= 8) {
			if (!shortFlag) {
				date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
				// 20190217155600 ==> 2019-01-18
			} else {
				date = date.substring(4, 6) + "/" + date.substring(6, 8);
				// 20190217155600 ==> 01/18
			}
		}

		return date;
	}

	public static String convertPickupExpress(String date) {
		if (date.length() >= 8) {
			date = date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10); // 2019-02-17 ==> 20190118
		}

		return date;
	}

	public static String convertShop11stDateToClmdt(String date, boolean shortFlag) {
		if (date.length() >= 8) {
			if (!shortFlag) {
				date = date.substring(0, 4) + "/" + date.substring(4, 6) + "/" + date.substring(6, 8);
				// 20190217155600 ==> 2019/01/18
			} else {
				date = date.substring(4, 6) + "/" + date.substring(6, 8);
				// 20190217155600 ==> 01/18
			}
		}

		return date;
	}

	public static String convertSabangNetOrderDateToOrddt(String date, boolean shortFlag) {
		if (date.length() >= 12) {
			if (!shortFlag) {
				date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " "
						+ date.substring(8, 10) + ":" + date.substring(10, 12);
				// 20190217155600 ==> 2019-01-18 10:34
			} else {
				date = date.substring(4, 6) + "/" + date.substring(6, 8);
				// 20190217155600 ==> 01/18
			}
		}

		return date;
	}

	public static String convertClaimContentsDateToClmdate(String date, boolean shortFlag) {
		if (date.length() >= 12) {
			// System.out.println("date=s" + date);
			if (shortFlag) {
				date = date.substring(5, 7) + "/" + date.substring(8, 10);
				// 2019-02-17 15:56:00 ==> 02/17
			}
		}
		// System.out.println("date=e" + date);
		return date;
	}

	public static void setDateTimeToDate8(DateTime dateTime, String date) {
		if (date.length() >= 8) {
			String year = date.substring(0, 4);
			String month = date.substring(4, 6);
			String day = date.substring(6, 8);
			dateTime.setYear(Integer.parseInt(year));
			dateTime.setMonth(Integer.parseInt(month) - 1);
			dateTime.setDay(Integer.parseInt(day));

		}
	}

	// Add 영업일 기준일수
	public static String getNetworkDay(String date1, int workingDaysToAdd) {

		Calendar startCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

		try {
			Date date2 = new SimpleDateFormat("yyyymmdd").parse(date1);
			startCal.setTime(date2);

			for (int i = 0; i < workingDaysToAdd; i++) {
				do {
					startCal.add(Calendar.DAY_OF_MONTH, 1);
				} while (!isWorkingDay(startCal));
			}

		} catch (Exception e) {
			e.printStackTrace();
			// MessageDialog.openInformation(getShell(), TITLE, e.getMessage());
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyymmdd");

		System.out.println("date=e" + dateFormat.format(startCal.getTime()));

		return dateFormat.format(startCal.getTime());
	}

	// 영업일(토,일요일 제외)
	private static boolean isWorkingDay(Calendar cal) {
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

//        if (dayOfWeek!=1) {
//        	dayOfWeek -= 1;
//        } else {
//        	dayOfWeek = 7;
//        };

		if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY)
			return false;
		return true;
	}

}
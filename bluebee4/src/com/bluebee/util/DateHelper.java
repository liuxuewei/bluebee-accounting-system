package com.bluebee.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
	public static String DATE_FORMAT_PATTERN_ONE = "yyyy-MM-dd";
	public static String DATE_FORMAT_PATTERN_TWO = "dd-MM-yyyy";
	public static String DATETIME_FORMAT_PATTERN_ONE = "yyyy-MM-dd HH:mm:ss";
	public static String DATETIME_FORMAT_PATTERN_TWO = "yyyy-MM-dd HH:mm:ss sss";
	public static String DATEFORMAT_DATE = "yyyy-MM-dd HH24:mi:ss";
	private static final String PATTERN = "yyyy-MM-dd";
	private static final String NOW_DATETIME_PATTERN = "yyyyMMddhhmmss";
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");

	public static String getNowDateTime() {
		return dateFormat.format(currentDate());
	}

	public static Date currentDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	public static boolean isDate(String date) {
		Date d;
		try {
			if ((date == null) || ("".equals(date))) {
				return false;
			}
			d = dateFormat.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		boolean ir = isDate("20091-2");
	}
}
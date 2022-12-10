package edu.chalkboarduni.uniregistrationsystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormatter {
	
	private static SimpleDateFormat dbFormatter = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat displayFormatter = new SimpleDateFormat("MM-dd-yyyy");
	
	private static SimpleDateFormat dbFormatterForTimeSlot = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat displayFormatterForTimeSlot = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");	
	
	public static String changeDateFormat(String dateString) {
		try {
			Date date = dbFormatter.parse(dateString);
			return displayFormatter.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String changeDateFormatForTimeSlot(String dateString) {
		try {
			Date date = dbFormatterForTimeSlot.parse(dateString);
			return displayFormatterForTimeSlot.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}

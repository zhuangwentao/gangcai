package com.gc.system.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zhuangwentao
 *	日期格式转化
 */
public class DateUtil {

	// 字符串转换日期格式为2011-10-19
	public static Date stringToDate(String str, String format) {

		SimpleDateFormat datePattern = new SimpleDateFormat(format);

		try {
			Date date = datePattern.parse(str);
			return date;
		} catch (ParseException e) {
		}
		return null;
	}

	// 添加日期转换字符串格式为2011-10-19
	public static String dateToString(Date date, String format) {

		SimpleDateFormat datePattern = new SimpleDateFormat(format);

		if (date == null) {
			return null;
		}
		try {
			String dateStr = datePattern.format(date);
			return dateStr;
		} catch (Exception e) {
		}
		return null;
	}

	public static boolean compareDateWithNow(Date endDate) {
		if(endDate == null) {
			return false;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(sdf.format(endDate).equals(sdf.format(new Date()))) {
			return true;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, +1);

		if(sdf.format(endDate).equals(sdf.format(calendar.getTime()))) {
			return true;
		}

		return false;
	}

	/**
	 * 取得月第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 取得上月第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfLastMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		c.add(Calendar.MONTH,-1);
		return c.getTime();
	}

	/**
	 * 取得月最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 取得上月最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfLastMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		c.add(Calendar.MONTH,-1);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	public static Date getNextDay(Date date, int dayNum) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				+ dayNum);
		return calendar.getTime();
	}

	/**
	 * 取得上月最后一天
	 */
	public static Date getPrevMonthLastDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date strDateTo = calendar.getTime();
		return strDateTo;
	}

	/**
	 * 取得下月第一天
	 */
	public static Date getNextMonthFirstDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month+1);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date strDateTo = calendar.getTime();
		return strDateTo;
	}

	/**
	 * 取得本周一
	 */
	public  static Date getThisMonday(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			calendar.add(Calendar.DATE,-1);
		}
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		return calendar.getTime();
	}

	/**
	 * 取得下周一
	 */
	public  static Date getNextMonday(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 1 + 7);
		return calendar.getTime();
	}

	/**
	 * 取得本周日
	 */
	public  static Date getThisSunday(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			calendar.add(Calendar.DATE,-1);
		}
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		calendar.add(Calendar.DATE,7);
		return calendar.getTime();
	}

	/**
	 * 取得下周日
	 */
	public  static Date getNextSunday(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 7 + 7);
		return calendar.getTime();
	}

	/**
	 * 取得本周 星期X 一：1 日：7
	 */
	public  static Date getThisWeek(Date date,Integer dayOfWeek){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + dayOfWeek );
		return calendar.getTime();
	}

	/**
	 * 取得下周 星期X 一：1 日：7
	 */
	public  static Date getNextWeek(Date date,Integer dayOfWeek){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + dayOfWeek + 7);
		return calendar.getTime();
	}

	public static void main(String[] args) {

	}



}

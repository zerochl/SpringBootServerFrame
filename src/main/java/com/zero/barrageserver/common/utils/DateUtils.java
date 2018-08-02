package com.zero.barrageserver.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 * 
 * @author blake
 * @version 1.0
 * @created 2015-1-19
 */

public class DateUtils {

	public static String getTimeByMilForMovieList(int time){
		int second = time / 1000;
		int minute = second / 60;
		int realSecond = second % 60;
		int realMinute = minute % 60;
		return (realMinute < 10 ? "0" + realMinute : realMinute) + "'" + (realSecond < 10 ? "0" + realSecond : realSecond) + "''";
	}

	public static String getNormalNow(){
		Date nowDate = new Date();
		return timeFormattingForDateByMilSec(nowDate.getTime());
	}

	/**
	 *
	 * @param startDate eg:2018-05-21 17:20
     * @param endDate eg:2018-05-22 17:20
	 * @return 2018.05.21 ~ 2018.05.22;2018.05.21 17:20 ~ 17:30
     */
	public static String getDotTime(String startDate,String endDate){
        String start = DateUtils.getDotYAndMAndD(startDate);
        String end = DateUtils.getDotYAndMAndD(endDate);
        String result;
        if(start.equals(end)){
            //同一天
            result = start + "  " + DateUtils.getTime(startDate) + " ~ " + DateUtils.getTime(endDate);
        }else{
            //不同天
            result = start + " ~ " + end;
        }
        return result;
	}
	
	/**
	 * 
	 * @param date eg:2018-05-21 17:20
	 * @return 2018年05月21日 17:20
	 */
	public static String getNormalDate(String date){
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sim2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		try {
			Date riLiDate = sim.parse(date);
			return sim2.format(riLiDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}

	/**
	 *
	 * @param date eg:2018-05-21 17:20
	 * @return2018.05.21 17:20
     */
	public static String getNormalNewDate(String date){
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sim2 = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		try {
			Date riLiDate = sim.parse(date);
			return sim2.format(riLiDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}

	public static String getNormalNewDateNoHour(String date){
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sim2 = new SimpleDateFormat("yyyy.MM.dd");
		try {
			Date riLiDate = sim.parse(date);
			return sim2.format(riLiDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}

	public static String getNormalNewDateNoHour(Date date){
		SimpleDateFormat sim2 = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		try {
			return sim2.format(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}

	/**
	 *
	 * @param date yyyy-MM-dd HH:mm
	 * @return HH:mm
     */
	public static String getHourAndMinuteFormate(String date){
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sim2 = new SimpleDateFormat("HH:mm");
		try {
			Date riLiDate = sim.parse(date);
			return sim2.format(riLiDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	/**
	 *
	 * @param secondDou
	 * @return mm:ss
	 */
	public static String getMinuteAndSecond(double secondDou){
		int second = 0;
		if(secondDou - (int)secondDou >= 0.5){
			second = (int)secondDou + 1;
		}else{
			second = (int)secondDou;
		}
		int minute = second / 60;
		int realSecond = second % 60;
		int realMinute = minute % 60;
		String strSecond = realSecond > 9 ? realSecond + "" : "0" + realSecond;
//		String strMinute = realMinute > 9 ? realMinute + "" : "0" + realMinute;
		String strMinute = realMinute + "";
		return strMinute + ":" + strSecond;
	}

	/**
	 *
	 * @param
	 * @return mm:ss
	 */
	public static String getMinuteAndSecond(int second){
		int minute = second / 60;
		int realSecond = second % 60;
		int realMinute = minute % 60;
		String strSecond = realSecond > 9 ? realSecond + "" : "0" + realSecond;
//		String strMinute = realMinute > 9 ? realMinute + "" : "0" + realMinute;
		String strMinute = realMinute + "";
		return strMinute + ":" + strSecond;
	}

    /**
     * 此方法完全依赖时间格式，谨慎使用
     * @param date 2016-10-12
     * @return 10月12日
     */
	public static String getMandY(String date) {
		String oldm_d = "";
		String newm_d = "";
		try{
			oldm_d = date.substring(5, 10);
			newm_d = oldm_d.replace("-", "月");
		}catch (Exception e){
			e.printStackTrace();
		}
		return newm_d + "日";
	}

    /**
     * 此方法完全依赖时间格式，谨慎使用
     * @param date 2016-10-12
     * @return 16年10月12日
     */
	public static String getYAndMAndD(String date){
		String oldm_d = "";
		String newm_d = "";
		String year = "";
		try{
			year = date.substring(2,4);
			oldm_d = date.substring(5, 10);
			newm_d = oldm_d.replace("-", "月");
		}catch (Exception e){
			e.printStackTrace();
		}
		return year + "年" + newm_d + "日";
	}

    /**
     * 此方法完全依赖时间格式，谨慎使用
     * @param date 2016-10-12
     * @return 16年10月12日
     */
    public static String getDotYAndMAndD(String date){
        String oldm_d = "";
        String newm_d = "";
        String year = "";
        try{
            year = date.substring(0,4);
            oldm_d = date.substring(5, 10);
            newm_d = oldm_d.replace("-", ".");
        }catch (Exception e){
            e.printStackTrace();
        }
        return year + "." + newm_d + "";
    }

	/**
	 * 获取日期字符串中的年份
	 * @param date eg：2018-05-21
	 * @return
	 */
	public static String getYear(String date) {
		if(StringUtil.isEmpty(date)){
			return "";
		}
		String year = date.substring(0,date.indexOf("-"));
		return year;
	}
	/**
	 * 获取日期字符串中的月份
	 * @param date eg：2018-05-21
	 * @return
	 */
	public static String getMonth(String date){
		if(StringUtil.isEmpty(date)){
			return "";
		}
		String month = date.substring(date.indexOf("-") + 1,date.lastIndexOf("-"));
		return month;
	}
	/**
	 * 获取日期字符串中的月份并增加单位“月”
	 * @param date
	 * @return 03->3
	 */
	public static String getMonthWithUnit(String date){
		if(StringUtil.isEmpty(date)){
			return "";
		}
		String month = date.substring(date.indexOf("-") + 1,date.lastIndexOf("-"));
		if("0".equals(month.substring(0, 1))){
			month = month.substring(1, month.length());
		}
		return month + "月";
	}
	/**
	 * 获取日期字符串中的日份
	 * @param date eg：2018-05-21
	 * @return
	 */
	public static String getDay(String date){
		if(StringUtil.isEmpty(date)){
			return "";
		}
		String day = date.substring(date.lastIndexOf("-") + 1,date.length());
		return day;
	}
	
	/**
	 * 获取日期字符串中的日份
	 * @param date eg：2018-05-21 17:20
	 * @return
	 */
	public static String getDayWithHour(String date){
		if(StringUtil.isEmpty(date)){
			return "";
		}
		String day = date.substring(date.lastIndexOf("-") + 1,date.indexOf(" "));
		return day;
	}
	/**
	 * 获取日期字符串中的小时
	 * @param date eg：2018-05-21 17:20
	 * @return
	 */
	public static String getHour(String date){
		if(StringUtil.isEmpty(date)){
			return "";
		}
		String hour = date.substring(date.indexOf(" ") + 1,date.indexOf(":"));
		return hour;
	}
	/**
	 * 获取日期字符串中的分钟
	 * @param date eg：2018-05-21 17:20
	 * @return
	 */
	public static String getMint(String date){
		if(StringUtil.isEmpty(date)){
			return "";
		}
		String mint = date.substring(date.indexOf(":") + 1,date.length());
		return mint;
	}
	/**
	 * 获取日期字符串中的分钟
	 * @param date eg：2018-05-21 17:20:01
	 * @return
	 */
	public static String getMintWithSecond(String date){
		if(StringUtil.isEmpty(date)){
			return "";
		}
		String mint = date.substring(date.indexOf(":") + 1,date.lastIndexOf(":"));
		return mint;
	}
	/**
	 * 给出发布时间与现在的间隔
	 * @param date
	 * @return 多少天、月、年之前
	 */
	public static String getTimeAgo(String date){
		int oldYear = Integer.parseInt(getYear(date));
		int nowYear = getYear();
		if(oldYear < nowYear){
			return (nowYear - oldYear) + "年之前";
		}
		int oldMonth = Integer.parseInt(getMonth(date));
		int nowMonth = getMonth();
		if(oldMonth < nowMonth){
			return (nowMonth - oldMonth) + "月之前";
		}
		int oldDay = Integer.parseInt(getDayWithHour(date));
		int nowDay = getDay();
		if(oldDay < nowDay){
			return (nowDay - oldDay) + "日之前";
		}
		int oldHour = Integer.parseInt(getHour(date));
		int nowHour = getHour();
		if(oldHour < nowHour){
			return (nowHour - oldHour) + "小时之前";
		}
		int oldMint = Integer.parseInt(getMint(date));
		int nowMint = getMinute();
		if(oldMint < nowMint){
			return (nowMint - oldMint) + "分钟之前";
		}
		return "当前发布";
	}
	public static String getTimeAgo2(String date){
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		long nmonth = 24 * 60 * 60 * 30;// 一月的毫秒数
		long nd = 24 * 60 * 60;// 一天的毫秒数
		long nh = 60 * 60;// 一小时的毫秒数
		long nm = 60;// 一分钟的毫秒数
		long ns = 1;// 一秒钟的毫秒数
		int month,day,hour,min;
		try {
			Date startDate = sim.parse(date);
			long delTime = (new Date().getTime() - startDate.getTime()) / 1000;
			month = (int)(delTime / nmonth);
			day = (int)(delTime / nd);
			hour = (int)(delTime / nh);
			min = (int)(delTime / nm);
			if(month > 0){
				return month + "月之前";
			}
			if(day > 0){
				return day + "天之前";
			}
			if(hour > 0){
				return hour + "小时之前";
			}
			if(min > 0){
				return min + "分钟之前";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "刚刚发布";
	}

    /**
     *
     * @param startTime 2016-12-23 12:40
     * @return 12.23 12:40
     */
	public static String getDateForArtistShowTime(String startTime){
		int startDay = Integer.parseInt(getDayWithHour(startTime));
		int startMonth = Integer.parseInt(getMonth(startTime));
		return startMonth + "月" + startDay + "日" + startTime.substring(startTime.indexOf(" "), startTime.length());
	}

	/**
	 *
	 * @param time 2016-12-23 12:40 or 12-23 12:40
	 * @return 12:10
     */
	public static String getHourAndMinute(String time){
		return time.substring(time.indexOf(" "), time.length());
	}
	
	public static String getTimeInterval(String startTime,String endTime){
		int startDay = Integer.parseInt(getDayWithHour(startTime));
		int endDay = Integer.parseInt(getDayWithHour(endTime));
		int startMonth = Integer.parseInt(getMonth(startTime));
		int endMonth = Integer.parseInt(getMonth(endTime));
		if(startMonth == endMonth && startDay == endDay){
			return getTimeWithOutYear(startTime) + " - " + endTime.substring(endTime.indexOf(" "), endTime.length());
		}else{
			return getTimeWithOutYear(startTime) + " - " + getTimeWithOutYear(endTime);
		}
	}
	
	public static String getTimeWithOutYear(String date){
		if(StringUtil.isEmpty(date)){
			return "";
		}
		String result = date.substring(date.indexOf("-") + 1, date.length());
		result = result.replace("-", "月");
		result = result.replace(" ", "日 ");
		return result;
	}

	public static String getTime(String date) {
		String time = date.substring(11, 16);
		return time;
	}

	public static int getYear() {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		return y;
	}

	public static int getMonth() {
		Calendar cal = Calendar.getInstance();
		int m = cal.get(Calendar.MONTH) + 1;
		// m=month;
		// if (m < 10) {
		// month = "0" + m;
		// }
		return m;
	}

	// 大写month
	public static String getBigXieMonth(int m) {
		String month = null;
		// Calendar cal = Calendar.getInstance();
		// int m = cal.get(Calendar.MONTH) + 1;
		switch (m) {
		case 1:
			month = "一";
			break;
		case 2:
			month = "二";
			break;
		case 3:
			month = "三";
			break;
		case 4:
			month = "四";
			break;
		case 5:
			month = "五";
			break;
		case 6:
			month = "六";
			break;
		case 7:
			month = "七";
			break;
		case 8:
			month = "八";
			break;
		case 9:
			month = "九";
			break;
		case 10:
			month = "十";
			break;
		case 11:
			month = "十一";
			break;
		case 12:
			month = "十二";
			break;
		}
		return month;
	}

	public static int getMonthNums(String start, String end) {
		int num = 1;
		// SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date startDate = sim.parse(start);

			Date endDate = sim.parse(end);
			int startYear = startDate.getYear();
			int startMonth = startDate.getMonth();
			int endYear = endDate.getYear();
			int endMonth = endDate.getMonth();
			if (startYear == endYear) {
				num = endMonth - startMonth + 1;
			} else if (startYear < endYear) {
				// 跨年的
				if (startMonth <= endMonth) {
					num = 12 * (endYear - startYear) + endMonth - startMonth
							+ 1;
				} else if (startMonth > endMonth) {
					num = 12 * (endYear - startYear) + (startMonth - endMonth)
							+ 1;
				}
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return num;
	}

	public static int getRiLiDay(String date) {
		int num = 1;
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date riLiDate = sim.parse(date);
			num = riLiDate.getDate();
			// int startYear=riLiDate.getYear();
			// int startMonth=riLiDate.getMonth();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return num;
	}

	public static int getDate() {
		// String day = null;
		Calendar cal = Calendar.getInstance();
		int d = cal.get(Calendar.DATE);
		// if (d < 10) {
		// day = "0" + d;
		// }
		return d;
	}
	
	public static int getDay(){
		Calendar cal = Calendar.getInstance();
		int h = cal.get(Calendar.DAY_OF_MONTH);
		return h;
	}

	public static int getHour() {
		Calendar cal = Calendar.getInstance();
		int h = cal.get(Calendar.HOUR_OF_DAY);
		return h;
	}

	public static int getMinute() {
		Calendar cal = Calendar.getInstance();
		int mi = cal.get(Calendar.MINUTE);
		return mi;
	}

	public static int getSecond() {
		Calendar cal = Calendar.getInstance();
		int s = cal.get(Calendar.SECOND);
		return s;
	}

	/**
	 * 时间戳转换为日期格式
	 * 
	 * @param time
	 *            时间戳
	 * @return
	 */
	public static String timeFormattingForDate(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		time = sdf.format(new Date(Long.parseLong(time) * 1000));
		return time;
	}

	public static String timeFormattingForDate2(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		time = sdf.format(new Date(Long.parseLong(time) * 1000));
		return time;
	}

	public static String timeFormattingForDate3(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		time = sdf.format(new Date(Long.parseLong(time) * 1000));
		return time;
	}

	/**
	 * 返回"yyyy-MM-dd HH:mm"时间
	 * 
	 * @param time
	 * @return
	 */
	public static String timeFormattingForDate4(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(time * 1000);
	}

	public static String timeFormattingForDateByMilSec(long time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(time);
	}

	/**
	 * 返回"MM-dd HH:mm"时间
	 * 
	 * @param time
	 * @return
	 */
	public static String timeFormattingForDate5(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
		return sdf.format(time * 1000);
	}

	public static String timeFormattingForDate6(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(time);
	}

	/**
	 * 将时间差转换为天或小时
	 * 
	 * @param now
     * @param startTime
	 * @return
	 */
	public static String timeFormatToDay(long startTime, long now) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate;
		Date nowDate;
		try {
			startDate = format.parse(timeFormattingForDate6(startTime));
			nowDate = format.parse(timeFormattingForDate6(now));
			int hours = (int) ((startDate.getTime() - nowDate.getTime()) / 1000 / 60 / 60);
			if (hours == 24) {

				return "明天开始";
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return "";

		}
		int n = (int) ((startTime - now) / 1000 / 60 / 60);
		if (n >= 24) {

			return n / 24 + "天后开始";
		} else {
			if (n >= 1) {
				return n + "小时后开始";
			}
			return "";
		}
	}

	public static String parseStringToTime(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (date == null) {
			return "";
		}
		return date.getTime() / 1000 + "";
	}

	// private int daysOfMonth = 0; //某月的天数
	// private int dayOfWeek = 0; //具体某一天是星期几
	// 判断是否为闰年
	public static boolean isLeapYear(int year) {
		if (year % 100 == 0 && year % 400 == 0) {
			return true;
		} else if (year % 100 != 0 && year % 4 == 0) {
			return true;
		}
		return false;
	}

	// 得到某月有多少天数
	public static int getDaysOfMonth(int daysOfMonth, boolean isLeapyear,
			int month) {
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			daysOfMonth = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			daysOfMonth = 30;
			break;
		case 2:
			if (isLeapyear) {
				daysOfMonth = 29;
			} else {
				daysOfMonth = 28;
			}

		}
		return daysOfMonth;
	}

	// 指定某年中的某月的第一天是星期几
	public static int getWeekdayOfMonth(int dayOfWeek, int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return dayOfWeek;
	}

	/**
	 * 评论时间
	 * 
	 * @param datetime
	 * @return
	 */
	public static String getCommentDate(String datetime) {
		Calendar calendar = Calendar.getInstance();
		// SimpleDateFormat format = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String str = format.format(datetime);
		int year = Integer.parseInt(datetime.substring(0, 4));
		int month = Integer.parseInt(datetime.substring(5, 7));
		int date = Integer.parseInt(datetime.substring(8, 10));
		int hour = Integer.parseInt(datetime.substring(11, 13));
		int minute = Integer.parseInt(datetime.substring(14, 16));
		// int second = Integer.parseInt(datetime.substring(17,19));
		if (calendar.get(Calendar.YEAR) > year) {
			int y = calendar.get(Calendar.YEAR) - year;
			return y + "年前";
		} else if ((calendar.get(Calendar.MONTH) + 1) > month) {
			int m = (calendar.get(Calendar.MONTH) + 1) - month;
			return m + "个月前";
		} else if (calendar.get(Calendar.DAY_OF_MONTH) > date) {
			int d = calendar.get(Calendar.DAY_OF_MONTH) - date;
			return d + "天前";
		} else if (calendar.get(Calendar.HOUR_OF_DAY) > hour) {
			int h = calendar.get(Calendar.HOUR_OF_DAY) - hour;
			return h + "小时前";
		} else if (calendar.get(Calendar.MINUTE) > minute) {
			int s = calendar.get(Calendar.MINUTE) - minute;
			return s + "分钟前";
		} else {
			return "刚刚";
		}
	}

	// 把字符串转为日期
	public static Date ConverToDate(String strDate) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.parse(strDate);
	}

	// 把字符串转为日期

	/**
	 *
	 * @param strDate yyyy-MM-dd HH:mm
	 * @return
	 * @throws Exception
     */
	public static Date ConverToDateToMinute(String strDate){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取两个时间日期 截取拼接 2015-03-18 12:13
	@SuppressWarnings("deprecation")
	public static String getShiYongData(String startDate, String endDate) {
		String str = "";
		String startYear = startDate.substring(0, 4);
		String startMonth = startDate.substring(5, 7);
		String startDay = startDate.substring(8, 10);
		String endYear = endDate.substring(0, 4);
		String endMonth = endDate.substring(5, 7);
		String endDay = endDate.substring(8, 10);
		str = startYear + "." + startMonth + "." + startDay + "-" + endYear
				+ "." + endMonth + "." + endDay;
		return str;
	}

	// 获取两个时间日期 同一天显示月日 时间 不同天显示 月日
	@SuppressWarnings("deprecation")
	public static String getShouYeShiYongData(String startDate, String endDate) {
		if(StringUtil.isEmpty(startDate) || StringUtil.isEmpty(endDate)){
			return "";
		}
		String str = "";
		String startYear = startDate.substring(0, 4);
		int startMonth = Integer.parseInt(startDate.substring(5, 7));
		int startDay = Integer.parseInt(startDate.substring(8, 10));
		String endYear = endDate.substring(0, 4);
		int endMonth = Integer.parseInt(endDate.substring(5, 7));
		int endDay = Integer.parseInt(endDate.substring(8, 10));
		String startTime = startDate.trim().substring(10, 16);
		String endTime = endDate.trim().substring(10, 16);
		if (startDay == endDay) {
//			str = startMonth + "." + startDay + "." + " " + startTime + " -"
//					+ endTime;
			str = startYear + "." + startMonth + "." + startDay;
			return str;
		} else {
			str = startYear + "." + startMonth + "." + startDay + "-" + endYear + "." + endMonth + "."
					+ endDay;
			return str;
		}
	}

	public static String getSinaShareTime(String startDate, String endDate) {
		if(StringUtil.isEmpty(startDate) || StringUtil.isEmpty(endDate)){
			return "";
		}
		String str = "";
		String startYear = startDate.substring(0, 4);
		int startMonth = Integer.parseInt(startDate.substring(5, 7));
		int startDay = Integer.parseInt(startDate.substring(8, 10));
		String endYear = endDate.substring(0, 4);
		int endMonth = Integer.parseInt(endDate.substring(5, 7));
		int endDay = Integer.parseInt(endDate.substring(8, 10));
		String startTime = startDate.trim().substring(10, 16);
		String endTime = endDate.trim().substring(10, 16);
		if (startDay == endDay) {
//			str = startMonth + "." + startDay + "." + " " + startTime + " -"
//					+ endTime;
			str = startMonth + "月" + startDay + "日" + startTime + "-" + endTime;
			return str;
		} else {
			str = startMonth + "月" + startDay + "日" + startTime + "-" + endMonth + "月"
					+ endDay + "日" + endTime;
			return str;
		}
	}

	/**
	 * 判断是否为同一天
	 * @param dataStrOne
	 * @param dataStrTwo
	 * @return
	 */
	public static boolean isSameDay(String dataStrOne,String dataStrTwo){
		if(StringUtil.isEmpty(dataStrOne) || StringUtil.isEmpty(dataStrTwo)){
			return false;
		}
		return dataStrOne.substring(0, dataStrOne.indexOf(" ")).equals(dataStrTwo.substring(0, dataStrTwo.indexOf(" ")));
	}


	/**
	 * 获取时间戳
	 * @return 20180521172011
	 */
	public static String getTimeStamp(){
		SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			return sim.format(new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取时间戳 毫秒级
	 * @return
     */
	public static String getTimeStampMilli(){
		SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmssSS");
		try {
			return sim.format(new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}

	public static String getTimeStampSmallMilli(){
		SimpleDateFormat sim = new SimpleDateFormat("mmssSSS");
		try {
			return sim.format(new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 给出时间和当前时间是否是同一天
	 * @param c
	 * @param time
     * @return
     */
	public static boolean isSameTime(Calendar c,String time){
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		if(year == Integer.valueOf(getYear(time)) && month == Integer.valueOf(getMonth(time)) && day == Integer.valueOf(getDayWithHour(time))){
//		if (getYear(time).equals(Integer.toString(year)) && getMonth(time).equals(Integer.toString(month)) && getDay(time).equals(Integer.toString(day))) {
			return true;
		}
		return false;
	}

	/**
	 * 当前时间是否在给出的起始和终止时间范围内
	 * @param c
	 * @param startTime
	 * @param endTime
     * @return
     */
	public static boolean isTimeScope(Calendar c,String startTime,String endTime){
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int minuteOfDay = hour * 60 + minute;
		if(isSameTime(c,startTime)){
			final int start = Integer.parseInt(getHour(startTime)) * 60 + Integer.parseInt(getMintWithSecond(startTime));
			final int end = Integer.parseInt(getHour(endTime)) * 60 + Integer.parseInt(getMintWithSecond(endTime));
			if (minuteOfDay >= start && minuteOfDay <= end) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 判断给出时间是否是明天
	 * @param c
	 * @param time
     * @return
     */
	public static boolean isTomorrow(Calendar c, String time) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		if (year == Integer.valueOf(getYear(time)) && month == Integer.valueOf(getMonth(time))) {
			c.add(c.DATE, 1);
			if (Integer.valueOf(getDayWithHour(time)) == c.get(Calendar.DAY_OF_MONTH)) {
				return true;
			}
		}
		return false;
	}

    public static String getNormalDateByUTC(String utcTime){
        return utc2Local(utcTime,"yyyy-MM-dd'T'HH:mm:ss.SSS Z", "yyyy-MM-dd HH:mm:ss");
    }

	public static String utc2Local(String utcTime, String utcTimePatten,
								   String localTimePatten) {
		utcTime = utcTime.replace("Z", " UTC");
		SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
		utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));// 时区定义并进行时间获取
		Date gpsUTCDate = null;
		try {
			gpsUTCDate = utcFormater.parse(utcTime);
		} catch (ParseException e) {
			e.printStackTrace();
//			LogUtil.e("DateUtil", com.zero.zerolib.util.BaseUtil.getExceptionStr(e));
		}
		SimpleDateFormat localFormater = new SimpleDateFormat(localTimePatten);
		localFormater.setTimeZone(TimeZone.getDefault());
		String localTime = "";
		if(null != gpsUTCDate){
			localTime = localFormater.format(gpsUTCDate.getTime());
		}
		return localTime;
	}

	public static Date getUtcTime(String utcTime){
    	if(StringUtil.isEmpty(utcTime)){
    		return null;
		}
        utcTime = utcTime.replace("Z", " UTC");
        SimpleDateFormat utcFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));// 时区定义并进行时间获取
        Date gpsUTCDate = null;
        try {
            gpsUTCDate = utcFormater.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
//			LogUtil.e("DateUtil", com.zero.zerolib.util.BaseUtil.getExceptionStr(e));
        }
        return gpsUTCDate;
    }

	public static String getFormatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		return dateString;
	}
}

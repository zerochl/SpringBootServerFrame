/**
 * 
 */
package com.zero.barrageserver.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author ChenHongLi
 *
 */
public class StringUtil {
	public static boolean isEmpty(String str) {
		if (null == str || "".equals(str)) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(CharSequence str) {
		if (null == str || "".equals(str)) {
			return true;
		}
		return false;
	}

	public static boolean isStartWith(String str, String startStr) {
		if (isEmpty(startStr) || isEmpty(str)) {
			return false;
		}
		if (str.startsWith(startStr)) {
			return true;
		}
		return false;
	}

	/**
	 * 检查是否是数字
	 * 
	 * @param value
	 * @return
	 * @author ChenHongLi 2015-1-21
	 */
	public static boolean checkNumber(double value) {
		String str = String.valueOf(value);
		String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
		return str.matches(regex);
	}

	/**
	 * 检查是否是数字
	 * 
	 * @param value
	 * @return
	 * @author ChenHongLi 2015-1-21
	 */
	public static boolean checkNumber(int value) {
		String str = String.valueOf(value);
		String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
		return str.matches(regex);
	}

	/**
	 * 检查是否是数字
	 * 
	 * @param value
	 * @return
	 * @author ChenHongLi 2015-1-21
	 */
	public static boolean checkNumber(String value) {
		String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
		return value.matches(regex);
	}
	/**
	 * 获取UTF-8编码
	 * @param str
	 * @return
	 */
	public static String getUTF8XMLString(String str) {
		// A StringBuffer Object
		StringBuffer sb = new StringBuffer();
		sb.append(str);
		String xmString = "";
		String xmlUTF8 = "";
		try {
			xmString = new String(sb.toString().getBytes("UTF-8"));
			xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// return to String Formed
		return xmlUTF8;
	}
	
	public static String getByReplace(String str){
		return str.replace(" ", "+");
	}
	
	/**
	 * 获取GBK编码
	 * @param str
	 * @return
	 */
	public static String getGBKString(String str) {
		// A StringBuffer Object
		StringBuffer sb = new StringBuffer();
		sb.append(str);
		String xmString = "";
		String xmlUTF8 = "";
		try {
			xmString = new String(sb.toString().getBytes("GBK"));
			xmlUTF8 = URLEncoder.encode(xmString, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// return to String Formed
		return xmlUTF8;
	}

}

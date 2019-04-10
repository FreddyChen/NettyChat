package com.freddy.chat.utils;

import android.telephony.PhoneNumberUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	private final static Pattern phonePattern = Pattern
			.compile("^1\\d{10}$");

	/**
	 * 去除特殊字符或将所有中文标号替换为英文标号
	 * 
	 * @param str
	 * @return
	 */
	public static String stringFilter(String str) {
		str = str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
		String regEx = "[『』]"; // 清除掉特殊字符
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * 半角转换为全角
	 * 
	 * @param input
	 * @return
	 */
	public static String toDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * string to int
	 * 
	 * @param input
	 * @return
	 */
	public static int stringtoint(String input) {
		try {
			return Integer.parseInt(input);
		} catch (Exception e) {
			return -1;
		}

	}

	/**
	 * string to long
	 * 
	 * @param input
	 * @return
	 */
	public static long stringtolong(String input) {
		try {
			return Long.parseLong(input);
		} catch (Exception e) {
			return 0l;
		}

	}

	/**
	 * 判断内容是否为空
	 * 
	 * @date 2013-10-24下午4:20:03
	 * @author hx
	 * @param o
	 * @return
	 */
	public static boolean isEmpty(Object o) {
		return (null == o || o.toString().trim().equals("")) ? true : false;
	}

	/**
	 * 字符长度
	 * 
	 * @date 2013-10-24下午4:20:03
	 * @author hx
	 * @param o
	 * @return
	 */
	public static int getLength(Object o) {
		if (null == o || o.toString().trim().equals("")) {
			return 0;
		} else {
			return o.toString().trim().length();
		}

	}

	/**
	 * object to int
	 * 
	 * @date 2014-1-3下午2:14:39
	 * @author hx
	 * @param o
	 * @return
	 */
	public static int ObjectToInt(Object o) {
		if (null == o || o.toString().trim().equals("")) {
			return -1;
		} else {
			return Integer.parseInt(o.toString());
		}

	}
	/**
	 * 去除非法字符(换行、回车...)
	 * @author liu_haifang
	 * @date 2014-11-7 下午2:36:48
	 * @param str
	 * @return
	 */
	public static String rmUnqualified (String str){
		if (!isEmpty(str)) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			return m.replaceAll("");
		}
		return null;
	}

	public static boolean isNotEmpty(Object o) {
		return !(null == o || o.toString().trim().equals("")) ;
	}

	/**
	 * 比较两个字符串（大小写敏感）。
	 *
	 * <pre>
	 *
	 *    StringUtil.equals(null, null)   = true
	 *    StringUtil.equals(null, &quot;abc&quot;)  = false
	 *    StringUtil.equals(&quot;abc&quot;, null)  = false
	 *    StringUtil.equals(&quot;abc&quot;, &quot;abc&quot;) = true
	 *    StringUtil.equals(&quot;abc&quot;, &quot;ABC&quot;) = false
	 *
	 * </pre>
	 *
	 * @param str1
	 *                要比较的字符串1
	 * @param str2
	 *                要比较的字符串2
	 *
	 * @return 如果两个字符串相同，或者都是 <code>null</code> ，则返回 <code>true</code>
	 */
	public static boolean equals(String str1, String str2)
	{
		if ( str1 == null )
		{
			return str2 == null;
		}

		return str1.equals(str2);
	}

	/**
	 * 利用正则表达式判断字符串是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false;
		}
		return true;
	}

	/**
	 * 获取较纯净的手机号码<br />
	 * 删除前缀、空格等
	 *
	 * @param phone
	 * @return
	 */
	public static String getValidPhoneNumber(String phone) {
		if (phone == null)
			return "";
		if (phone.startsWith("0086")) {
			phone = phone.substring(4);
		}
		if (phone.startsWith("+86")) {
			phone = phone.substring(3);
		}
		PhoneNumberUtils.stripSeparators(phone);
		phone = phone.replace("-", "").replace(" ", "").trim();
		return phone;
	}

	/**
	 * 判断是不是一个合法的手机号码
	 */
	public static boolean isPhone(CharSequence phoneNum) {
		if (isEmpty(phoneNum))
			return false;
		return phonePattern.matcher(phoneNum).matches();
	}
}

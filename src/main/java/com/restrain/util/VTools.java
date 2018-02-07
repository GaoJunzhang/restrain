/**
 * 
 */
package com.restrain.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Administrator
 *
 */
public class VTools {

	public static String getNewUUID(){
		
		String str = UUID.randomUUID().toString().replace("-", "");
	    return str;
	}
	public static boolean StringIsNullOrSpace(String paramString)
	  {
	    return (paramString == null) || ("".equals(paramString.trim())) || ("NULL".equals(paramString.trim().toUpperCase())) || ("<无>".equals(paramString.trim()));
	  }
	public static String getCurrencyTime(String type){
		SimpleDateFormat df = new SimpleDateFormat(type);
		return df.format(new Date());
	}

	public static String getCurrentDate(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
	public static String getWordCount(String str,int len) {
		try {
			String symbol = "...";
			int counterOfDoubleByte = 0;
			byte b[] = str.getBytes("GBK");
			if (b.length <= len)
				return str;
			for (int i = 0; i < len; i++) {
				if (b[i] < 0)
					counterOfDoubleByte++;
			}
			if (counterOfDoubleByte % 2 == 0)
				return new String(b, 0, len, "GBK") + symbol;
			else
				return new String(b, 0, len - 1, "GBK") + symbol;

		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}

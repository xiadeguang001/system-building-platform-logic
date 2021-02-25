package com.haso.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.google.common.base.Strings;

public class ExceptionUtil {

	public static String getCauseString(Throwable e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
	
	public static String getCauseStringWithLimit(Throwable e, Integer limit) {
		String message = getCauseString(e);
		if (!Strings.isNullOrEmpty(message) && message.length() > limit) {
			message = message.substring(0, limit);
		}
		return message;
	}
}

package com.haso.common.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.sift.SiftingAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.FileAppender;

public class LogBackUtil {
	
	private static final String LOGGER_ROOT = "ROOT";
	
	private static final String SIFTING_APPENDER_NAME = "SIFT";
	
	public static final String FILE_PATH = "filePath";
	
	public static final String FILE_NAME = "fileName";
	
	public static Map<String, String> getSiftingFilePath(String key) {
		Map<String, String> pathMap = new HashMap<String, String>();
		String path = null;
		LoggerContext context = (LoggerContext)LoggerFactory.getILoggerFactory();
		ch.qos.logback.classic.Logger uaLogger = context.getLogger(LOGGER_ROOT);
		SiftingAppender siftingAppender = (SiftingAppender)uaLogger.getAppender(SIFTING_APPENDER_NAME);
		Appender<ILoggingEvent> appender = siftingAppender.getAppenderTracker().find(key);
		if(appender instanceof FileAppender){
            FileAppender fileAppender = (FileAppender) appender;
            String defineFilePath = fileAppender.getFile();
            File file = new File(defineFilePath);
            try {
				path = file.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
            
            pathMap.put(FILE_PATH, path);
            pathMap.put(FILE_NAME, getFileName(defineFilePath));
        }
		return pathMap;
	}
	
	private static String getFileName(String filePath) {
		if (Strings.isEmpty(filePath)) return null;
		String[] split = filePath.split("/");
		return split[split.length - 1];
	}
}

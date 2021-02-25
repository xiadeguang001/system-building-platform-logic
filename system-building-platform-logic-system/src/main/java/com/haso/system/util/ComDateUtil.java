/**
 * Copyright 2018 Goldwind Science & Technology.
 * All rights reserved. GOLDWIND PROPRIETARY/CONFIDENTIAL.
 * Use is subject to license terms.
 */
package com.haso.system.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期工具类, 继承org.apache.commons3.lang.time.DateUtils类
 * 
 * @author 发创软件
 *
 */
@Component
public class ComDateUtil {

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+9"));
    }

    /**
     * 今現在の日付を取得　フォーマット（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 日付を取得
     * 
     * @param pattern
     *            pattern："yyyy-MM-dd" "HH:mm:ss" "E"
     * @return
     */
    public static String getDate(String pattern) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.JAPANESE);
        return DateFormatUtils.format(calendar, pattern);
    }

    /**
     * 今現在の日付を取得　フォーマット（HH:mm:ss）
     */
    public static String getTime() {
        
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.JAPANESE);
        return DateFormatUtils.format(calendar, "HH:mm:ss");
    }

    /**
     * 今現在の日時を取得　フォーマット（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.JAPANESE);
        return DateFormatUtils.format(calendar, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 今現在の日時を取得　フォーマット（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 日付フォーマット処理
     * 
     * @param date
     *            日付
     * @param pattern
     *            フォーマット
     * @return
     */
    public static String formatDateTime(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 日付変換処理
     * 
     * @param str
     *            日付
     * @param parsePatterns
     *            フォーマット
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String str, String parsePatterns) throws ParseException {
        return DateUtils.parseDate(str, parsePatterns);
    }

    /**
     * 指定した年と月の最初の日を取得します
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth1(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        int firstDay = cal.getMinimum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH,firstDay);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        return sdf.format(cal.getTime());
    }
    /**
     * 指定した年と月の最初の日を取得します
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth2(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        int firstDay = cal.getMinimum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH,firstDay);
        SimpleDateFormat sdf = new SimpleDateFormat("M/d");
        return sdf.format(cal.getTime());
    }

    /**
     * 指定した年月の末日を取得
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth1(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        return sdf.format(cal.getTime());
    }
}

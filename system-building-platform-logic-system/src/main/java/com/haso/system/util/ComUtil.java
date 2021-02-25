package com.haso.system.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ComUtil {
    private static Logger logger = LoggerFactory.getLogger(FtpUtil.class);

    private static class LazyHolder {
        private static final ComUtil INSTANCE = new ComUtil();
    }

    private ComUtil() {
    }

    public static final ComUtil getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String trim(String value) {

        StringBuilder sb = new StringBuilder(value);

        for (int i = sb.length() - 1; 0 <= i; i--) {
            char c = sb.charAt(i);
            if (c == ' ' || c == '　')
                sb.deleteCharAt(i);
            else
                break;
        }
        return sb.toString();
    }

    public String dateToString(Date date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public String getNow() {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }

    public String getNowTime() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return df.format(new Date());
    }

    public String getNowTimes() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public String getNowTimeYyyyMMddHHmmss() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(new Date());
    }

    public String getNowDate() {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        return df.format(new Date());
    }

    public String format(String value) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = format.parse(value);
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTime = format.format(date);
        return formattedTime;
    }

    public String getFilePath(String fileDirPath, String fileName) throws Exception {
        String dateNowStr = ComUtil.getInstance().getNow();
        String name = fileDirPath + "/" + dateNowStr + "/" + fileName;
        return name;
    }
//	public static void main(String[] args) {
//		System.out.println(ComUtil.getInstance().trim("あいう 　 　 "));
//	}

    /**
     * @param source
     * @param dest
     * @param bufferSize
     * @param fullPath
     * @throws IOException
     */
    public void copy(String source, String dest, int bufferSize, String fullPath) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(new File(source));

            File file = new File(fullPath);
            if (!file.exists()) {
                file.mkdirs();
            }

            out = new FileOutputStream(new File(dest));

            byte[] buffer = new byte[bufferSize];
            int len;

            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            logger.error(":copy", "error occur while copy", e);
        } finally {
            in.close();
            out.close();
        }
    }

    /**
     * 再帰的に削除
     * ディレクトリとその下のすべてのサブディレクトリとファイルを削除する
     *
     * @param file ファイルまたはディレクトリ
     */
    public boolean delFiles(File file) {
        boolean result = false;
        if (file.isDirectory()) {
            File[] childrenFiles = file.listFiles();
            for (File childFile : childrenFiles) {
                result = delFiles(childFile);
                if (!result) {
                    return result;
                }
            }
        }
        result = file.delete();
        return result;
    }

}

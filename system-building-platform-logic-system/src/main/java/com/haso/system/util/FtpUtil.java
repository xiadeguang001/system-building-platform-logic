package com.haso.system.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//import com.haso.sfk.service.LogEventLogic;

@Component
public class FtpUtil {
    /** ロガー */
    private static Logger logger = LoggerFactory.getLogger(FtpUtil.class);

//    @Autowired
//    private LogEventLogic logEventLogic;

    /** URL */
//    @Value("${def.application.ftpurl}")
    private String URL;
    /** PORT */
//    @Value("${def.application.ftpport}")
    private int PORT;
    /** USERNAME */
//    @Value("${def.application.ftpusername}")
    private String USERNAME;
    /** PASSWORD */
//    @Value("${def.application.ftppassword}")
    private String PASSWORD;
    /** URLSOKO */
//    @Value("${def.application.ftpurlsoko}")
    private String URLSOKO;
    /** PORTSOKO */
//    @Value("${def.application.ftpportsoko}")
    private int PORTSOKO;
    /** USERNAMESOKO */
//    @Value("${def.application.ftpusernamesoko}")
    private String USERNAMESOKO;
    /** PASSWORDSOKO */
//    @Value("${def.application.ftppasswordsoko}")
    private String PASSWORDSOKO;
    /** REMOTE_PATH */
//    @Value("${def.application.ftpremotepath}")
    private String REMOTE_PATH;
    /** LOCAL_PATH */
//    @Value("${def.application.ftplocalpath}")
    private String LOCAL_PATH;
    /** UPLOAD_PATH */
//    @Value("${def.application.ftpuploadpath}")
    private String UPLOAD_PATH;

    private FtpUtil() {
    }

    /**
     * FTP Download
     * @param remotePath
     * @param fileName
     * @return
     */
    public String downloadFile(int jobId, String remoteSubPath, String fileName) {
        String fileDirPath = null;
        String name = null;
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("Shift_JIS");
        try {
            int reply;

            if (PORT > -1) {
                ftp.connect(URL, PORT);
            } else {
                ftp.connect(URL);
            }
            ftp.login(USERNAME, PASSWORD);
            reply = ftp.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            } else {
                ftp.changeWorkingDirectory(REMOTE_PATH + remoteSubPath);
                ftp.enterLocalPassiveMode();
                FTPFile[] fs = ftp.listFiles();
                for (FTPFile ff : fs) {
                    if (ff.getName().equals(fileName)) {
                        InputStream in = ftp.retrieveFileStream(fileName);
                        String dateNowStr = ComUtil.getInstance().getNow();
                        fileDirPath = LOCAL_PATH + "/" + dateNowStr;
                        File fileDir = new File(fileDirPath);
                        if(!fileDir .exists() && !fileDir .isDirectory()){
                            fileDir .mkdir();
                        }
                        name = fileDirPath + "/" + fileName;
                        Path pathToFile = Paths.get(name);
                        Files.createDirectories(pathToFile.getParent());
                        OutputStream out = new FileOutputStream(new File(name));
                        IOUtils.copy(in, out);
                        in.close();
                        out.close();
                        break;
                    }
                }
            }
            if (name != null) {
                logger.info("[FTP downloadFile] :" + fileName + " 成功。");
            } else {
                logger.info("[FTP downloadFile] :" + fileName + " 失败。(指定されたファイルが見つかりません)");
            }
        } catch (Exception e) {
//            logEventLogic.insertLog(jobId, "【　FTP downloadFile　】 エラー発生 " + e.getLocalizedMessage(), "ERROR");
            logger.error(e.getLocalizedMessage(), e);
            logger.info("終了 [FTP downloadFile] 失败");
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.logout();
                    ftp.disconnect();
                } catch (IOException e) {
//                    logEventLogic.insertLog(jobId, "【　FTP downloadFile　】 エラー発生 " + e.getLocalizedMessage(), "ERROR");
                    logger.error(e.getLocalizedMessage(), e);
                    logger.info("終了 [FTP downloadFile] 失败");
                }
            }
        }
        return name;
    }

    public String downloadFileSoko(int jobId, String remoteSubPath, String fileName) {
        String fileDirPath = null;
        String name = null;
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("Shift_JIS");
        try {
            int reply;

            if (PORTSOKO > -1) {
                ftp.connect(URLSOKO, PORTSOKO);
            } else {
                ftp.connect(URLSOKO);
            }
            ftp.login(USERNAMESOKO, PASSWORDSOKO);
            reply = ftp.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            } else {
                ftp.changeWorkingDirectory(REMOTE_PATH + remoteSubPath);
                ftp.enterLocalPassiveMode();
                FTPFile[] fs = ftp.listFiles();
                for (FTPFile ff : fs) {
                    if (ff.getName().equals(fileName)) {
                        InputStream in = ftp.retrieveFileStream(fileName);
                        String dateNowStr = ComUtil.getInstance().getNow();
                        fileDirPath = LOCAL_PATH + "/" + dateNowStr;
                        File fileDir = new File(fileDirPath);
                        if(!fileDir .exists() && !fileDir .isDirectory()){
                            fileDir .mkdir();
                        }
                        name = fileDirPath + "/" + fileName;
                        Path pathToFile = Paths.get(name);
                        Files.createDirectories(pathToFile.getParent());
                        OutputStream out = new FileOutputStream(new File(name));
                        IOUtils.copy(in, out);
                        in.close();
                        out.close();
                        break;
                    }
                }
            }
            if (name != null) {
                logger.info("[FTP downloadFile] :" + fileName + " 成功。");
            } else {
                logger.info("[FTP downloadFile] :" + fileName + " 失败。(指定されたファイルが見つかりません)");
            }
        } catch (Exception e) {
//            logEventLogic.insertLog(jobId, "【　FTP downloadFile　】 エラー発生 " + e.getLocalizedMessage(), "ERROR");
            logger.error(e.getLocalizedMessage(), e);
            logger.info("終了 [FTP downloadFile] 失败");
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.logout();
                    ftp.disconnect();
                } catch (IOException e) {
//                    logEventLogic.insertLog(jobId, "【　FTP downloadFile　】 エラー発生 " + e.getLocalizedMessage(), "ERROR");
                    logger.error(e.getLocalizedMessage(), e);
                    logger.info("終了 [FTP downloadFile] 失败");
                }
            }
        }
        return name;
    }

    /**
     * FTP upload file
     * @param localFileFullName upload file full path name
     * @param fileName upload ftp file name
     * @throws Exception
     */
    public void uploadFTPFile(int jobId, String localFileFullName, String fileName) throws Exception {
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("Shift_JIS");
        try {
            int reply;

            if (PORT > -1) {
                ftp.connect(URL, PORT);
            } else {
                ftp.connect(URL);
            }

            ftp.login(USERNAME, PASSWORD);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            } else {
                InputStream input = new FileInputStream(new File(localFileFullName));
                ftp.storeFile(UPLOAD_PATH + "/" + fileName, input);
            }
            logger.info("終了 [FTP uploadFTPFile]");
        } catch (Exception e) {
//            logEventLogic.insertLog(jobId, "【　FTP downloadFile　】 エラー発生 " + e.getLocalizedMessage(), "ERROR");
            logger.error(e.getLocalizedMessage(), e);
            logger.info("終了 [FTP uploadFTPFile] 失败");
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.logout();
                    ftp.disconnect();
                } catch (IOException e) {
//                    logEventLogic.insertLog(jobId, "【　FTP downloadFile　】 エラー発生 " + e.getLocalizedMessage(), "ERROR");
                    logger.error(e.getLocalizedMessage(), e);
                    logger.info("終了 [FTP uploadFTPFile] 失败");
                }
            }
        }
    }

    /**
     * FTP upload file
     * @param fileByte csvファイル
     * @param fileName upload ftp file name
     * @throws Exception
     */
    public void uploadFTPFile(int jobId, byte[] fileByte, String fileName) throws Exception {
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("UTF-8");
        try {
            int reply;

            if (PORT > -1) {
                ftp.connect(URL, PORT);
            } else {
                ftp.connect(URL);
            }

            ftp.login(USERNAME, PASSWORD);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            } else {
                InputStream input = new ByteArrayInputStream(fileByte);
                ftp.storeFile(UPLOAD_PATH + "/" + fileName, input);
            }
            logger.info("終了 [FTP uploadFTPFile]");
        } catch (Exception e) {
//            logEventLogic.insertLog(jobId, "【　FTP downloadFile　】 エラー発生 " + e.getLocalizedMessage(), "ERROR");
            logger.error(e.getLocalizedMessage(), e);
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.logout();
                    ftp.disconnect();
                } catch (IOException e) {
//                    logEventLogic.insertLog(jobId, "【　FTP downloadFile　】 エラー発生 " + e.getLocalizedMessage(), "ERROR");
                    logger.error(e.getLocalizedMessage(), e);
                }
            }
        }
    }

    /**
     * FTP upload file to soko
     * @param fileByte csvファイル
     * @param fileName upload ftp file name
     * @throws Exception
     */
    public void uploadFTPFileSoko(int jobId, byte[] fileByte, String fileName) throws Exception {
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("UTF-8");
        try {
            int reply;

            if (PORTSOKO > -1) {
                ftp.connect(URLSOKO, PORTSOKO);
            } else {
                ftp.connect(URLSOKO);
            }

            ftp.login(USERNAMESOKO, PASSWORDSOKO);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            } else {
                InputStream input = new ByteArrayInputStream(fileByte);
                ftp.storeFile(UPLOAD_PATH + "/" + fileName, input);
            }
            logger.info("終了 [FTP uploadFTPFile]");
        } catch (Exception e) {
//            logEventLogic.insertLog(jobId, "【　FTP uploadFTPFile　】 エラー発生 " + e.getLocalizedMessage(), "ERROR");
            logger.error(e.getLocalizedMessage(), e);
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.logout();
                    ftp.disconnect();
                } catch (IOException e) {
//                    logEventLogic.insertLog(jobId, "【　FTP uploadFTPFile　】 エラー発生 " + e.getLocalizedMessage(), "ERROR");
                    logger.error(e.getLocalizedMessage(), e);
                }
            }
        }
    }

    /**
     * ftp file delete
     * @param jobId
     * @param fileName
     * @param deletFlag
     */
    public void deleteFTPFile(int jobId, String fileName, String deleteFlag) {
        if (deleteFlag.equals("1")) {
            FTPClient ftpClient = new FTPClient();
            try {
                ftpClient.connect(URL, PORT);

                int replyCode = ftpClient.getReplyCode();
                if (!FTPReply.isPositiveCompletion(replyCode)) {
                    ftpClient.disconnect();
                    return;
                }
                ftpClient.login(USERNAME, PASSWORD);
                ftpClient.deleteFile(REMOTE_PATH + "/" + fileName);

            } catch (Exception e) {
//                logEventLogic.insertLog(jobId, "【　FTP deleteFTPFile　】 エラー発生 " + e.getLocalizedMessage(), "ERROR");
                logger.error(e.getLocalizedMessage(), e);
            } finally {
                try {
                    if (ftpClient.isConnected()) {
                        ftpClient.logout();
                        ftpClient.disconnect();
                    }
                } catch (IOException e) {
//                    logEventLogic.insertLog(jobId, "【　FTP deleteFTPFile　】 エラー発生 " + e.getLocalizedMessage(), "ERROR");
                    logger.error(e.getLocalizedMessage(), e);
                }
            }
        }
    }

    /**
     * ftp file delete
     * @param jobId
     * @param fileName
     * @param deletFlag
     */
    public void deleteFTPFileSoko(int jobId, String fileName, String deleteFlag) {
        if (deleteFlag.equals("1")) {
            FTPClient ftpClient = new FTPClient();
            try {
                ftpClient.connect(URLSOKO, PORTSOKO);

                int replyCode = ftpClient.getReplyCode();
                if (!FTPReply.isPositiveCompletion(replyCode)) {
                    ftpClient.disconnect();
                    return;
                }
                ftpClient.login(USERNAMESOKO, PASSWORDSOKO);
                ftpClient.deleteFile(REMOTE_PATH + "/" + fileName);

            } catch (Exception e) {
//                logEventLogic.insertLog(jobId, "【　FTP deleteFTPFile　】 エラー発生 " + e.getLocalizedMessage(), "ERROR");
                logger.error(e.getLocalizedMessage(), e);
            } finally {
                try {
                    if (ftpClient.isConnected()) {
                        ftpClient.logout();
                        ftpClient.disconnect();
                    }
                } catch (IOException e) {
//                    logEventLogic.insertLog(jobId, "【　FTP deleteFTPFile　】 エラー発生 " + e.getLocalizedMessage(), "ERROR");
                    logger.error(e.getLocalizedMessage(), e);
                }
            }
        }
    }
}


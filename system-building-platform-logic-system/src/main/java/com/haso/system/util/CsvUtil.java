package com.haso.system.util;

import org.apache.commons.csv.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CsvUtil {

    /** ロガー */
    private static Logger logger = LoggerFactory.getLogger(CsvUtil.class);

    // Delimiter used in CSV file
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String NEW_LINE_SEPARATOR_WINDOWS = "\r\n";

    // private static final String FILE_PATH =
    // "/Users/hasomac/Documents/workspace2/CSV/";
    /** LOCAL_PATH */
//    @Value("${def.application.ftplocalpath}")
    private String LOCAL_PATH;
    private CsvUtil() {
    }

    /**
     * writeCsvFile
     *
     * @param fileName
     * @param fileHeader
     * @param list
     * @return
     */
    public byte[] writeCsvFile(String fileName, Object[] fileHeader,
                               List<Map<String, Object>> list) throws Exception {
        //FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        byte[] binary = null;
        // Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withEscape('\\')
                .withQuoteMode(QuoteMode.NONE)
                .withRecordSeparator(NEW_LINE_SEPARATOR);
        // CSVFormat csvFileFormat =
        // CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        // fileName = "d://" + fileName;
        File file = new File(fileName + ".csv");

        file.deleteOnExit();
        // initialize FileWriter object
        //fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        // initialize CSVPrinter object
        csvFilePrinter = new CSVPrinter(writer, csvFileFormat);
        // Create CSV file header
        //csvFilePrinter.printRecord(fileHeader);

        // Write a new object list to the CSV file
        for (Map<String, Object> map : list) {
            List<Object> dataPrint = new ArrayList<Object>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                dataPrint.add(value);
            }
            csvFilePrinter.printRecord(dataPrint);
        }
        writer.flush();
        writer.close();
        csvFilePrinter.close();

        // Write to temp file
        Path path = Paths.get(fileName + ".csv");
        binary = Files.readAllBytes(path);
        /*
         * FileOutputStream os = new
         * FileOutputStream("d:\\"+fileName+".csv"); os.write(binary);
         * os.flush(); os.close();
         */
        return binary;
    }

    /**
     * writeCsvFile
     *
     * @param fileName
     * @param fileHeader
     * @param list
     * @return
     */
    public byte[] writeCsvFileNeedHeader(String fileName, Object[] fileHeader,
                               List<Map<String, Object>> list) throws Exception {
        //FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        byte[] binary = null;
        // Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withEscape('\\')
                .withQuoteMode(QuoteMode.NONE)
                .withRecordSeparator(NEW_LINE_SEPARATOR);
        // CSVFormat csvFileFormat =
        // CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        // fileName = "d://" + fileName;
        File file = new File(fileName + ".csv");

        file.deleteOnExit();
        // initialize FileWriter object
        //fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        // initialize CSVPrinter object
        csvFilePrinter = new CSVPrinter(writer, csvFileFormat);
        // Create CSV file header
        csvFilePrinter.printRecord(fileHeader);

        // Write a new object list to the CSV file
        for (Map<String, Object> map : list) {
            List<Object> dataPrint = new ArrayList<Object>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                dataPrint.add(value);
            }
            csvFilePrinter.printRecord(dataPrint);
        }
        writer.flush();
        writer.close();
        csvFilePrinter.close();

        // Write to temp file
        Path path = Paths.get(fileName + ".csv");
        binary = Files.readAllBytes(path);
        /*
         * FileOutputStream os = new
         * FileOutputStream("d:\\"+fileName+".csv"); os.write(binary);
         * os.flush(); os.close();
         */
        return binary;
    }

    /**
     *
     * @param fileName
     */
    public List<List<Object>> readCsvFile(String fileName) throws Exception {
        CSVParser csvFileParser = null;
        // Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.RFC4180;
        List<List<Object>> list = new ArrayList<List<Object>>();
        // initialize CSVParser object
        csvFileParser = new CSVParser(new InputStreamReader(
                new FileInputStream(fileName), "UTF-8"), csvFileFormat);
        // Get a list of CSV file records
        List<CSVRecord> csvRecords = csvFileParser.getRecords();
        // Read the CSV file records starting from the second record to skip
        // the header
        for (int i = 0; i < csvRecords.size(); i++) {
            CSVRecord record = csvRecords.get(i);
            List<Object> datas = new ArrayList<Object>();
            for (int j = 0; j < record.size(); j++) {
                datas.add(record.get(j));
            }
            list.add(datas);
        }
        csvFileParser.close();
        return list;
    }

    /**
     *
     * @param fileName
     */
    public List<List<Object>> readCsvFile2(String fileName) throws Exception {
        CSVParser csvFileParser = null;
        // Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withEscape('\\')
                .withQuote( Character.valueOf('\''))
                .withRecordSeparator(NEW_LINE_SEPARATOR);
        List<List<Object>> list = new ArrayList<List<Object>>();
        // initialize CSVParser object
        csvFileParser = new CSVParser(new InputStreamReader(
                new FileInputStream(fileName), "Shift_JIS"), csvFileFormat);

        // Get a list of CSV file records
        List<CSVRecord> csvRecords = csvFileParser.getRecords();

        // Read the CSV file records starting from the second record to skip
        // the header
        for (int i = 0; i < csvRecords.size(); i++) {
            CSVRecord record = csvRecords.get(i);
            List<Object> datas = new ArrayList<Object>();
            for (int j = 0; j < record.size(); j++) {
                datas.add(record.get(j));
            }
            list.add(datas);
        }
        csvFileParser.close();
        return list;
    }

    /**
     *
     * @param fileName
     */
    public List<List<Object>> readCsvFileLine_r_n(String fileName) throws Exception {
        CSVParser csvFileParser = null;
        // Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withEscape('\\')
                .withQuote( Character.valueOf('\''))
                .withRecordSeparator(NEW_LINE_SEPARATOR_WINDOWS);
        List<List<Object>> list = new ArrayList<List<Object>>();
        // initialize CSVParser object
        csvFileParser = new CSVParser(new InputStreamReader(
                new FileInputStream(fileName), "UTF-8"), csvFileFormat);

        // Get a list of CSV file records
        List<CSVRecord> csvRecords = csvFileParser.getRecords();

        // Read the CSV file records starting from the second record to skip
        // the header
        for (int i = 0; i < csvRecords.size(); i++) {
            CSVRecord record = csvRecords.get(i);
            List<Object> datas = new ArrayList<Object>();
            for (int j = 0; j < record.size(); j++) {
                datas.add(record.get(j));
            }
            list.add(datas);
        }
        csvFileParser.close();
        return list;
    }

    /**
     *
     * @param fileName
     */
    public List<List<Object>> readCsvFile2(String fileName,String encode) throws Exception {
        CSVParser csvFileParser = null;
        // Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.RFC4180;
        List<List<Object>> list = new ArrayList<List<Object>>();
        // initialize CSVParser object
        csvFileParser = new CSVParser(new InputStreamReader(
                new FileInputStream(fileName), encode), csvFileFormat);

        // Get a list of CSV file records
        List<CSVRecord> csvRecords = csvFileParser.getRecords();

        // Read the CSV file records starting from the second record to skip
        // the header
        for (int i = 0; i < csvRecords.size(); i++) {
            CSVRecord record = csvRecords.get(i);
            List<Object> datas = new ArrayList<Object>();
            for (int j = 0; j < record.size(); j++) {
                datas.add(record.get(j));
            }
            list.add(datas);
        }
        csvFileParser.close();
        return list;
    }

    /**
     * writeCsvFile
     *
     * @param fileName
     * @param fileHeader
     * @param list
     * @return
     */
    public void writeErrorCsvFile(String fileName,List<List<Object>> list) throws Exception {
        CSVPrinter csvFilePrinter = null;
        byte[] binary = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withEscape('\\')
                .withQuoteMode(QuoteMode.NONE)
                .withRecordSeparator(NEW_LINE_SEPARATOR);
        File fileDir = new File(LOCAL_PATH);
        if(!fileDir.exists() && !fileDir.isDirectory()){
            fileDir.mkdir();
        }
        File file = new File(fileName + ".csv");

        file.deleteOnExit();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "Shift_JIS"));
        csvFilePrinter = new CSVPrinter(writer, csvFileFormat);

        // Write a new object list to the CSV file
        for (List<Object> obj : list) {
            List<Object> dataPrint = new ArrayList<Object>();
            dataPrint = obj;
            csvFilePrinter.printRecord(dataPrint);
        }
        writer.flush();
        writer.close();
        csvFilePrinter.close();
        // Write to temp file
        Path path = Paths.get(fileName + ".csv");
        binary = Files.readAllBytes(path);

        FileOutputStream os = new FileOutputStream(LOCAL_PATH + "/"+fileName+".csv");
        os.write(binary);
        os.flush();
        os.close();
    }

    /**
     * split file
     * @param rows
     * @param sourceFilePath
     * @param targetDirectoryPath
     */
    public Integer splitDataToSaveFile(int rows, String sourceFilePath,
                                       String targetDirectoryPath) {
        long start1 = System.currentTimeMillis();
        //System.out.println(start1);
        File sourceFile = new File(sourceFilePath);
        File targetFile = new File(targetDirectoryPath);
        if (!sourceFile.exists() || rows <= 0 || sourceFile.isDirectory()) {
            return 0;
        }
        if (targetFile.exists()) {
            if (!targetFile.isDirectory()) {
                return 0;
            }
        } else {
            targetFile.mkdirs();
        }
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream(sourceFilePath),"Shift_JIS");
            BufferedReader br=new BufferedReader(in);

            BufferedWriter bw = null;
            String str = "";
            String tempData = br.readLine();
            int i = 1, s = 0;
            long start2 = System.currentTimeMillis();
            //System.out.println(start2);
            while (tempData != null) {
                str += tempData + "\r\n";
                if (i % rows == 0) {
                    bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                            targetFile.getAbsolutePath() + "/" +  sourceFile.getName() +"_" + (s+1) +".csv"), "Shift_JIS"),1024);

                    bw.write(str);
                    bw.close();

                    str = "";
                    start2 = System.currentTimeMillis();
                    s += 1;
                }
                i++;
                tempData = br.readLine();
            }
            if ((i - 1) % rows != 0) {

                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                        targetFile.getAbsolutePath() + "/" +  sourceFile.getName() +"_" + (s+1) +".csv"), "Shift_JIS"),1024);
                bw.write(str);
                bw.close();
                br.close();

                s += 1;
            }
            in.close();
            return s;
        } catch (Exception e) {
            
        }
        return 0;
    }


}


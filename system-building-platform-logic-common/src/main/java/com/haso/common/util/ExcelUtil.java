package com.haso.common.util;


import java.awt.Color;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description: Excel读写工具类
 * @Author: hunger.zhu
 * @CreateDate: 2019/4/10 13:21
 */
public class ExcelUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    // 将需要的单元格式样式放到Map集合中,使用时直接从Map中获取，如果在使用时创建，那当数据量很大时严重影响性能
    public static ThreadLocal<Map<String, XSSFCellStyle>> styles = new ThreadLocal<>();

    /**
     * 构造方法私有，禁止用户new对象
     */
    private ExcelUtil() {
        super();
    }

    /**
     * 导出工作簿，将工作簿写响应(response)输出流实现浏览器下载
     *
     * @param response
     * @param workbook
     * @param fileName
     * @throws Exception
     */
    public static void exportExcel(HttpServletResponse response, XSSFWorkbook workbook, String fileName) throws Exception {
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
            response.setContentType("application/msexcel");
            workbook.write(os);
        } finally {
            if (null != workbook) {
                workbook.close();
            }
            if (null != os) {
                os.flush();
                os.close();
            }
        }
    }

    /**
     * 创建工作簿
     *
     * @param sheetNames
     * @param headNames
     * @param titles
     * @param contents
     * @return
     * @throws Exception
     */
    public static XSSFWorkbook createExcel(String[] sheetNames, String[] headNames,
                                           List<String[]> titles, List<List<Object[]>> contents) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        int sheetCount = sheetNames.length;
        XSSFSheet sheet = null;
        String headName = "";
        for (int i = 0; i < sheetCount; i++) {
            sheet = workbook.createSheet(sheetNames[i]);
            headName = (null != headNames && StringUtils.isNotBlank(headNames[i])) ? headNames[i] : sheetNames[i];
            createExcel(workbook, sheet, headName, titles.get(i), contents.get(i));
        }
        return workbook;
    }

    public static XSSFWorkbook createExcel(String sheetName, String headName,
                                           String[] titles, List<Object[]> contents) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);
        createExcel(workbook, sheet, headName, titles, contents);
        return workbook;
    }

    /**
     * 创建工作簿
     *
     * @param workbook 导出工作簿
     * @param sheet    导出工作表
     * @param titles   标题列表
     * @param contents 数据列表
     * @throws Exception
     */
    public static void createExcel(XSSFWorkbook workbook, XSSFSheet sheet, String[] titles, List<Object[]> contents) throws Exception {
        createExcel(workbook, sheet, sheet.getSheetName(), titles, contents);
    }

    /**
     * 导出Excel
     *
     * @param workbook 导出工作簿
     * @param sheet    导出工作表
     * @param headName 表头名
     * @param titles   标题列表
     * @param contents 数据列表
     * @throws Exception
     */
    public static void createExcel(XSSFWorkbook workbook, XSSFSheet sheet, String headName,
                                   String[] titles, List<Object[]> contents) throws Exception {
        try {
            if (null == contents || contents.size() <= 0) {
                return;
            }
            // 创建单元格式样式集合
            styles.set(styleMap(workbook));
            // 创建工作表头
            createSheetHead(workbook, sheet, titles, headName);
            // 填充工作表数据
            createSheetData(sheet, contents, 2);
        } finally {
            if (null != styles.get()) {
                styles.get().clear();
            }
            styles.remove();
        }
    }

    /**
     * 构建sheet表头
     *
     * @param sheet
     * @param heads
     */
    private static void createSheetHead(XSSFWorkbook workbook, XSSFSheet sheet, String[] heads, String headName) {
        sheet.createFreezePane(0, 2, 0, 2);// 冻结前2行
        sheet.setDefaultColumnWidth((short) 20);// 设置表格默认列宽度为20个字节
        XSSFCellStyle tilteStyle = styles.get().get("head");
        XSSFCell cell = null;
        XSSFRow rowFirst = sheet.createRow(0);// 创建第一行（报表名称）
        cell = rowFirst.createCell(0);
        cell.setCellValue(StringUtils.isBlank(headName) ? sheet.getSheetName() : headName);
        cell.setCellStyle(tilteStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, heads.length - 1));

        XSSFRow row = sheet.createRow(1);// 创建第二行（列名）
        if (heads != null && heads.length > 0) {
            XSSFCellStyle cellStyle = styles.get().get("title");
            for (int i = 0; i < heads.length; i++) {
                cell = row.createCell(i);
                if (heads[i] != null) {
                    cell.setCellValue(heads[i]);
                    cell.setCellStyle(cellStyle);
                }
            }
        }
    }

    /**
     * 构建sheet数据内容
     *
     * @param sheet
     * @param contents
     * @param index
     */
    private static void createSheetData(XSSFSheet sheet, List<Object[]> contents, int index) {
        XSSFCellStyle contentStyle = ExcelUtil.styles.get().get("content");
        XSSFCellStyle integerStyle = ExcelUtil.styles.get().get("integer");
        XSSFCellStyle doubleStyle = ExcelUtil.styles.get().get("double");
        Iterator<Object[]> it = contents.iterator();
        Row nextrow;
        Cell cell2;
        Object[] obj;
        // 遍历数据
        while (it.hasNext()) {
            nextrow = sheet.createRow(index++);
            obj = it.next();
            if (obj != null) {
                int objLen = obj.length;
                for (int i = 0; i < objLen; i++) {
                    cell2 = nextrow.createCell(i);
                    if (obj[i] != null) {
                        if (obj[i] instanceof Float || obj[i] instanceof Double || StringUtils.isNumeric(obj[i].toString())) {
                            cell2.setCellValue(Double.parseDouble(obj[i].toString()));
                            cell2.setCellStyle(doubleStyle);
                        } else if (obj[i] instanceof Integer || (obj[i] instanceof Long && obj[i].toString().length() <= 10)
                                || (StringUtils.isNumeric(obj[i].toString()) && obj[i].toString().length() <= 10)) {
                            cell2.setCellValue(Integer.parseInt(obj[i].toString()));
                            cell2.setCellStyle(integerStyle);
                        } else {
                            cell2.setCellValue(obj[i].toString());
                            cell2.setCellStyle(contentStyle);
                        }
                    } else {
                        cell2.setCellValue("");
                        cell2.setCellStyle(contentStyle);
                    }
                }
            }
        }
    }

    /**
     * 创建单元格表头样式
     *
     * @param workbook 工作薄
     */
    private static XSSFCellStyle createCellHeadStyle(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置边框样式
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        //设置对齐样式
        style.setAlignment(HorizontalAlignment.CENTER);
        // 生成字体
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 20);
        font.setBold(true);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }

    /**
     * 创建单元格表头标题样式
     *
     * @param workbook 工作薄
     */
    private static XSSFCellStyle createCellTitleStyle(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置边框样式
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        //设置对齐样式
        style.setAlignment(HorizontalAlignment.CENTER);
        // 生成字体
        XSSFFont font = workbook.createFont();
        // 表头样式
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(new XSSFColor(Color.CYAN));
        font.setBold(true);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }

    /**
     * 创建单元格正文样式
     *
     * @param workbook 工作薄
     */
    private static XSSFCellStyle createCellContentStyle(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置边框样式
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        // 生成字体
        XSSFFont font = workbook.createFont();
        // 正文样式
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        font.setBold(false);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }

    /**
     * 单元格样式(Integer)列表
     */
    private static XSSFCellStyle createCellContent4IntegerStyle(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置边框样式
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        // 生成字体
        XSSFFont font = workbook.createFont();
        // 正文样式
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        font.setBold(false);
        // 把字体应用到当前的样式
        style.setFont(font);
        style.setDataFormat(workbook.createDataFormat().getFormat("#,##0"));//数据格式只显示整数
        return style;
    }

    /**
     * 单元格样式(Double)列表
     */
    private static XSSFCellStyle createCellContent4DoubleStyle(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置边框样式
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        // 生成字体
        XSSFFont font = workbook.createFont();
        // 正文样式
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        font.setBold(false);
        // 把字体应用到当前的样式
        style.setFont(font);
        style.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));//保留两位小数点
        return style;
    }

    /**
     * 单元格样式列表
     */
    private static Map<String, XSSFCellStyle> styleMap(XSSFWorkbook workbook) {
        Map<String, XSSFCellStyle> styleMap = new LinkedHashMap<>();
        styleMap.put("head", createCellHeadStyle(workbook));
        styleMap.put("title", createCellTitleStyle(workbook));
        styleMap.put("content", createCellContentStyle(workbook));
        styleMap.put("integer", createCellContent4IntegerStyle(workbook));
        styleMap.put("double", createCellContent4DoubleStyle(workbook));
        return styleMap;
    }


    /**
     * 读取Excel内容
     *
     * @param file        需要被读的文件对象
     * @param startRow    从哪一行开始读 (rowIndex从0开始的)
     * @param isExcel2003 是否是excel2003还是更高的版本
     * @param sheetIndex  读取哪一个sheet (sheetIndex也是从0开始)
     * @return List<List < String>>
     * @throws Exception
     */
    public static List<List<String>> readExcel(File file, int startRow, boolean isExcel2003, int sheetIndex) throws Exception {
        List<List<String>> dataLst;
        InputStream is = null;
        try {
            /** 创建读取文件的输入流  */
            is = new FileInputStream(file);
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb;
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook(is);
            }
            /** 调用本类的读取方法读取excel数据  */
            dataLst = read(wb, startRow, sheetIndex);
        } catch (Exception ex) {
            logger.error("读取excel文件异常!", ex);
            ex.printStackTrace();
            throw ex;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {

                }
            }
        }
        /** 返回最后读取的结果 */
        return dataLst;
    }

    private static List<List<String>> read(Workbook wb, int startRow, int sheetIndex) {
        /** 总列数 */
        int totalCells = 0;
        /** 创建集合存储读取的数据  */
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(sheetIndex);
        /** 得到Excel的行数  */
        int totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数  */
        if (totalRows >= 1 && sheet.getRow(0) != null) {
            totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        /** 循环Excel的行  */
        for (int r = startRow; ; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                break;
            }
            List<String> rowLst = new ArrayList<String>();
            /** 循环Excel的列 */
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                String cellValue = "";
                if (null != cell) {
                    // 以下是判断数据的类型
                    switch (cell.getCellType()) {
                        case 0: // 数字
                            // 判断是不是日期格式
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                cellValue = cell.getDateCellValue() + "";
                            } else {
                                cellValue = cell.getNumericCellValue() + "";
                            }
                            break;
                        case 1: // 字符串
                            cellValue = cell.getStringCellValue();
                            break;
                        case 2: // Boolean
                            cellValue = cell.getBooleanCellValue() + "";
                            break;
                        case 3: // 公式
                            cellValue = cell.getCellFormula() + "";
                            break;
                        case 4: // 空值
                            cellValue = "";
                            break;
                        case 5: // 故障
                            cellValue = "非法字符";
                            break;
                        default:
                            cellValue = "未知类型";
                            break;
                    }
                }
                rowLst.add(cellValue);
            }
            /** 保存第r行的第c列 */
            boolean isEmptyRow = true;

            if (rowLst != null) {
                for (String s : rowLst) {
                    if (s != null && !s.isEmpty()) {
                        isEmptyRow = false;
                    }
                }
            }
            if (!isEmptyRow) {
                dataLst.add(rowLst);
            }
        }
        return dataLst;
    }

    /**
     * 读取Excel内容
     *
     * @param filePath    被读取文件的绝对路径
     * @param startRow
     * @param isExcel2003
     * @param sheetIndex
     * @return List<List < String>>
     * @throws Exception
     */
    public static List<List<String>> readExcel(String filePath, int startRow, boolean isExcel2003, int sheetIndex) throws Exception {

        return readExcel(new File(filePath), startRow, isExcel2003, sheetIndex);
    }

    /**
     * 将数据写入Excel工作簿
     *
     * @param header      表格的标题
     * @param dataList    所需写入的数据 List<List<String>>
     * @param isExcel2003 是否是excel2003还是更高的版本
     * @param sheetName   生成的excel中sheet的名字
     * @return Workbook    之后直接写出即可，如workbook.write(new FileOutputStream("E://test/20190410_test.xlsx"));
     */
    public static Workbook getWorkbookFromList(List<String> header, List<List<String>> dataList, boolean isExcel2003,
                                               String sheetName) {
        Workbook wb;
        // 创建Workbook对象(excel的文档对象)
        if (isExcel2003) {
            wb = new HSSFWorkbook();
        } else {
            wb = new XSSFWorkbook();
        }
        // 建立新的sheet对象（excel的表单）
        Sheet sheet = wb.createSheet(sheetName);
        // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        int rowNum = 0;
        Row row0 = sheet.createRow(rowNum);
        if (!CollectionUtils.isEmpty(header)) {
            // 设置表头
            for (int i = 0; i < header.size(); i++) {
                Cell cell = row0.createCell(i);
                // 设置单元格样式
                cell.setCellStyle(POIUtil.getCellStyle(wb, "Calibri", (short) 12));
                // 设置列宽
                sheet.setColumnWidth(i, 256 * 20);
                cell.setCellValue(header.get(i));
            }
            rowNum++;
        }
        if (!CollectionUtils.isEmpty(dataList)) {
            // 填充数据
            for (List<String> cellList : dataList) {
                Row row = sheet.createRow(rowNum);
                for (int i = 0; i < cellList.size(); i++) {
                    Cell cell = row.createCell(i);
                    cell.setCellStyle(POIUtil.getCellStyle(wb, "Calibri", (short) 12));
                    if (CollectionUtils.isEmpty(header)) {
                        sheet.setColumnWidth(i, 256 * 20);
                    }
                    cell.setCellValue(cellList.get(i));
                }
                rowNum++;
            }
        }
        return wb;
    }

    /**
     * 将数据写入Excel工作簿
     *
     * @param header      表格的标题
     * @param dataList    所需写入的数据 List<Object>
     * @param isExcel2003 是否是excel2003还是更高的版本
     * @param sheetName   生成的excel中sheet的名字
     * @return Workbook对象，之后直接写出即可，如workbook.write(new FileOutputStream("E://test/20190410_test.xlsx"));
     * @throws Exception
     */
    public static Workbook getWorkbookFromObj(List<String> header, List<?> dataList, boolean isExcel2003,
                                              String sheetName) throws Exception {
        Workbook wb;
        // 创建Workbook对象(excel的文档对象)
        if (isExcel2003) {
            wb = new HSSFWorkbook();
        } else {
            wb = new XSSFWorkbook();
        }
        // 建立新的sheet对象（excel的表单）
        Sheet sheet = wb.createSheet(sheetName);
        // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        int rowNum = 0;
        Row row0 = sheet.createRow(rowNum);
        if (!CollectionUtils.isEmpty(header)) {
            // 设置表头
            for (int i = 0; i < header.size(); i++) {
                Cell cell = row0.createCell(i);
                // 设置单元格样式
                cell.setCellStyle(POIUtil.getCellStyle(wb, "Calibri", (short) 12));
                sheet.setColumnWidth(i, 256 * 20);
                cell.setCellValue(header.get(i));
            }
            rowNum++;
        }
        if (!CollectionUtils.isEmpty(dataList)) {
            // 填充数据
            Class<? extends Object> objClass = dataList.get(0).getClass();
            Field[] fields = objClass.getDeclaredFields();
            for (int i = 0; i < dataList.size(); i++) {
                // 创建row对象
                Row row = sheet.createRow(rowNum);
                // 遍历获取每一个字段的值
                for (int j = 0; j < fields.length; j++) {
                    String fieldVal = "";
                    Method[] methods = objClass.getDeclaredMethods();
                    for (Method method : methods) {
                        if (method.getName().equalsIgnoreCase("get" + fields[j].getName())) {
                            String property = (String) method.invoke(dataList.get(i), null);
                            fieldVal = property == null ? "" : property;
                            break;
                        }
                    }
                    Cell cell = row.createCell(j);
                    cell.setCellStyle(POIUtil.getCellStyle(wb, "Calibri", (short) 12));
                    if (CollectionUtils.isEmpty(header)) {
                        sheet.setColumnWidth(j, 256 * 20);
                    }
                    cell.setCellValue(fieldVal);
                }
                rowNum++;
            }
        }
        return wb;
    }

    public static boolean validateExcel(String filePath) {
        /** 检查文件名是否为空或者是否是Excel格式的文件 */
        if (filePath == null
                || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            // "文件名不是excel格式";
            return false;
        }
        /** 检查文件是否存在 */
        File file = new File(filePath);
        if (file == null || !file.exists()) {
            // "文件不存在";
            return false;
        }
        return true;
    }

    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    public static List<List<String>> readInvoiceShushiSakuteiExcel(String filePath, int startRow, int sheetIndex, int flg) throws Exception {

        try (InputStream is = new FileInputStream(new File(filePath))) {
            List<List<String>> dataLst = null;
            Workbook wb = new XSSFWorkbook(is);
            if (flg == 1) {
                /** 调用本类的读取方法读取excel数据  */
                dataLst = readInvoiceShushiSakutei999(wb, startRow, sheetIndex);
            } else if (flg == 2) {
                // FP1
                dataLst = readInvoiceShushiSakuteiFp1(wb, startRow, sheetIndex);
            } else if (flg == 3) {
                // IO
                dataLst = readInvoiceShushiSakuteiIo(wb, startRow, sheetIndex);
            } else if (flg == 4) {
                // SD
                dataLst = readInvoiceShushiSakuteiSd(wb, startRow, sheetIndex);
            } else if (flg == 5) {
                // SUMAPURI
                dataLst = readInvoiceShushiSakuteiSumapuri(wb, startRow, sheetIndex);
            } else if (flg == 6) {
                // SUMAPURIDAISU
                dataLst = readInvoiceShushiSakuteiSumapuriDaisu(wb, startRow, sheetIndex);
            }
            /** 返回最后读取的结果 */
            return dataLst;
        }
    }

    private static List<List<String>> readInvoiceShushiSakutei999(Workbook wb, int startRow, int sheetIndex) {
        /** 总列数 */
        int totalCells = 0;
        /** 创建集合存储读取的数据  */
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(sheetIndex);
        /** 得到Excel的行数  */
        int totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数  */
        if (totalRows >= 1 && sheet.getRow(1) != null) {
            totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
        }
        /** 循环Excel的行  */
        for (int r = startRow; r < 13; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                break;
            }
            List<String> rowLst = new ArrayList<String>();
            /** 循环Excel的列 */
            for (int c = 0; c < totalCells; c++) {
                if (c < 6) {
                    Cell cell = row.getCell(c);
                    String cellValue = "";
                    if (null != cell) {
                        // 以下是判断数据的类型
                        switch (cell.getCellType()) {
                            case 0: // 数字
                                // 判断是不是日期格式
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    cellValue = cell.getDateCellValue() + "";
                                } else {
                                    cellValue = cell.getNumericCellValue() + "";
                                }
                                break;
                            case 1: // 字符串
                                cellValue = cell.getStringCellValue();
                                break;
                            case 2: // 公式
//                                String a = "SUMIF('②明細書'!$A$8:$A$29,\"*九州\",'②明細書'!$T$8:$T$29)";
//                                cell.setCellFormula(a);
                                //cell.setCellValue("SUMIF('②明細書'!$A$8:$A$29,\"*九州\",'②明細書'!$T$8:$T$29)");
//                                FormulaEvaluator formulaEvaluator1 = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
//                                System.out.println("cell:"+ cell);
//                                cellValue = POIUtil.getCellValueFormula(cell, formulaEvaluator1);
//                                System.out.println("公式:"+ cellValue);
                                break;
                            case 3: // CELL_TYPE_BLANK
                                cellValue = "";
                                //cellValue = cell.getCellFormula() + "";
                                break;
                            case 4: // boolean
                                cellValue = cell.getBooleanCellValue() + "";
                                break;
                            case 5: // 故障
                                cellValue = "非法字符";
                                break;
                            default:
                                cellValue = "未知类型";
                                break;
                        }
                    }
                    rowLst.add(cellValue);
                }

            }
            /** 保存第r行的第c列 */
            boolean isEmptyRow = true;

            if (rowLst != null) {
                for (String s : rowLst) {
                    if (s != null && !s.isEmpty()) {
                        isEmptyRow = false;
                    }
                }
            }
            if (!isEmptyRow) {
                dataLst.add(rowLst);
            }
        }
        return dataLst;
    }

    private static List<List<String>> readInvoiceShushiSakuteiFp1(Workbook wb, int startRow, int sheetIndex) {
        /** 总列数 */
        int totalCells = 0;
        /** 创建集合存储读取的数据  */
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(sheetIndex);
        /** 得到Excel的行数  */
        int totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数  */

        /** 循环Excel的行  */
        for (int r = startRow; r < 17; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                break;
            }
            List<String> rowLst = new ArrayList<String>();
            Cell cell = row.getCell(20);
            String cellValue = "";
            if (null != cell) {
                // 以下是判断数据的类型
                switch (cell.getCellType()) {
                    case 0: // 数字
                        // 判断是不是日期格式
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            cellValue = cell.getDateCellValue() + "";
                        } else {
                            cellValue = cell.getNumericCellValue() + "";
                        }
                        break;
                    case 1: // 字符串
                        cellValue = cell.getStringCellValue();
                        break;
                    case 2: // 公式
                        FormulaEvaluator formulaEvaluator1 = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
                        cellValue = POIUtil.getCellValueFormula(cell, formulaEvaluator1);
                        break;
                    case 3: // CELL_TYPE_BLANK
                        cellValue = "";
                        break;
                    case 4: // boolean
                        cellValue = cell.getBooleanCellValue() + "";
                        break;
                    case 5: // 故障
                        cellValue = "非法字符";
                        break;
                    default:
                        cellValue = "未知类型";
                        break;
                }
            }
            rowLst.add(cellValue);

            /** 保存第r行的第c列 */
            boolean isEmptyRow = true;

            if (rowLst != null) {
                for (String s : rowLst) {
                    if (s != null && !s.isEmpty()) {
                        isEmptyRow = false;
                    }
                }
            }
            if (!isEmptyRow) {
                dataLst.add(rowLst);
            }
        }
        return dataLst;
    }

    private static List<List<String>> readInvoiceShushiSakuteiIo(Workbook wb, int startRow, int sheetIndex) {

        /** 创建集合存储读取的数据  */
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(sheetIndex);

        /** 循环Excel的行  */
        for (int r = startRow; r < 69; r++) {
            List<String> rowLst = new ArrayList<String>();
            Row row = sheet.getRow(r);
            // 上半部分
            if (r < 34) {
                if (r == 30 || r == 31 || r == 32) {
                    continue;
                }

                if (r == 33) {
                    Cell cell = row.getCell(4);
                    String cellValue = cell.getNumericCellValue() + "";
                    rowLst.add(cellValue);
                } else {
                    /** 循环Excel的列 */
                    for (int c = 4; c < 27; c++) {
                        Cell cell = row.getCell(c);
                        String cellValue = "";
                        if (null != cell) {
                            // 以下是判断数据的类型
                            switch (cell.getCellType()) {
                                case 0: // 数字
                                    // 判断是不是日期格式
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                        cellValue = cell.getDateCellValue() + "";
                                    } else {
                                        cellValue = cell.getNumericCellValue() + "";
                                    }
                                    break;
                                case 1: // 字符串
                                    cellValue = cell.getStringCellValue();
                                    break;
                                case 2: // 公式
                                    FormulaEvaluator formulaEvaluator1 = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
                                    System.out.println("cell:" + cell);
                                    cellValue = POIUtil.getCellValueFormula(cell, formulaEvaluator1);
                                    System.out.println("公式:" + cellValue);
                                    break;
                                case 3: // CELL_TYPE_BLANK
                                    cellValue = "";
                                    //cellValue = cell.getCellFormula() + "";
                                    break;
                                case 4: // boolean
                                    cellValue = cell.getBooleanCellValue() + "";
                                    break;
                                case 5: // 故障
                                    cellValue = "非法字符";
                                    break;
                                default:
                                    cellValue = "未知类型";
                                    break;
                            }
                        }
                        rowLst.add(cellValue);

                    }
                }
            }

            // 下半部分 iO!（DIGNOJ版）
            if (r > 40 && r < 68) {
                if (r == 61 || r == 62 || r == 63 || r == 65 || r == 66) {
                    continue;
                }
                if (r == 64 || r == 67) {
                    Cell cell = row.getCell(4);
                    String cellValue = cell.getNumericCellValue() + "";
                    rowLst.add(cellValue);
                } else {

                    /** 循环Excel的列 */
                    for (int c = 4; c < 19; c++) {
                        Cell cell = row.getCell(c);
                        String cellValue = "";
                        if (null != cell) {
                            // 以下是判断数据的类型
                            switch (cell.getCellType()) {
                                case 0: // 数字
                                    // 判断是不是日期格式
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                        cellValue = cell.getDateCellValue() + "";
                                    } else {
                                        cellValue = cell.getNumericCellValue() + "";
                                    }
                                    break;
                                case 1: // 字符串
                                    cellValue = cell.getStringCellValue();
                                    break;
                                case 2: // 公式
                                    FormulaEvaluator formulaEvaluator1 = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
                                    System.out.println("cell:" + cell);
                                    cellValue = POIUtil.getCellValueFormula(cell, formulaEvaluator1);
                                    System.out.println("公式:" + cellValue);
                                    break;
                                case 3: // CELL_TYPE_BLANK
                                    cellValue = "";
                                    //cellValue = cell.getCellFormula() + "";
                                    break;
                                case 4: // boolean
                                    cellValue = cell.getBooleanCellValue() + "";
                                    break;
                                case 5: // 故障
                                    cellValue = "非法字符";
                                    break;
                                default:
                                    cellValue = "未知类型";
                                    break;
                            }
                        }
                        rowLst.add(cellValue);
                    }
                }

            }

            /** 保存第r行的第c列 */
            boolean isEmptyRow = true;

            if (rowLst != null) {
                for (String s : rowLst) {
                    if (s != null && !s.isEmpty()) {
                        isEmptyRow = false;
                    }
                }
            }
            if (!isEmptyRow) {
                dataLst.add(rowLst);
            }
        }
        return dataLst;
    }

    private static List<List<String>> readInvoiceShushiSakuteiSd(Workbook wb, int startRow, int sheetIndex) {

        /** 创建集合存储读取的数据  */
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(sheetIndex);

        /** 循环Excel的行  */
        for (int r = startRow; r < 87; r++) {
            List<String> rowLst = new ArrayList<String>();
            Row row = sheet.getRow(r);
            // ① SDスマホ
            if (r < 30) {
                /** 循环Excel的列 */
                for (int c = 4; c < 25; c++) {
                    Cell cell = row.getCell(c);
                    String cellValue = "";
                    if (null != cell) {
                        // 以下是判断数据的类型
                        switch (cell.getCellType()) {
                            case 0: // 数字
                                // 判断是不是日期格式
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    cellValue = cell.getDateCellValue() + "";
                                } else {
                                    cellValue = cell.getNumericCellValue() + "";
                                }
                                break;
                            case 1: // 字符串
                                cellValue = cell.getStringCellValue();
                                break;
                            case 2: // 公式
                                FormulaEvaluator formulaEvaluator1 = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
                                System.out.println("cell:" + cell);
                                cellValue = POIUtil.getCellValueFormula(cell, formulaEvaluator1);
                                System.out.println("公式:" + cellValue);
                                break;
                            case 3: // CELL_TYPE_BLANK
                                cellValue = "";
                                //cellValue = cell.getCellFormula() + "";
                                break;
                            case 4: // boolean
                                cellValue = cell.getBooleanCellValue() + "";
                                break;
                            case 5: // 故障
                                cellValue = "非法字符";
                                break;
                            default:
                                cellValue = "未知类型";
                                break;
                        }
                    }
                    rowLst.add(cellValue);

                }

            }

            // ② シンプルスマホ
            if (r > 37 && r < 58) {
                /** 循环Excel的列 */
                for (int c = 4; c < 15; c++) {
                    Cell cell = row.getCell(c);
                    String cellValue = "";
                    if (null != cell) {
                        // 以下是判断数据的类型
                        switch (cell.getCellType()) {
                            case 0: // 数字
                                // 判断是不是日期格式
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    cellValue = cell.getDateCellValue() + "";
                                } else {
                                    cellValue = cell.getNumericCellValue() + "";
                                }
                                break;
                            case 1: // 字符串
                                cellValue = cell.getStringCellValue();
                                break;
                            case 2: // 公式
                                FormulaEvaluator formulaEvaluator1 = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
                                System.out.println("cell:" + cell);
                                cellValue = POIUtil.getCellValueFormula(cell, formulaEvaluator1);
                                System.out.println("公式:" + cellValue);
                                break;
                            case 3: // CELL_TYPE_BLANK
                                cellValue = "";
                                //cellValue = cell.getCellFormula() + "";
                                break;
                            case 4: // boolean
                                cellValue = cell.getBooleanCellValue() + "";
                                break;
                            case 5: // 故障
                                cellValue = "非法字符";
                                break;
                            default:
                                cellValue = "未知类型";
                                break;
                        }
                    }
                    rowLst.add(cellValue);
                }

            }

            // 店頭受取用スマホ
            if (r > 65 && r < 87) {
                /** 循环Excel的列 */
                for (int c = 4; c < 16; c++) {
                    Cell cell = row.getCell(c);
                    String cellValue = "";
                    if (null != cell) {
                        // 以下是判断数据的类型
                        switch (cell.getCellType()) {
                            case 0: // 数字
                                // 判断是不是日期格式
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    cellValue = cell.getDateCellValue() + "";
                                } else {
                                    cellValue = cell.getNumericCellValue() + "";
                                }
                                break;
                            case 1: // 字符串
                                cellValue = cell.getStringCellValue();
                                break;
                            case 2: // 公式
                                FormulaEvaluator formulaEvaluator1 = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
                                System.out.println("cell:" + cell);
                                cellValue = POIUtil.getCellValueFormula(cell, formulaEvaluator1);
                                System.out.println("公式:" + cellValue);
                                break;
                            case 3: // CELL_TYPE_BLANK
                                cellValue = "";
                                //cellValue = cell.getCellFormula() + "";
                                break;
                            case 4: // boolean
                                cellValue = cell.getBooleanCellValue() + "";
                                break;
                            case 5: // 故障
                                cellValue = "非法字符";
                                break;
                            default:
                                cellValue = "未知类型";
                                break;
                        }
                    }
                    rowLst.add(cellValue);
                }

            }

            /** 保存第r行的第c列 */
            boolean isEmptyRow = true;

            if (rowLst != null) {
                for (String s : rowLst) {
                    if (s != null && !s.isEmpty()) {
                        isEmptyRow = false;
                    }
                }
            }
            if (!isEmptyRow) {
                dataLst.add(rowLst);
            }
        }
        return dataLst;
    }

    private static List<List<String>> readInvoiceShushiSakuteiSumapuri(Workbook wb, int startRow, int sheetIndex) {
        /** 总列数 */
        int totalCells = 0;
        /** 创建集合存储读取的数据  */
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(sheetIndex);
        /** 得到Excel的行数  */
        int totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数  */
//        if (totalRows >= 1 && sheet.getRow(1) != null) {
//            totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
//        }

        /** 循环Excel的行  */
        for (int r = startRow; r < 17; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                break;
            }
            List<String> rowLst = new ArrayList<String>();
            /** 循环Excel的列 */
            for (int c = 6; c < 10; c++) {
                if (c == 7 || c == 8) {
                    continue;
                }
                Cell cell = row.getCell(c);
                String cellValue = "";
                if (null != cell) {
                    // 以下是判断数据的类型
                    switch (cell.getCellType()) {
                        case 0: // 数字
                            // 判断是不是日期格式
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                cellValue = cell.getDateCellValue() + "";
                            } else {
                                cellValue = cell.getNumericCellValue() + "";
                            }
                            break;
                        case 1: // 字符串
                            cellValue = cell.getStringCellValue();
                            break;
                        case 2: // 公式
                            cellValue = "";
                            break;
                        case 3: // CELL_TYPE_BLANK
                            cellValue = "";
                            //cellValue = cell.getCellFormula() + "";
                            break;
                        case 4: // boolean
                            cellValue = cell.getBooleanCellValue() + "";
                            break;
                        case 5: // 故障
                            cellValue = "非法字符";
                            break;
                        default:
                            cellValue = "未知类型";
                            break;
                    }
                }
                rowLst.add(cellValue);


            }

            /** 保存第r行的第c列 */
            boolean isEmptyRow = true;

            if (rowLst != null) {
                for (String s : rowLst) {
                    if (s != null && !s.isEmpty()) {
                        isEmptyRow = false;
                    }
                }
            }
            if (!isEmptyRow) {
                dataLst.add(rowLst);
            }
        }
        return dataLst;
    }

    private static List<List<String>> readInvoiceShushiSakuteiSumapuriDaisu(Workbook wb, int startRow, int sheetIndex) {

        /** 创建集合存储读取的数据  */
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(sheetIndex);

        /** 循环Excel的行  */
        for (int r = startRow; r < 17; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                break;
            }
            List<String> rowLst = new ArrayList<String>();
            /** 循环Excel的列 */

            for (int c = 5; c < 14; c++) {
                if(c == 7 || c == 8 || c == 9 || c == 10 || c == 11){
                    continue;
                }
                Cell cell = row.getCell(c);
                String cellValue = "";
                if (null != cell) {
                    // 以下是判断数据的类型
                    switch (cell.getCellType()) {
                        case 0: // 数字
                            // 判断是不是日期格式
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                cellValue = cell.getDateCellValue() + "";
                            } else {
                                cellValue = cell.getNumericCellValue() + "";
                            }
                            break;
                        case 1: // 字符串
                            cellValue = cell.getStringCellValue();
                            break;
                        case 2: // 公式
                            cellValue = "";
                            break;
                        case 3: // CELL_TYPE_BLANK
                            cellValue = "";
                            //cellValue = cell.getCellFormula() + "";
                            break;
                        case 4: // boolean
                            cellValue = cell.getBooleanCellValue() + "";
                            break;
                        case 5: // 故障
                            cellValue = "非法字符";
                            break;
                        default:
                            cellValue = "未知类型";
                            break;
                    }
                }
                rowLst.add(cellValue);

            }

            /** 保存第r行的第c列 */
            boolean isEmptyRow = true;

            if (rowLst != null) {
                for (String s : rowLst) {
                    if (s != null && !s.isEmpty()) {
                        isEmptyRow = false;
                    }
                }
            }
            if (!isEmptyRow) {
                dataLst.add(rowLst);
            }
        }
        return dataLst;
    }

    public static List<List<String>> readJobMotoExcel(String filePath, int startRow, int sheetIndex, int flg) throws Exception {

        try (InputStream is = new FileInputStream(new File(filePath))) {
            List<List<String>> dataLst = null;
            Workbook wb = new XSSFWorkbook(is);
            if (flg == 1) {
                /** 除外  */
                dataLst = readJobMotoJogaiRisuto(wb, startRow, sheetIndex);
            } else if (flg == 2) {
                /** T_リース  */
                dataLst = readJobMotoRisu(wb, startRow, sheetIndex);
            } else if (flg == 3) {
                /** T_全損紛失  */
                dataLst = readJobMotoZensonFunshitsu(wb, startRow, sheetIndex);
            } else if (flg == 4) {
                /** t_スマホ出荷日  */
                dataLst = readJobMotoSumahoShukkabi(wb, startRow, sheetIndex);
            } else if (flg == 5) {
                /** T_電話番号  */
                dataLst = readJobMotoTenwabango(wb, startRow, sheetIndex);
            } else if (flg == 6) {
                /** T_貸付  */
                dataLst = readJobMotoKashitsuke(wb, startRow, sheetIndex);
            }

            /** 返回最后读取的结果 */
            return dataLst;
        }
    }


    /**
     * 除外
     */
    private static List<List<String>> readJobMotoJogaiRisuto(Workbook wb, int startRow, int sheetIndex) {
        /** 总列数 */
        int totalCells = 0;
        /** 创建集合存储读取的数据  */
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(sheetIndex);
        /** 得到Excel的行数  */
        int totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数  */
        if (totalRows >= 1 && sheet.getRow(1) != null) {
            totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
        }
        /** 循环Excel的行  */
        for (int r = startRow; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                break;
            }
            List<String> rowLst = new ArrayList<String>();
            /** 循环Excel的列 */
            for (int c = 0; c < 5; c++) {
                if (c < 5) {
                    Cell cell = row.getCell(c);
                    String cellValue = "";
                    if (null != cell) {
                        // 以下是判断数据的类型
                        SimpleDateFormat sdf = null;
                        sdf = new SimpleDateFormat("yyyy/MM/dd");
                        NumberFormat nf = NumberFormat.getInstance();
                        switch (cell.getCellType()) {
                            case 0: // 数字
                                // 判断是不是日期格式
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    cellValue = sdf.format(cell.getDateCellValue());
                                } else if (c == 2) {
                                    cellValue = sdf.format(cell.getNumericCellValue());
                                } else if (c == 3) {
                                    if (cell != null) {
                                        cell.setCellType(Cell.CELL_TYPE_STRING);
                                        cellValue = cell.getStringCellValue();
                                    }
                                } else {
                                    cellValue = cell.getNumericCellValue() + "";
                                }
                                break;
                            case 1: // 字符串
                                cellValue = cell.getStringCellValue();
                                break;
                            case 2: // 公式
//                                String a = "SUMIF('②明細書'!$A$8:$A$29,\"*九州\",'②明細書'!$T$8:$T$29)";
//                                cell.setCellFormula(a);
                                //cell.setCellValue("SUMIF('②明細書'!$A$8:$A$29,\"*九州\",'②明細書'!$T$8:$T$29)");
//                                FormulaEvaluator formulaEvaluator1 = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
//                                System.out.println("cell:"+ cell);
//                                cellValue = POIUtil.getCellValueFormula(cell, formulaEvaluator1);
//                                System.out.println("公式:"+ cellValue);
                                break;
                            case 3: // CELL_TYPE_BLANK
                                cellValue = "";
                                //cellValue = cell.getCellFormula() + "";
                                break;
                            case 4: // boolean
                                cellValue = cell.getBooleanCellValue() + "";
                                break;
                            case 5: // 故障
                                cellValue = "非法字符";
                                break;
                            default:
                                cellValue = "未知类型";
                                break;
                        }
                    }
                    rowLst.add(cellValue);
                }

            }
            /** 保存第r行的第c列 */
            boolean isEmptyRow = true;

            if (rowLst != null) {
                for (String s : rowLst) {
                    if (s != null && !s.isEmpty()) {
                        isEmptyRow = false;
                    }
                }
            }
            if (!isEmptyRow) {
                dataLst.add(rowLst);
            }
        }
        return dataLst;
    }

    /**
     * T_リース
     */
    private static List<List<String>> readJobMotoRisu(Workbook wb, int startRow, int sheetIndex) {
        /** 总列数 */
        int totalCells = 0;
        /** 创建集合存储读取的数据  */
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(sheetIndex);
        /** 得到Excel的行数  */
        int totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数  */
        if (totalRows >= 1 && sheet.getRow(1) != null) {
            totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
        }
        /** 循环Excel的行  */
        for (int r = startRow; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                break;
            }
            List<String> rowLst = new ArrayList<String>();
            /** 循环Excel的列 */
            if (r > 0) {
                for (int c = 0; c < 7; c++) {
                    if (c < 6) {
                        Cell cell = row.getCell(c);
                        String cellValue = "";
                        if (null != cell) {
                            // 以下是判断数据的类型
                            SimpleDateFormat sdf = null;
                            sdf = new SimpleDateFormat("yyyy/MM/dd");

                            switch (cell.getCellType()) {
                                case 0: // 数字
                                    // 判断是不是日期格式
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                        cellValue = sdf.format(cell.getDateCellValue());
                                    } else {
                                        cellValue = sdf.format(cell.getNumericCellValue());
                                    }
                                    break;
                                case 1: // 字符串
                                    cellValue = cell.getStringCellValue();
                                    break;
                                case 2: // 公式
                                    try {
                                        cellValue = String.valueOf(cell.getNumericCellValue());
                                        if (c == 4 || c == 5) {
                                            double value = cell.getNumericCellValue();
                                            Date date = org.apache.poi.ss.usermodel.DateUtil
                                                    .getJavaDate(value);
                                            cellValue = sdf.format(date);
                                        } else if (c == 3) {
                                            cellValue = String.valueOf(cell.getRichStringCellValue());
                                        }
                                    } catch (IllegalStateException e) {
                                        cellValue = String.valueOf(cell.getRichStringCellValue());
                                    }
                                    break;
                                case 3: // CELL_TYPE_BLANK
                                    cellValue = "";
                                    //cellValue = cell.getCellFormula() + "";
                                    break;
                                case 4: // boolean
                                    cellValue = cell.getBooleanCellValue() + "";
                                    break;
                                case 5: // 故障
                                    cellValue = "非法字符";
                                    break;
                                default:
                                    cellValue = "未知类型";
                                    break;
                            }
                        }
                        rowLst.add(cellValue);
                    }

                }
            }

            /** 保存第r行的第c列 */
            boolean isEmptyRow = true;

            if (rowLst != null) {
                for (String s : rowLst) {
                    if (s != null && !s.isEmpty()) {
                        isEmptyRow = false;
                    }
                }
            }
            if (!isEmptyRow) {
                dataLst.add(rowLst);
            }
        }
        return dataLst;
    }

    /**
     * T_全損紛失
     */
    private static List<List<String>> readJobMotoZensonFunshitsu(Workbook wb, int startRow, int sheetIndex) {
        /** 总列数 */
        int totalCells = 0;
        /** 创建集合存储读取的数据  */
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(sheetIndex);
        /** 得到Excel的行数  */
        int totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数  */
        if (totalRows >= 1 && sheet.getRow(1) != null) {
            totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
        }
        /** 循环Excel的行  */
        for (int r = startRow; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                break;
            }
            List<String> rowLst = new ArrayList<String>();
            /** 循环Excel的列 */
            for (int c = 0; c < 5; c++) {
                if (c < 5) {
                    Cell cell = row.getCell(c);
                    String cellValue = "";
                    if (null != cell) {
                        SimpleDateFormat sdf = null;
                        sdf = new SimpleDateFormat("yyyyMMdd");
                        // 以下是判断数据的类型
                        switch (cell.getCellType()) {
                            case 0: // 数字
                                // 判断是不是日期格式
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    cellValue = sdf.format(cell.getDateCellValue());
                                } else if (cell.getCellStyle().getDataFormat() == 179) {
                                    Date d = cell.getDateCellValue();
                                    cellValue = sdf.format(d);
                                } else {
                                    if (cell != null) {
                                        cell.setCellType(Cell.CELL_TYPE_STRING);
                                        cellValue = cell.getStringCellValue();
                                    }
                                }
                                break;
                            case 1: // 字符串
                                cellValue = cell.getStringCellValue();
                                break;
                            case 2: // 公式
//                                String a = "SUMIF('②明細書'!$A$8:$A$29,\"*九州\",'②明細書'!$T$8:$T$29)";
//                                cell.setCellFormula(a);
                                //cell.setCellValue("SUMIF('②明細書'!$A$8:$A$29,\"*九州\",'②明細書'!$T$8:$T$29)");
//                                FormulaEvaluator formulaEvaluator1 = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
//                                System.out.println("cell:"+ cell);
//                                cellValue = POIUtil.getCellValueFormula(cell, formulaEvaluator1);
//                                System.out.println("公式:"+ cellValue);
                                break;
                            case 3: // CELL_TYPE_BLANK
                                cellValue = "";
                                //cellValue = cell.getCellFormula() + "";
                                break;
                            case 4: // boolean
                                cellValue = cell.getBooleanCellValue() + "";
                                break;
                            case 5: // 故障
                                cellValue = "非法字符";
                                break;
                            default:
                                cellValue = "未知类型";
                                break;
                        }
                    }
                    rowLst.add(cellValue);
                }

            }
            /** 保存第r行的第c列 */
            boolean isEmptyRow = true;

            if (rowLst != null) {
                for (String s : rowLst) {
                    if (s != null && !s.isEmpty()) {
                        isEmptyRow = false;
                    }
                }
            }
            if (!isEmptyRow) {
                dataLst.add(rowLst);
            }
        }
        return dataLst;
    }


    /**
     * t_スマホ出荷日
     */
    private static List<List<String>> readJobMotoSumahoShukkabi(Workbook wb, int startRow, int sheetIndex) {
        /** 总列数 */
        int totalCells = 0;
        /** 创建集合存储读取的数据  */
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(sheetIndex);
        /** 得到Excel的行数  */
        int totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数  */
        if (totalRows >= 1 && sheet.getRow(1) != null) {
            totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
        }
        /** 循环Excel的行  */
        for (int r = startRow; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                break;
            }
            List<String> rowLst = new ArrayList<String>();
            /** 循环Excel的列 */
            for (int c = 0; c < 2; c++) {
                if (c < 2) {
                    Cell cell = row.getCell(c);
                    String cellValue = "";
                    if (null != cell) {
                        SimpleDateFormat sdf = null;
                        sdf = new SimpleDateFormat("yyyyMMdd");
                        // 以下是判断数据的类型
                        switch (cell.getCellType()) {
                            case 0: // 数字
                                // 判断是不是日期格式
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    cellValue = sdf.format(cell.getDateCellValue());
                                } else if (cell.getCellStyle().getDataFormat() == 179) {
                                    Date d = cell.getDateCellValue();
                                    cellValue = sdf.format(d);
                                } else {
                                    if (cell != null) {
                                        cell.setCellType(Cell.CELL_TYPE_STRING);
                                        cellValue = cell.getStringCellValue();
                                    }
                                }
                                break;
                            case 1: // 字符串
                                cellValue = cell.getStringCellValue();
                                break;
                            case 2: // 公式
//                                String a = "SUMIF('②明細書'!$A$8:$A$29,\"*九州\",'②明細書'!$T$8:$T$29)";
//                                cell.setCellFormula(a);
                                //cell.setCellValue("SUMIF('②明細書'!$A$8:$A$29,\"*九州\",'②明細書'!$T$8:$T$29)");
//                                FormulaEvaluator formulaEvaluator1 = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
//                                System.out.println("cell:"+ cell);
//                                cellValue = POIUtil.getCellValueFormula(cell, formulaEvaluator1);
//                                System.out.println("公式:"+ cellValue);
                                break;
                            case 3: // CELL_TYPE_BLANK
                                cellValue = "";
                                //cellValue = cell.getCellFormula() + "";
                                break;
                            case 4: // boolean
                                cellValue = cell.getBooleanCellValue() + "";
                                break;
                            case 5: // 故障
                                cellValue = "非法字符";
                                break;
                            default:
                                cellValue = "未知类型";
                                break;
                        }
                    }
                    rowLst.add(cellValue);
                }

            }
            /** 保存第r行的第c列 */
            boolean isEmptyRow = true;

            if (rowLst != null) {
                for (String s : rowLst) {
                    if (s != null && !s.isEmpty()) {
                        isEmptyRow = false;
                    }
                }
            }
            if (!isEmptyRow) {
                dataLst.add(rowLst);
            }
        }
        return dataLst;
    }

    /**
     * t_スマホ出荷日
     */
    private static List<List<String>> readJobMotoTenwabango(Workbook wb, int startRow, int sheetIndex) {
        /** 总列数 */
        int totalCells = 0;
        /** 创建集合存储读取的数据  */
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(sheetIndex);
        /** 得到Excel的行数  */
        int totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数  */
        if (totalRows >= 1 && sheet.getRow(1) != null) {
            totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
        }
        /** 循环Excel的行  */
        for (int r = startRow; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                break;
            }
            List<String> rowLst = new ArrayList<String>();
            /** 循环Excel的列 */
            for (int c = 0; c < 2; c++) {
                if (c < 2) {
                    Cell cell = row.getCell(c);
                    String cellValue = "";
                    if (null != cell) {
                        SimpleDateFormat sdf = null;
                        sdf = new SimpleDateFormat("yyyyMMdd");
                        // 以下是判断数据的类型
                        switch (cell.getCellType()) {
                            case 0: // 数字
                                // 判断是不是日期格式
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    cellValue = sdf.format(cell.getDateCellValue());
                                } else if (cell.getCellStyle().getDataFormat() == 179) {
                                    Date d = cell.getDateCellValue();
                                    cellValue = sdf.format(d);
                                } else {
                                    if (cell != null) {
                                        cell.setCellType(Cell.CELL_TYPE_STRING);
                                        cellValue = cell.getStringCellValue();
                                    }
                                }
                                break;
                            case 1: // 字符串
                                cellValue = cell.getStringCellValue();
                                break;
                            case 2: // 公式
//                                String a = "SUMIF('②明細書'!$A$8:$A$29,\"*九州\",'②明細書'!$T$8:$T$29)";
//                                cell.setCellFormula(a);
                                //cell.setCellValue("SUMIF('②明細書'!$A$8:$A$29,\"*九州\",'②明細書'!$T$8:$T$29)");
//                                FormulaEvaluator formulaEvaluator1 = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
//                                System.out.println("cell:"+ cell);
//                                cellValue = POIUtil.getCellValueFormula(cell, formulaEvaluator1);
//                                System.out.println("公式:"+ cellValue);
                                break;
                            case 3: // CELL_TYPE_BLANK
                                cellValue = "";
                                //cellValue = cell.getCellFormula() + "";
                                break;
                            case 4: // boolean
                                cellValue = cell.getBooleanCellValue() + "";
                                break;
                            case 5: // 故障
                                cellValue = "非法字符";
                                break;
                            default:
                                cellValue = "未知类型";
                                break;
                        }
                    }
                    rowLst.add(cellValue);
                }

            }
            /** 保存第r行的第c列 */
            boolean isEmptyRow = true;

            if (rowLst != null) {
                for (String s : rowLst) {
                    if (s != null && !s.isEmpty()) {
                        isEmptyRow = false;
                    }
                }
            }
            if (!isEmptyRow) {
                dataLst.add(rowLst);
            }
        }
        return dataLst;
    }

    /**
     * T_貸付
     */
    private static List<List<String>> readJobMotoKashitsuke(Workbook wb, int startRow, int sheetIndex) {
        /** 总列数 */
        int totalCells = 0;
        /** 创建集合存储读取的数据  */
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(sheetIndex);
        /** 得到Excel的行数  */
        int totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数  */
        if (totalRows >= 1 && sheet.getRow(1) != null) {
            totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
        }
        /** 循环Excel的行  */
        for (int r = startRow; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                break;
            }
            List<String> rowLst = new ArrayList<String>();
            /** 循环Excel的列 */
            for (int c = 0; c < 23; c++) {
                if (c == 1 || c == 2 || c == 3 || c == 5 || c == 6 || c == 18 || c == 19 || c == 22) {
                    Cell cell = row.getCell(c);
                    String cellValue = "";
                    if (null != cell) {
                        SimpleDateFormat sdf = null;
                        sdf = new SimpleDateFormat("yyyyMMdd");
                        // 以下是判断数据的类型
                        switch (cell.getCellType()) {
                            case 0: // 数字
                                // 判断是不是日期格式
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    cellValue = sdf.format(cell.getDateCellValue());
                                } else if (cell.getCellStyle().getDataFormat() == 179) {
                                    Date d = cell.getDateCellValue();
                                    cellValue = sdf.format(d);
                                } else {
                                    if (cell != null) {
                                        cell.setCellType(Cell.CELL_TYPE_STRING);
                                        cellValue = cell.getStringCellValue();
                                    }
                                }
                                break;
                            case 1: // 字符串
                                cellValue = cell.getStringCellValue();
                                break;
                            case 2: // 公式
//                                String a = "SUMIF('②明細書'!$A$8:$A$29,\"*九州\",'②明細書'!$T$8:$T$29)";
//                                cell.setCellFormula(a);
                                //cell.setCellValue("SUMIF('②明細書'!$A$8:$A$29,\"*九州\",'②明細書'!$T$8:$T$29)");
//                                FormulaEvaluator formulaEvaluator1 = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
//                                System.out.println("cell:"+ cell);
//                                cellValue = POIUtil.getCellValueFormula(cell, formulaEvaluator1);
//                                System.out.println("公式:"+ cellValue);
                                break;
                            case 3: // CELL_TYPE_BLANK
                                cellValue = "";
                                //cellValue = cell.getCellFormula() + "";
                                break;
                            case 4: // boolean
                                cellValue = cell.getBooleanCellValue() + "";
                                break;
                            case 5: // 故障
                                cellValue = "非法字符";
                                break;
                            default:
                                cellValue = "未知类型";
                                break;
                        }
                    }
                    rowLst.add(cellValue);
                }

            }
            /** 保存第r行的第c列 */
            boolean isEmptyRow = true;

            if (rowLst != null) {
                for (String s : rowLst) {
                    if (s != null && !s.isEmpty()) {
                        isEmptyRow = false;
                    }
                }
            }
            if (!isEmptyRow) {
                dataLst.add(rowLst);
            }
        }
        return dataLst;
    }
}

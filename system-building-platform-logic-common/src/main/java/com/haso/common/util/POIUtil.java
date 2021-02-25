package com.haso.common.util;


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Color;

/**
 * @Description: Excel的单元格样式
 * @Author: hunger.zhu
 * @CreateDate: 2019/4/10 13:05
 */
public class POIUtil {
    /**
     * 设置单元格的边框（细）且为黑色，字体水平垂直居中，自动换行
     *
     * @param workbook
     * @param fontName
     * @param fontSize
     * @return
     */
    public static CellStyle getCellStyle(Workbook workbook, String fontName, short fontSize) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        // 设置上下左右四个边框宽度
//        style.setBorderTop(Short.parseShort( BorderStyle.THIN.toString()));
//        style.setBorderBottom(Short.parseShort( BorderStyle.THIN.toString()));
//        style.setBorderLeft(Short.parseShort( BorderStyle.THIN.toString()));
//        style.setBorderRight(Short.parseShort( BorderStyle.THIN.toString()));
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        // 设置上下左右四个边框颜色
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        // 水平居中，垂直居中，自动换行
        //style.setAlignment(Short.parseShort( HorizontalAlignment.CENTER.toString()));
        //style.setVerticalAlignment(Short.parseShort( VerticalAlignment.CENTER.toString()));
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setWrapText(false);
        // 设置字体样式及大小
        font.setFontName(fontName);
        font.setFontHeightInPoints(fontSize);

        style.setFont(font);

        return style;
    }

    //水平居中 字体加粗
    public static XSSFCellStyle getHorizontalCenterBoldHead(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getBold(wb, 20);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    //水平居中 字体加粗
    public static XSSFCellStyle getHorizontalCenterBoldHead2(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getBold(wb, 14);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }


    //水平居中 字体加粗
    public static XSSFCellStyle getHorizontalCenterBoldHead2Blue(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getBold(wb, 14);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cellStyle;
    }

    //水平居中
    public static XSSFCellStyle getHorizontalCenter(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    //水平居中
    public static XSSFCellStyle getHorizontalCenterBlue(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cellStyle;
    }

    public static XSSFCellStyle getNormal(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        return cellStyle;
    }


    //右对齐
    public static XSSFCellStyle getRight(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        return cellStyle;
    }

    public static XSSFCellStyle getNormalWrap(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        cellStyle.setWrapText(true);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }


    //加粗
    public static XSSFCellStyle getBold(XSSFWorkbook wb, int fontSize) {
        XSSFCellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) fontSize);
        cellStyle.setFont(font);
        return cellStyle;
    }

    // 左上边框
    public static XSSFCellStyle getBorderLT(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        return cellStyle;
    }

    // 右上边框
    public static XSSFCellStyle getBorderTR(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        return cellStyle;
    }

    //上边框
    public static XSSFCellStyle getBorderT(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setBorderTop(BorderStyle.THIN);
        return cellStyle;
    }

    //左边框
    public static XSSFCellStyle getBorderL(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        return cellStyle;
    }

    //右边框
    public static XSSFCellStyle getBorderR(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setBorderRight(BorderStyle.THIN);
        return cellStyle;
    }

    //左下边框
    public static XSSFCellStyle getBorderLB(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    //下边框
    public static XSSFCellStyle getBorderB(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    //下边框
    public static XSSFCellStyle getBorderLBR(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        return cellStyle;
    }

    //下边框
    public static XSSFCellStyle getBorderBRight(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        return cellStyle;
    }


    //下边框,居中
    public static XSSFCellStyle getBorderBC(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }


    //右下边框
    public static XSSFCellStyle getBorderBR(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        return cellStyle;
    }

    //右下边框，居中
    public static XSSFCellStyle getBorderBCR(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    //蓝色字体
    public static XSSFCellStyle getBlue(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        return cellStyle;
    }

    //蓝色字体
    public static XSSFCellStyle getCenterBlue(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        return cellStyle;
    }

    //水平居中 字体加粗加大
    public static XSSFCellStyle getHorizontalCenterBoldHeadBig(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getBold(wb, 16);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    //水平居中 四周边框
    public static XSSFCellStyle getHorizontalCenterBorder(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        return cellStyle;
    }

    // 灰色背景
    public static XSSFCellStyle getGrayBackgroundColor(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());//背景颜色
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    // 靠右显示
    public static XSSFCellStyle getRightStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont9Style(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getLeftFont9Style(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getMSPCenterFont10Style(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getLeftFont11Style(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont11Style(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getMSPFont11Style(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getMSPFont9Style(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getMSPFont11YelloRightStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getMaelioFont9Style(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("メイリオ");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getAquaMaelioFont9Style(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFillForegroundColor(new XSSFColor( new Color(218, 238, 243)));
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("メイリオ");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getMSPFont11RightStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getMSPFont10NoBorderLeftStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getMSPFont10NoBorderRightStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getMSPFont11NoBorderStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont10Style(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getRightFont10Style(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont12Style(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getLeftFont12Style(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getRightFont12Style(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getRightFont11Style(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getRightFont11BorderDStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    // 靠左显示
    public static XSSFCellStyle getLeftStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());//背景颜色
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        return cellStyle;
    }

    // 第一行显示
    public static XSSFCellStyle getFirstRowStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    // 第一行靠右显示
    public static XSSFCellStyle getFirstRowRightStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        return cellStyle;
    }

    // 底部边框MEDIUM
    public static XSSFCellStyle getBottomMEDIUMStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        return cellStyle;
    }

    // 第二行样式
    public static XSSFCellStyle getSecondRowStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    // SUM行样式
    public static XSSFCellStyle getSumStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getBold(wb, 12);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        //背景颜色
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        return cellStyle;
    }

    // SUM行样式
    public static XSSFCellStyle getSumFont10Style(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getBold(wb, 10);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        //背景颜色
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        return cellStyle;
    }

    // SUM行样式
    public static XSSFCellStyle getOrdinarySumStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getBold(wb, 12);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        return cellStyle;
    }

    // SUM行样式
    public static XSSFCellStyle getCenterSumStyleFont11(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getBold(wb, 11);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    // SUM行样式
    public static XSSFCellStyle getSumStyleFont11(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getBold(wb, 11);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //背景颜色
        //cellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        return cellStyle;
    }

    // 红色字体样式
    public static XSSFCellStyle getRedFontStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 11);
        font.setColor(Font.COLOR_RED);
        cellStyle.setFont(font);
        return cellStyle;
    }

    // 靠左显示
    public static XSSFCellStyle getLeftSpecialBottomStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getLeftStyle(wb);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        return cellStyle;
    }

    // 靠左显示
    public static XSSFCellStyle getRightSpecialBottomFron11Style(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getRightFont11Style(wb);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        return cellStyle;
    }

    // 靠左显示
    public static XSSFCellStyle getRightSpecialBottomStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getRightStyle(wb);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        return cellStyle;
    }

    /**
     * 该方法用来将具体的数据转换成Excel中的ABCD列
     *
     * @param columnIndex：需要转换成字母的数字
     * @return column:ABCD列名称
     **/
    public static String excelColIndexToStr(int columnIndex) {
        if (columnIndex <= 0) {
            return null;
        }
        String columnStr = "";
        columnIndex--;
        do {
            if (columnStr.length() > 0) {
                columnIndex--;
            }
            columnStr = ((char) (columnIndex % 26 + (int) 'A')) + columnStr;
            columnIndex = (int) ((columnIndex - columnIndex % 26) / 26);
        } while (columnIndex > 0);
        return columnStr;
    }

    public static XSSFCellStyle getCenterFont9MStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getLeftFont9MStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getNoBorderLeftFont9MStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getRightFont9MStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getNoBorderRightFont9MStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont11TextStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        cellStyle.setWrapText(false);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont9TextStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        cellStyle.setWrapText(false);
        return cellStyle;
    }

    public static XSSFCellStyle getLeftFont10MSPStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐ明朝");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont10MSPStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐ明朝");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getRightFont10MSPStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐ明朝");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getFont10MSPStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐ明朝");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont9MSPStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐ明朝");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        return cellStyle;
    }


    public static XSSFCellStyle getCenterFont9RedStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 9);
        font.setColor(Font.COLOR_RED);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont11ThinStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getLeftFont11ThinStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont11MSPStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont11RedStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 11);
        font.setColor(Font.COLOR_RED);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getRightFont11STStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont11STStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont11STStyleSXSSF(SXSSFWorkbook wb) {
        XSSFWorkbook xssfWorkbook = wb.getXSSFWorkbook();
        XSSFCellStyle cellStyle = getNormal(xssfWorkbook);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont10STStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont9STStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getLeftFont11STStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont9YellowStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getTopStyle(XSSFWorkbook wb, String fontName, short fontSize) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints(fontSize);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getBottomStyle(XSSFWorkbook wb, String fontName, short fontSize) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints(fontSize);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getLeftStyle(XSSFWorkbook wb, String fontName, short fontSize) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints(fontSize);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getRightStyle(XSSFWorkbook wb, String fontName, short fontSize) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints(fontSize);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getLeftHeavyStyle(XSSFWorkbook wb, String fontName, short fontSize) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THICK);
        Font font = wb.createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints(fontSize);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getLeftRightStyle(XSSFWorkbook wb, String fontName, short fontSize) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.DOTTED);
        cellStyle.setBorderBottom(BorderStyle.DOTTED);
        Font font = wb.createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints(fontSize);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont9NoBorderStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        cellStyle.setWrapText(false);
        return cellStyle;
    }

    //水平居中
    public static XSSFCellStyle getFont11Center(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = wb.createFont();
        font.setFontName("ＭＳ 明朝");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont11MSPStyleBottom(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont10MSPStyleCenter(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterFont10MSPStyleLeft(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = getNormal(wb);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static XSSFCellStyle getFont10MSPGoshikkuStyleRight(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.DOTTED);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        //cellStyle.setBorderRight(BorderStyle.THIN);
        Font font = wb.createFont();
        font.setFontName("ＭＳ Ｐゴシック");
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);
        return cellStyle;
    }

    //处理公式
    public static String getCellValueFormula(Cell cell, FormulaEvaluator formulaEvaluator) {
        if (cell == null || formulaEvaluator == null) {
            return null;
        }

        if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            return String.valueOf(formulaEvaluator.evaluate(cell).getNumberValue());
            //return formulaEvaluator.evaluate(cell).getStringValue();
        }
        return getCellValue(cell);
    }

    //未处理公式
    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString().trim();
            case Cell.CELL_TYPE_NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
//                if (DateUtil.isCellDateFormatted(cell)) {
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//非线程安全
//                    return sdf.format(cell.getDateCellValue());
//                } else {
//                    return String.valueOf(cell.getNumericCellValue());
//                }
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }

}

package com.haso.sysp.test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SxxfWorkbookApplicationTests2 {
    /**
     * 模板地址
     * POI SXSSFWorkbook 读取模板 存在公式解决
     */
    protected String tempAddress;
    /**
     * 模板结果集
     */
    protected String[] result;
    @Test
    public SXSSFWorkbook export(List<Map<String, Object>> data) throws Exception {
        long stime = System.currentTimeMillis();
        try {
            File fi = new File(tempAddress);
            FileInputStream is = new FileInputStream(fi);
            XSSFWorkbook wb = new XSSFWorkbook(is);
            //获取模板中最后一行，用于判断是否存在公式
            int lastRowNum = wb.getSheetAt(0).getLastRowNum();
            Sheet sheet0 = wb.getSheetAt(0);
            Row baseRow0=sheet0.getRow(2);
            lastRowNum = wb.getSheetAt(0).getLastRowNum();

            Map<Integer, String> gsMap=new HashMap<>();

            for (Iterator<Cell> it = baseRow0.cellIterator(); it.hasNext();) {
                Cell baseCell = it.next();
                if (baseCell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    String cellFormula = baseCell.getCellFormula();
                    gsMap.put(baseCell.getColumnIndex(), cellFormula);
                }
            }
            sheet0.removeRow(baseRow0); //取到公式后进行删除

            SXSSFWorkbook workbook = new SXSSFWorkbook(wb, 500);
            Sheet sheet = workbook.getSheetAt(0);

            CellStyle contextstyle = workbook.createCellStyle();
            DataFormat df = workbook.createDataFormat();
            contextstyle.setDataFormat(df.getFormat("#,##0.00"));

            final int startRow = lastRowNum;
            for (int i = startRow; i < data.size() + startRow; i++) {
                int rowNum = i - startRow;
                Row row = sheet.getRow(i);
                if (row == null) {
                    row = sheet.createRow(i);
                }
                Map<String, Object> dataMap = data.get(rowNum);

                String[] columNames = result;
                dataMap.put("serialNum", rowNum + 1);

                for (int j = 0; j < columNames.length; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        cell = row.createCell(j);
                    }
                    System.out.println(cell.getColumnIndex());
                    Object val = dataMap.get(columNames[j]);
//                    ExcelUtil.setCellValue(cell, val, contextstyle);
                    if(gsMap.get(cell.getColumnIndex())!=null){
                        String cellFormula =gsMap.get(cell.getColumnIndex());
                        String s = cellFormula.replaceAll("(\\w)\\d", "$1" + (i + 1));
                        cell.setCellFormula(s);
                        cell.setCellType(Cell.CELL_TYPE_FORMULA);
                    }
                }
                dataMap.clear();
                // 清空内存中缓存的行数
                if (i % 500 == 0) {
                    ((SXSSFSheet) sheet).flushRows();
                }
            }
            // 数据清理
            data.clear();
            data = null;
            workbook.setForceFormulaRecalculation(true);//计算公式
            long etime = System.currentTimeMillis();
            System.out.println("处理写入模板数据用时：" + (etime - stime) / 1000);
            return workbook;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

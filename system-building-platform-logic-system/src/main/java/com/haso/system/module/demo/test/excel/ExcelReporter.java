package com.haso.system.module.demo.test.excel;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by lzx on 2018/8/12
 */

public class ExcelReporter {

    // 模板路径
    private String fileLoaclPath = null;
    // 输入流
    private InputStream  inputStream = null;
    // 输入流
    private FileOutputStream outputStream = null;
    // 表格对象
    private Workbook wb = null;

    public ExcelReporter(){

    }
    public ExcelReporter(String fileLoaclPath){
        this.fileLoaclPath = fileLoaclPath;
    }

    public void setFileLoaclPath(String fileLoaclPath) {
        this.fileLoaclPath = fileLoaclPath;
    }

    /**
     *  初始化
     * @throws IOException
     */
    public void init() throws IOException {
        //inputStream =  ExcelReporter.class.getResourceAsStream("/model/" + this.fileLoaclPath);
        inputStream = new FileInputStream(new File(this.fileLoaclPath));
        if (this.fileLoaclPath.endsWith(".xlsx")){
            wb = new XSSFWorkbook(inputStream);
        }else if(this.fileLoaclPath.endsWith(".xls")){
            wb = new HSSFWorkbook(inputStream);
        }else {
            throw new RuntimeException("model file format is not valid , this : " + this.fileLoaclPath + " , eg:.xlsx or xls");
        }
    }

    /**
     *  导出excel表格
     * @param params
     * @param sheetName
     */
    public void export(List<Map<String,String>> params, String sheetName){
        this.insertValueToTable(wb,params,sheetName);
    }

    /**
     *  填充excel数据
     * @param wb
     * @param params
     * @param sheetName
     */
    private void insertValueToTable(Workbook wb,List<Map<String,String>> params,String sheetName) {
        // 获取模板中的sheet
        Sheet sheet = wb.getSheetAt(0);
        // 设置模板页的名称
        wb.setSheetName(0, sheetName);
        // 获得模板航
        Row tmpRow = sheet.getRow(1);
        // 获得非空白的行数
        int last = sheet.getLastRowNum();
        // 循环遍历填充数据
        for (int i = 0, len = params.size(); i < len; i++){
            // 获的开始填充的一行 就是模板的下一行
            int index = i+last+1;
            Map<String,String> map = params.get(i);
            // 创建新的一行
            Row row = sheet.getRow(index);
            if(row == null) {
                row = sheet.createRow(index);
            }
            // 循环便利模板行的列 获取${key}中的key
            for (int j = tmpRow.getFirstCellNum() ; j < tmpRow.getLastCellNum() ; j++){
                // 得到模板行的第j列单元格
                Cell tmpCell = tmpRow.getCell(j);
                // 获取key
                String key = tmpCell.getStringCellValue().replace("$", "").replace("{", "").replace("}", "");
                int columnindex = tmpCell.getColumnIndex();
                System.out.println(MessageFormat.format("这是第{0}行，第{1}列的key：{2}",index,columnindex,key));
                // 得到创建的一行的第j列单元格
                Cell c = row.getCell(j);
                if(c == null) {
                    c = row.createCell(columnindex);
                }
                // 填充单元格数据
                c.setCellValue(map.get(key));
            }
        }
        // 删除模板行
        sheet.shiftRows(2,5,-1);

    }
    /**
     *  收尾方法
     * @param outDocPath
     * @return
     * @throws IOException
     */
    public boolean generate(String outDocPath) throws IOException{
        outputStream = new FileOutputStream(new File(outDocPath));
        wb.write(outputStream);
        this.close(wb);
        this.close(inputStream);
        this.close(outputStream);
        return true;
    }

    /**
     *  关闭Workbook
     * @param wb
     */
    private void close(Workbook wb) {
        if (wb != null) {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     *  关闭输入流
     * @param is
     */
    private void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  关闭输出流
     * @param os
     */
    private void close(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

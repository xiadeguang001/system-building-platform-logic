package com.haso.sysp.test;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
//https://www.jianshu.com/p/1f6f10ab503c
public class SxxfWorkbookApplicationTests3 {

    public static void main(String[] args) throws Exception {
        String sheetName = "测试Excel格式";
        String sheetTitle = "测试Excel格式";
        List<String> columnNames = new LinkedList<>();
        columnNames.add("日期-String");
        columnNames.add("日期-Date");
        columnNames.add("时间戳-Long");
        columnNames.add("客户编码");
        columnNames.add("整数");
        columnNames.add("带小数的正数");

        ExportExcel2007 exportExcel2007 = new ExportExcel2007();

        exportExcel2007.writeExcelTitle("E:\\temp", "a", sheetName, columnNames, sheetTitle);

        for (int j = 0; j < 2; j++) {
            List<List<Object>> objects = new LinkedList<>();
            for (int i = 0; i < 1000; i++) {
                List<Object> dataA = new LinkedList<>();
                dataA.add("2016-09-05 17:27:25");
                dataA.add(new Date(1451036631012L));
                dataA.add(1451036631012L);
                dataA.add("000628");
                dataA.add(i);
                dataA.add(1.323 + i);
                objects.add(dataA);
            }
            try {
                exportExcel2007.writeExcelData("E:\\temp", "a", sheetName, objects);
            } catch (Exception e) {
                e.printStackTrace();
            }
            objects.clear();
        }

        exportExcel2007.dispose();

    }

}
package com.haso.system.module.demo.test.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzx on 2018/8/12
 */

public class ExcelExportDemo {
    public static void main(String[] args) throws IOException {
        // 添加假数据 这里你也可以从数据库里获取数据
        List<Map<String, String>> list = new ArrayList<>();
        for (int i =0;i < 4; i++){
            Map<String,String> map = new HashMap<>();
            map.put("id", i+"");
            map.put("name", "我在这"+i);
            map.put("car", "你好"+i);
            map.put("shop", "加班啊"+i);
            map.put("content", "123");
            list.add(map);
        }
        File dir= new File("C:/tmp");
        if(!dir.exists()){
            dir.mkdirs();
        }
        // 模板文件输入输出地址
        String filePath = "C:/tmp/demo.xlsx";
        String outPath = "C:/tmp/out.xlsx";
        // 开始导出
        ExcelReporter excelReporter = new ExcelReporter();
        excelReporter.setFileLoaclPath(filePath);
        excelReporter.init();
        excelReporter.export(list,"20180814");
        excelReporter.generate(outPath);

    }
}

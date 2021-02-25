package com.haso.system.module.demo.reader;//package com.haso.dataimport.module.demo.reader;
//
//import com.haso.dataimport.module.demo.view.View4WriterDemo;
//import com.haso.dataimport.module.system.service.ISysUserService;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.NonTransientResourceException;
//import org.springframework.batch.item.ParseException;
//import org.springframework.batch.item.UnexpectedInputException;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BatchItemReader implements ItemReader<View4WriterDemo> {
//
//    @Autowired
//    private ISysUserService sysUserService;
//
//    private int nextIndex;
//    private List<View4WriterDemo> lstData;
//
//    BatchItemReader(){
//        initialize();
//    }
//
//    private void initialize() {
//        lstData=new ArrayList<>();
//    }
//    @Override
//    public View4WriterDemo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//        View4WriterDemo nextView4WriterDemo = null;
//
//        if (nextIndex < lstData.size()) {
//            nextView4WriterDemo = lstData.get(nextIndex);
//            nextIndex++;
//        }
//
//        return nextView4WriterDemo;
//
//    }
//}

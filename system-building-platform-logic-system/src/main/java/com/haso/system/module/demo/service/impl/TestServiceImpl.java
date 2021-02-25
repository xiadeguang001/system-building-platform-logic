package com.haso.system.module.demo.service.impl;//package com.haso.dataimport.module.demo.service.impl;
//
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.haso.dataimport.module.demo.db2mapper.Test2Mapper;
//import com.haso.dataimport.module.demo.entity.Test1;
//import com.haso.dataimport.module.demo.entity.Test2;
//import com.haso.dataimport.module.demo.mapper.Test1Mapper;
//import com.haso.dataimport.module.demo.service.ITestService;
//import com.haso.dataimport.module.demo.view.View4Demo2_2;
//
//import org.quartz.DisallowConcurrentExecution;
//import org.quartz.JobExecutionContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameter;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.util.*;
//
//@DisallowConcurrentExecution
//@Service
//public class TestServiceImpl  extends ServiceImpl<Test1Mapper, Test1> implements ITestService{
//    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
//    @Resource
//    private Test1Mapper test1Mapper;
//    @Resource
//    private Test2Mapper test2Mapper;
//
//    @Autowired
//    PlatformTransactionManager transactionManager;
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private JobRepository jobRepository;
//
////    @Autowired
////    private SpringBatchHelloService springBatchHelloService;
//
//
//    //    @Autowired
////    private SqlSessionFactory sqlSessionFactory;
//    @Autowired
//    private Job singleJob;
//
//    //    @Transactional
//    @Override
//    public void doLogic( List<? extends View4Demo2_2> lstView4Demo2_2) {
//        for (View4Demo2_2 view4Demo2_2:lstView4Demo2_2) {
//            Test1   test1 =new Test1();
//            test1.setEname(view4Demo2_2.getEname());
//            test1Mapper.insert(test1);
//
//            Test2 test2 =new Test2();
//            test2.setBasicSalary(view4Demo2_2.getBasicSalary());
//            test2Mapper.insert(test2);
//
//        }
//
//
//
//    }
//
//    public void executeJob()   throws  Exception {
//        logger.error("SpringBatchHelloJob:executeJob");
//
////        StepBuilderFactory stepBuilderFactory = new StepBuilderFactory(jobRepository, transactionManager);
//        LinkedHashMap<String, JobParameter> parameterMap = new LinkedHashMap<String,JobParameter>();
//        JobParameter datetime = new JobParameter(new Date().getTime());
//        parameterMap.put("datetime", datetime);
//        JobParameters paramters = new JobParameters(parameterMap);
//        Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
////        runIdIncrementer.setKey("TestKey");
//
//        parameters.put("startDate", new JobParameter(new Date().getTime()));
//        parameters.put("endDate", new JobParameter(new Date().getTime()));
////        JobParameters jobParameters = runIdIncrementer.getNext(new JobParameters(parameters));
//
//
//        //流式编程
////        Step step = stepBuilderFactory.get("step1")
////                .tasklet((contribution, chunkContext) -> {
////                    System.out.println("执行步骤....");
////                    return RepeatStatus.FINISHED;
////                }).build();
//        JobBuilderFactory jobBuilderFactory = new JobBuilderFactory(jobRepository);
////        Job job = jobBuilderFactory.get("job1") .incrementer(new RunIdIncrementer()).start(step).build();
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addDate("date", new Date())
//                .toJobParameters();
////        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
////        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
////        TransactionStatus status = transactionManager.getTransaction(def);
////        try{
//        jobLauncher.run(singleJob,jobParameters);
//
////        transactionManager.commit(status);
////    } catch (Exception e) {
////        transactionManager.rollback(status);
////    }
////        springBatchHelloService.run();
//
//
//
//
//    }
//}

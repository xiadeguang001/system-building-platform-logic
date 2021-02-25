package com.haso.system.config.db;

import com.atomikos.icatch.config.UserTransactionServiceImp;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import java.util.Properties;

/***
 * 多数据源事务
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
public class MultiDatabaseConfig {
    @Bean(initMethod = "init", destroyMethod = "close")
    @DependsOn("atomikosUserTransactionService")
    public UserTransactionManager atomikosTransactionManager() {
        UserTransactionManager manager = new UserTransactionManager();
        manager.setForceShutdown(false);
        manager.setStartupTransactionService(false);
        return manager;
    }

    @Bean(initMethod = "init", destroyMethod = "shutdownForce")
    public UserTransactionServiceImp atomikosUserTransactionService() {
        Properties properties = new Properties();
        return new UserTransactionServiceImp(properties);
    }

    @Bean
    public UserTransactionImp atomikosUserTransaction() throws SystemException {
        UserTransactionImp transaction = new UserTransactionImp();
        transaction.setTransactionTimeout(300);
        return transaction;
    }

    @Primary
    @Bean
    public JtaTransactionManager jtaTransactionManager() throws Exception {
        JtaTransactionManager manager = new JtaTransactionManager();
        manager.setTransactionManager(atomikosTransactionManager());
        manager.setUserTransaction(atomikosUserTransaction());
        manager.setAllowCustomIsolationLevels(true);
        return manager;
    }

}

package com.haso.system.config.db;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

//import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
//import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
//import org.springframework.data.transaction.ChainedTransactionManager;
//import com.atomikos.jdbc.AtomikosDataSourceBean;

//@Configuration
@MapperScan(value={"com.haso.**.mapper.*Mapper"}, sqlSessionFactoryRef = "sqlSessionFactoryBean2")
public class DatabaseConfig2 {

//    @Bean
//    @Primary
//    @ConfigurationProperties("spring.datasource")
//    DataSource dataSource() {
//        DataSourceBuilder builder = DataSourceBuilder.create();
//        builder.type(HikariDataSource.class);
//        return builder.build();
//    }

	/*@Bean
	DataSourceInitializer springBatchDbInit(@Value("${spring-batch-db.init-script}") Resource initScript) {
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(cardDb());
		initializer.setEnabled(true);

		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(initScript);
		initializer.setDatabasePopulator(populator);

		ResourceDatabasePopulator cleaner = new ResourceDatabasePopulator();
		initializer.setDatabaseCleaner(cleaner);

		return initializer;
	}*/

    @Value("${spring.datasource.dynamic.datasource.second.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.dynamic.datasource.second.url}")
    private String url;
    @Value("${spring.datasource.dynamic.datasource.second..username}")
    private String username;
    @Value("${spring.datasource.dynamic.datasource.second.password}")
    private String password;
//    @Value("${spring.datasource.initialSize}")
//    private int initialSize;
//    @Value("${spring.datasource.minIdle}")
//    private int minIdle;
//    @Value("${spring.datasource.maxIdle}")
//    private int maxIdle;
//    @Value("${spring.datasource.maxActive}")
//    private int maxActive;
//    @Value("${spring.datasource.maxWait}")
//    private int maxWait;
//    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
//    private int timeBetweenEvictionRunsMillis;
//    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
//    private int minEvictableIdleTimeMillis;
//    @Value("${spring.datasource.validationQuery}")
//    private String validationQuery;
//    @Value("${spring.datasource.testWhileIdle}")
//    private boolean testWhileIdle;
//    @Value("${spring.datasource.testOnBorrow}")
//    private boolean testOnBorrow;
//    @Value("${spring.datasource.testOnReturn}")
//    private boolean testOnReturn;
//    @Value("${spring.datasource.poolPreparedStatements}")
//    private boolean poolPreparedStatements;
//    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
//    private int maxPoolPreparedStatementPerConnectionSize;
//    @Value("${spring.datasource.filters}")
//    private String filters;
//    @Value("${spring.datasource.maxpoolsize}")
//    private int maxpoolsize;

//    @Primary
    @Bean(name = "datasource2")
    @ConfigurationProperties("spring.datasource.dynamic.datasource.second")
//    @QuartzDataSource
//    @Qualifier("quartzSource")
    DataSource dataSource() {
//        DataSourceBuilder builder = DataSourceBuilder.create();
//        builder.type(HikariDataSource.class);
//        return builder.build();

        DruidXADataSource dataSource = new DruidXADataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(10);
        dataSource.setMinIdle(20);
        dataSource.setMaxIdle(50);
        dataSource.setMaxActive(50);
        dataSource.setMaxWait(60000);
        dataSource.setTransactionQueryTimeout(3600);
        dataSource.setQueryTimeout(3600);
//        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//        dataSource.setValidationQuery(validationQuery);
//        dataSource.setTestWhileIdle(testWhileIdle);
//        dataSource.setTestOnBorrow(testOnBorrow);
//        dataSource.setTestOnReturn(testOnReturn);
//        dataSource.setPoolPreparedStatements(poolPreparedStatements);
//        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        try {
            //dataSource.setFilters(filters);
            dataSource.init();
        } catch (SQLException e) {
            
        }
        AtomikosDataSourceBean stDataSource = new AtomikosDataSourceBean();
        stDataSource.setXaDataSource(dataSource);
        stDataSource.setMaxPoolSize(50);
        stDataSource.setPoolSize(20);
        stDataSource.setUniqueResourceName("datasource2");
//        return stDataSource;
//                DataSourceBuilder builder = DataSourceBuilder.create();
//        builder.type(HikariDataSource.class);
//        return builder.build();
        return stDataSource;
    }

//    @Bean(name="db1TransactionManager")
//    @Qualifier("db1TransactionManager")
//    PlatformTransactionManager db1TransactionManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }

    @Bean(name = "sqlSessionFactoryBean2")
    @Qualifier("sqlSessionFactoryBean2")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("datasource2") DataSource datasource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(datasource);

        // ページング
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        // properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        // properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        // プラグイン追加
        sqlSessionFactoryBean.setPlugins(new Interceptor[] {pageHelper});

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:com/haso/dacs/module/*/db2mapper/xml/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

//    /**
//     * 注入sqlSession对象
//     * @param sqlSessionFactory
//     * @return
//     */
//    @Bean(value = "sqlSessionTemplate2")
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryBean2") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.SIMPLE);
//    }

//    @Bean
//    DataSourceInitializer cardDbInit(@Value("${card-db.init-script}") Resource initScript) {
//        DataSourceInitializer initializer = new DataSourceInitializer();
//        initializer.setDataSource(dataSource());
//        initializer.setEnabled(true);
//
//        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//        populator.addScript(initScript);
//        populator.setSeparator("//");
//        initializer.setDatabasePopulator(populator);
//
//        ResourceDatabasePopulator cleaner = new ResourceDatabasePopulator();
//        initializer.setDatabaseCleaner(cleaner);
//
//        return initializer;
//    }

//    @Bean
//    @ConfigurationProperties("spring.db2.datasource")
//    DataSource accountDb() {
//        DataSourceBuilder builder = DataSourceBuilder.create();
//        builder.type(HikariDataSource.class);
//        return builder.build();
//    }
//
////    @Bean
////    DataSourceInitializer accountDbInit(@Value("${account-db.init-script}") Resource initScript) {
////        DataSourceInitializer initializer = new DataSourceInitializer();
////        initializer.setDataSource(accountDb());
////        initializer.setEnabled(true);
////
////        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
////        populator.addScript(initScript);
////        initializer.setDatabasePopulator(populator);
////
////        ResourceDatabasePopulator cleaner = new ResourceDatabasePopulator();
////        initializer.setDatabaseCleaner(cleaner);
////
////        return initializer;
////    }
//
//    @Bean
//    PlatformTransactionManager db1TransactionManager(@Qualifier("datasource") DataSource datasource) {
//        return new DataSourceTransactionManager(datasource);
//        //return new JtaTransactionManager();
//    }
//
////    @Bean(name = "sqlSessionFactoryBean2")
////    @Qualifier("sqlSessionFactoryBean2")
////    public SqlSessionFactory sqlSessionFactoryBean2() throws Exception {
////
////        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
////        sqlSessionFactoryBean.setDataSource(accountDb());
////
////        // ページング
////        PageHelper pageHelper = new PageHelper();
////        Properties properties = new Properties();
////        properties.setProperty("reasonable", "true");
////        // properties.setProperty("supportMethodsArguments", "true");
////        properties.setProperty("returnPageInfo", "check");
////        // properties.setProperty("params", "count=countSql");
////        pageHelper.setProperties(properties);
////
////        // プラグイン追加
////        sqlSessionFactoryBean.setPlugins(new Interceptor[] {pageHelper});
////
////        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
////        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:com/haso/batch/db2mapper/*.xml"));
////        return sqlSessionFactoryBean.getObject();
////    }
//
//    @Bean
//    PlatformTransactionManager chainTxManager() {
//        ChainedTransactionManager txManager = new ChainedTransactionManager(platformTransactionManager(),accountDbTxManager());
//        return txManager;
//    }
}

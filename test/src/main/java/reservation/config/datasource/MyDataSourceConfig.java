package reservation.config.datasource;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
@Configuration
@EnableTransactionManagement
@MapperScan(value = "reservation.dao", sqlSessionFactoryRef = "mySessionFactory")
@PropertySource("classpath:/jdbc.properties")
@Slf4j
public class MyDataSourceConfig {
    @Value("${reservation.db.classname}")
    private String className;

    @Value("${reservation.db.username}")
    private String userName;

    @Value("${reservation.db.password}")
    private String password;

    @Value("${reservation.db.url}")
    private String url;

    @Bean
    @ConfigurationProperties(prefix = "reservation.config.datasource")
    public HikariDataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(userName);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setConnectionTestQuery("SELECT 1");

        HikariDataSource hikariDataSource = new HikariDataSource(config);
        return  hikariDataSource;
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(className);
//        dataSource.setUsername(userName);
//        dataSource.setPassword(password);
//        dataSource.setUrl(url);
//        dataSource.setMaxTotal(200);
//        dataSource.setMaxIdle(200);
//        dataSource.setMinIdle(10);
//        dataSource.setInitialSize(10);
//        dataSource.setMaxWaitMillis(10000);
//
//        dataSource.setRemoveAbandonedOnBorrow(true);
//        dataSource.setRemoveAbandonedTimeout(60);
//        dataSource.setValidationQuery("SELECT 1");
//
//        dataSource.setTestOnBorrow(true);
//        dataSource.setTestWhileIdle(true);
//        dataSource.setMinEvictableIdleTimeMillis(1200000);
//        dataSource.setTimeBetweenEvictionRunsMillis(600000);
//        return dataSource;
    }
    @Bean(name = "mySessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory() {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("reservation.vo");
        sessionFactory.setTransactionFactory(new ManagedTransactionFactory());
        return sessionFactory;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(this.dataSource());
    }
}

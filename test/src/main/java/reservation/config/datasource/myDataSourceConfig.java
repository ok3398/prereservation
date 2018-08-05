package reservation.config.datasource;


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
public class myDataSourceConfig {
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
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(className);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setMaxTotal(200);
        dataSource.setMaxIdle(200);
        dataSource.setMinIdle(10);
        dataSource.setInitialSize(10);
        dataSource.setMaxWaitMillis(10000);

        dataSource.setRemoveAbandonedOnBorrow(true);
        dataSource.setRemoveAbandonedTimeout(60);
        dataSource.setValidationQuery("SELECT 1");

        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setMinEvictableIdleTimeMillis(1200000);
        dataSource.setTimeBetweenEvictionRunsMillis(600000);
        return dataSource;
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

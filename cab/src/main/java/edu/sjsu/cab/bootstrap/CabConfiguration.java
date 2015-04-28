package edu.sjsu.cab.bootstrap;

import java.beans.PropertyVetoException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import edu.sjsu.cab.storage.CabStorageService;
import edu.sjsu.cab.storage.CabStorageServiceImplementation;

@Configuration
@PropertySource(value = { "classpath:db.properties" })
@EnableTransactionManagement

public class CabConfiguration {

    @Autowired
    private Environment env;

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() throws Exception {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "edu.sjsu.cab.storage" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean(name = "dataSource")
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("jdbcDriver"));
        dataSource.setJdbcUrl(env.getProperty("localDbUrl"));
        dataSource.setUser(env.getProperty("localDbUser"));
//        dataSource.setPassword(env.getProperty("localDbPass"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        return properties;
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager transactionManager() throws Exception {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        txManager.setDataSource(dataSource());
        return txManager;
    }
    
    @Bean
    public CabStorageService cabStorageService() {
        return new CabStorageServiceImplementation();
    }

}

package edu.sjsu.cab.bootstrap;

import java.beans.PropertyVetoException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import edu.sjsu.cab.algorithm.AlgorithmProcessor;
import edu.sjsu.cab.storage.CabStorageService;
import edu.sjsu.cab.storage.CabStorageServiceImplementation;

@Configuration
@EnableTransactionManagement
public class CabConfiguration {


    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() throws Exception {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "edu.sjsu.cab.storage" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() throws PropertyVetoException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/cab");
        dataSource.setUsername("root");
        dataSource.setPassword("asdf");
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
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
    
    @Bean
    public AlgorithmProcessor algorithmProcessor() {
        return new AlgorithmProcessor();
    }

}

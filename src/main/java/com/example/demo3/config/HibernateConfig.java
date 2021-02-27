//package com.example.demo3.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@ComponentScan("com.example.demo3")
//@EnableTransactionManagement
//public class HibernateConfig {
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
//        entityManager.setDataSource(dataSource());
//        entityManager.setPackagesToScan("com.example.demo3");
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        entityManager.setJpaVendorAdapter(vendorAdapter);
//        entityManager.setJpaProperties(additionalProperties());
//        return entityManager;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC&useSSL=false");
//        dataSource.setUsername("root");
//        dataSource.setPassword("11111111");
//        return dataSource;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return transactionManager;
//    }
//
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
//
//    Properties additionalProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "validate");
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//        return properties;
//    }
//}
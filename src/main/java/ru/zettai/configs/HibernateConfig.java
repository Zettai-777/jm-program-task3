package ru.zettai.configs;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.Basic;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "ru.zettai.entities")
@PropertySource(value = "classpath:config.properties")

public class HibernateConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource getDataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(environment.getRequiredProperty("postgres.driverClassName"));
            dataSource.setJdbcUrl(environment.getRequiredProperty("postgres.url"));
            dataSource.setUser(environment.getRequiredProperty("postgres.username"));
            dataSource.setPassword(environment.getRequiredProperty("postgres.password"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

//    @Bean
//    public DataSource getDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClass("postgres.driverClassName");
//        dataSource.setJdbcUrl("postgres.url");
//        dataSource.setUser("postgres.username");
//        dataSource.setPassword("postgres.password");
//        return dataSource;
//    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(getDataSource());
        entityManagerFactoryBean.setPackagesToScan("ru.zettai.entities");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties hibernateProps = new Properties();
        hibernateProps.put("hibernate.dialect", environment.getRequiredProperty("dialect"));
        hibernateProps.put("hibernate.show_sql", environment.getRequiredProperty("showSql"));
        entityManagerFactoryBean.setJpaProperties(hibernateProps);
        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(getEntityManagerFactory().getObject());
        jpaTransactionManager.setDataSource(getDataSource());
        return jpaTransactionManager;
    }

//    @Bean
//    public LocalSessionFactoryBean getSessionFactory(){
//        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//        sessionFactoryBean.setDataSource(getDataSource());
//        sessionFactoryBean.setPackagesToScan("ru.zettai.entities");
//
//        Properties hibernateProps = new Properties();
//        hibernateProps.put("hibernate.dialect", environment.getRequiredProperty("dialect"));
//        hibernateProps.put("hibernate.show_sql", environment.getRequiredProperty("showSql"));
//        sessionFactoryBean.setHibernateProperties(hibernateProps);
//
//        return sessionFactoryBean;
//    }
//
//    @Bean
//    public HibernateTransactionManager transactionManager(){
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(getSessionFactory().getObject());
//        return transactionManager;
//    }
}

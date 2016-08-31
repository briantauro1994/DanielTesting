package com.covenant.app.config.root;

import org.h2.tools.Server;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.covenant.app.model.CDCustomNamingStrategy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 *
 * Development specific configuration - creates a localhost mysql datasource,
 * sets hibernate on create drop mode and inserts some test data on the database.
 *
 * Set -Dspring.profiles.active=development to activate this config.
 *
 */
@Configuration
@ComponentScan({ "com.covenant.app.dao","com.covenant.app.services"})
@EnableTransactionManagement
public class DatabaseTestConfig {



		

	    @Bean(name = "tdatasource")
	    public DriverManagerDataSource dataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();       
	        dataSource.setDriverClassName("org.h2.Driver");      
	        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
	        dataSource.setUsername("sa");
	        dataSource.setPassword("");
	        return dataSource;
	    }
	    
	   

	    @Bean(name = "entityManagerFactory")
	    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DriverManagerDataSource dataSource) {

	        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	        entityManagerFactoryBean.setDataSource(dataSource);
	        entityManagerFactoryBean.setPackagesToScan(new String[]{"com.covenant.app.model"});
	        entityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
	        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	      

	        Map<String, Object> jpaProperties = new HashMap<String, Object>();
	        jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
	        jpaProperties.put("hibernate.show_sql", "true");
	        jpaProperties.put("hibernate.format_sql", "true");
	        jpaProperties.put("hibernate.use_sql_comments", "true");
	        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	    
	        entityManagerFactoryBean.setJpaPropertyMap(jpaProperties);

	        return entityManagerFactoryBean;
	    }
	    @Bean(name = "transactionManager")
	    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory,
	                                                         DriverManagerDataSource dataSource) {
	        JpaTransactionManager transactionManager = new JpaTransactionManager();
	        transactionManager.setEntityManagerFactory(entityManagerFactory);
	        transactionManager.setDataSource(dataSource);
	        return transactionManager;
	    }

	}





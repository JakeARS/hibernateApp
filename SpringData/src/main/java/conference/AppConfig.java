package conference;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import java.sql.DriverManager;
import java.util.Properties;

@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public DriverManagerDataSource conferenceDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:file:D:/Programs/IdeaProjects/DataBases/MyDB_HSQLDB/MyDB;sql.syntax_pgs=true;shutdown=true");
        dataSource.setUsername("admin");
        dataSource.setPassword("admin");
        dataSource.setSchema("MYSCHEMA");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
        //EntityManager
        emFactory.setDataSource(conferenceDataSource());
        emFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        emFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        jpaProperties.setProperty("hibernate.hbm2dll.auto", "create");
        emFactory.setJpaProperties(jpaProperties);
        emFactory.setPackagesToScan("conference");
        return emFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}

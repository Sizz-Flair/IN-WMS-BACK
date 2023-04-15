package com.wms.inwms.config.dbconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.wms.inwms.domain",
        entityManagerFactoryRef = "mainEntityFactory",
        transactionManagerRef = "mainTransactionManager"
)
public class MainDBConfig {

    @Bean(name = "maindb")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource-internal")
    public DataSource mainDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mainEntityFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean mainEntityFactory(EntityManagerFactoryBuilder builder, Environment env) {

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("internal.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getRequiredProperty("internal.hibernate.dialect"));

        return builder
                .dataSource(mainDataSource())
                .packages("com.wms.inwms.domain")
                .persistenceUnit("internal")
                .properties(properties)
                .build();
    }

    @Bean(name = "mainTransactionManager")
    @Primary
    public PlatformTransactionManager mainTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(mainDataSource());
        jpaTransactionManager.setPersistenceUnitName("internal");
        return jpaTransactionManager;
    }
}

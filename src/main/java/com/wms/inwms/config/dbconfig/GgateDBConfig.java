package com.wms.inwms.config.dbconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(value="com.wms.inwms.domain.mapper.ggate", sqlSessionFactoryRef = "ggateSqlSessionFactory")
public class GgateDBConfig {
    @Bean(name="ggateDataSource")
    @ConfigurationProperties(prefix = "spring.datasource-gate")
    public DataSource ggateDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="ggateSqlSessionFactory")
    public SqlSessionFactory ggateSqlSessionFactory(@Qualifier("ggateDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        Resource[] mapperLocation = new Resource[1];
        mapperLocation[0] = new ClassPathResource("com/wms/inwms/domain/mapper/ggate/GgateMapper.xml");
        sqlSessionFactoryBean.setMapperLocations(mapperLocation);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "ggateSessiontemplate")
    public SqlSessionTemplate ggateSessiontemplate(@Qualifier("ggateSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

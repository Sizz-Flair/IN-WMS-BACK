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
@MapperScan(value="com.wms.inwms.domain.mapper.cj", sqlSessionFactoryRef = "cjSqlSessionFactory")
public class CJDBConfig {

    @Bean(name = "cjDataSource")
    @ConfigurationProperties(prefix = "spring.datasource-cj")
    public DataSource cjDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "cjSqlSessionFactory")
    public SqlSessionFactory cjSqlSessionFactory(@Qualifier("cjDataSource") DataSource cjDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(cjDataSource);

        Resource[] mapperLocation = new Resource[1];
        mapperLocation[0] = new ClassPathResource("com/wms/inwms/domain/mapper/cj/CjMapper.xml");
        sqlSessionFactoryBean.setMapperLocations(mapperLocation);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "cjSqlSessiontemplate")
    public SqlSessionTemplate cjSqlSessiontemplate(@Qualifier("cjSqlSessionFactory") SqlSessionFactory cjSqlSessionFactory) {
        return new SqlSessionTemplate(cjSqlSessionFactory);
    }
}

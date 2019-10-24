package com.sample.app;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.sample.dao"})
public class DatabaseConfig {
    
    @Autowired
    GlobalPropertySource globalPropertySource;
    
    @Bean
    @Primary
    public DataSource customDataSource() {
        System.out.println("customDataSource 호출");
        
        return DataSourceBuilder
            .create()
            .url(globalPropertySource.getUrl())
            .driverClassName(globalPropertySource.getDriverClassName())
            .username(globalPropertySource.getUsername())
            .password(globalPropertySource.getPassword())
            .build();
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource source) throws Exception {
        System.out.println("sqlSessionFactory 호출");
        
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(source);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:/mapper/**/*.xml"));
        return sessionFactory.getObject();
    }
    
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        System.out.println("sqlSessionTemplate 호출");
        
      final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
      return sqlSessionTemplate;
    }

}

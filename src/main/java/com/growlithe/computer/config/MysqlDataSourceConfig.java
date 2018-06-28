package com.growlithe.computer.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Author : Growlithe
 * @Date : 2018/5/18 21:31
 * @Description 配置文件
 */
@Configuration
@MapperScan(basePackages = {"com.growlithe.computer.mysql.**.**.dao.mapper"},
        sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class MysqlDataSourceConfig {
    private static final String MAPPER_LOCATION = "classpath:com/growlithe/computer/mysql/**/**/dao/mapper/*.xml";

    @Bean(name = {"mysqlDataSource"})
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }(

    @Bean(name = {"mysqlTransactionManager"})
    public DataSourceTransactionManager mysqlTransactionManager(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = {"mysqlSqlSessionFactory"})
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource,
                                                    @Qualifier("mysqlPageHelper") PageHelper mysqlPageHelper) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        Interceptor[] plugins = new Interceptor[]{mysqlPageHelper};
        sessionFactory.setPlugins(plugins);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean
    public PageHelper mysqlPageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("dialect", "MYSQL");
        pageHelper.setProperties(p);
        return pageHelper;
    }

}

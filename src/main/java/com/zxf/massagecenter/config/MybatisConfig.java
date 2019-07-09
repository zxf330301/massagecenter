package com.zxf.massagecenter.config;

import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.zxf.massagecenter.mapper")
@Slf4j
public class MybatisConfig {

 /*   @Autowired
    private DataSource dataSource;

    @Autowired
    private MybatisProperties properties;

    @Autowired
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired(required = false)
    private Interceptor[] interceptors;

    @Autowired(required = false)
    private DatabaseIdProvider databaseIdProvider;
*/

    @Value("${mybatis-plus.mapper-locations}")
    private String mapperLocations;
    @Value("${mybatis-plus.type-aliases-package}")
    private String typeAliasesPackage;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 这里全部使用mybatis-autoconfigure 已经自动加载的资源。不手动指定
     * 配置文件和mybatis-boot的配置文件同步
     * @return
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactoryBean(@Qualifier(value = "globalConfig") GlobalConfiguration configuration) throws Exception{
        log.info("初始化SqlSessionFactory");
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean=new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Interceptor[] interceptor={new PaginationInterceptor()};
        sqlSessionFactoryBean.setPlugins(interceptor);
        ResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
            try{
                sqlSessionFactoryBean.setGlobalConfig(configuration);
                sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
                sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
                return sqlSessionFactoryBean.getObject();
            }catch (Exception e){
                e.printStackTrace();
            }
            return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "globalConfig")
    public GlobalConfiguration globalConfiguration(){
        log.info("初始化GlobalConfiguration");
        GlobalConfiguration configuration=new GlobalConfiguration();
        return configuration;
    }

}


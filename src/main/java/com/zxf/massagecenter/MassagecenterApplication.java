package com.zxf.massagecenter;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/***
 * 使用nacos作为配置中心
 */
@SpringBootApplication
//@NacosPropertySource(dataId = "massagecenter.properties", autoRefreshed = true)
@EnableCaching
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableSwagger2
public class MassagecenterApplication {

    public static void main(String[] args) {

        SpringApplication.run(MassagecenterApplication.class, args);
    }

}

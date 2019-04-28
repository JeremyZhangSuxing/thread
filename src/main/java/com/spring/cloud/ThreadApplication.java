/*
 * All rights Reserved, Designed By jere
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.spring.cloud;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author suxing.zhang
 * @since 2019/4/26
 */
@EnableEurekaServer
@SpringBootApplication
@MapperScan("com.spring.cloud.interfaces.mapper")
public class ThreadApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThreadApplication.class,args);
    }
}

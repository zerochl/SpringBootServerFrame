package com.zero.barrageserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

/**
 * 启动程序
 * Created by Silence on 2016/11/11.
 */
@Configuration
@ConfigurationProperties
@SpringBootApplication
@RestController
@EnableScheduling
@EnableAsync
@EnableCaching
@ServletComponentScan
public class Application  {
  public static void main(String[] args) {
    ConfigurableApplicationContext applicationContext =  SpringApplication.run(Application.class, args);
    ConfigurableEnvironment environment = applicationContext.getEnvironment();
    System.out.println("当前环境是:" + environment.getProperty("spring.profiles.active"));

  }

}

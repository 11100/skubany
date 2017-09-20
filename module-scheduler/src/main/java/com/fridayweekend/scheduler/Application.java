package com.fridayweekend.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

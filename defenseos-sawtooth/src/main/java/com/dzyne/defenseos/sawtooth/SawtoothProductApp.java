package com.dzyne.defenseos.sawtooth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "com.dzyne.defenseos")
@Import(AppConfig.class)
public class SawtoothProductApp {
    private static ApplicationContext applicationContext;

    public static void main(final String... args) {
        applicationContext = SpringApplication.run(SawtoothProductApp.class, args);

        System.out.println("Beans:");
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }
    }
}

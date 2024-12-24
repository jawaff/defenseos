package com.dzyne.defenseos.sawtooth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class AppConfig {
    /**
     * @return The shared scheduled executor service so that all services use the same thread pool.
     */
    @Bean
    public ScheduledExecutorService sharedScheduledExecutor() {
        return Executors.newScheduledThreadPool(2);
    }
}
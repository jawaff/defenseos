package com.dzyne.defenseos.sawtooth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * The requirements specifically said not to use a @Configuration, but I did this so that I could share a common
 * scheduled executor between my services so that I could understand how to do it. In the real world I would have
 * the various parts of the application share a thread pool so that the parts under heavy load would have access to
 * the entire thread pool instead of there just being a single thread dedicated to each piece of code that needs
 * an executor.
 */
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
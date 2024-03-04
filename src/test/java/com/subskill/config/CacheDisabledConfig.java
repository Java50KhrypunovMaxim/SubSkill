package com.subskill.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class CacheDisabledConfig {

    @Bean
    public CacheManager cacheManager() {
        return new NoOpCacheManager();
    }
}
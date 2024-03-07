//package com.subskill.config;
//
//import lombok.AllArgsConstructor;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cache.CacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
//import org.springframework.data.redis.connection.*;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
//import org.springframework.data.redis.serializer.RedisSerializer;
//
//@Configuration
//@Setter
//public class RedisConfig {
//
//    @Value("${spring.data.redis.host}")
//    private String host;
//    @Value("${spring.data.redis.password}")
//    private String password;
//
//    @Bean
//    @Primary
//    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(RedisConfiguration defaultRedisConfig) {
//        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
//                .useSsl().build();
//        return new LettuceConnectionFactory(defaultRedisConfig, clientConfig);
//    }
//
//    @Bean
//    public RedisConfiguration defaultRedisConfig() {
//        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
//        config.setHostName(host);
//        config.setPassword(RedisPassword.of(password));
//        return config;
//    }
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory(RedisProperties redisProperties) {
//        final var standaloneConfiguration = new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
//        return new LettuceConnectionFactory(standaloneConfiguration);
//    }
//
//    @Bean
//    @Primary
//    public CacheManager listCacheManager(RedisConnectionFactory redisConnectionFactory) {
//        return build(redisConnectionFactory, new Jackson2JsonRedisSerializer<>(Object.class));
//    }
//
//    @Bean
//    public CacheManager objectCacheManager(RedisConnectionFactory redisConnectionFactory) {
//        return build(redisConnectionFactory, RedisSerializer.json());
//    }
//
//    private CacheManager build(RedisConnectionFactory redisConnectionFactory, RedisSerializer<?> redisSerializer) {
//        final var serializer = SerializationPair.fromSerializer(redisSerializer);
//        return RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)
//                .cacheDefaults(RedisCacheConfiguration
//                        .defaultCacheConfig()
//                        .serializeValuesWith(serializer)
//                        .disableCachingNullValues())
//                .build();
//    }
//
//}
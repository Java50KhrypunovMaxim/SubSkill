package com.subskill.config;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
//import org.springframework.data.redis.serializer.RedisSerializer;
//
//@Configuration
//public class RedisConfiguration {
//
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
package cn.tzq0301.pos.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author TZQ
 * @Description Spring Boot configuration for Redis
 */
@SpringBootConfiguration
public class RedisConfig {
    // @Bean(name="redisTemplate")
    // public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    //     RedisTemplate<Object, Object> template = new RedisTemplate<>();
    //     template.setConnectionFactory(redisConnectionFactory);
    //
    //     RedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
    //     // value值的序列化采用Jackson2JsonRedisSerializer
    //     template.setValueSerializer(serializer);
    //     template.setHashValueSerializer(serializer);
    //     // key的序列化采用StringRedisSerializer
    //     template.setKeySerializer(new StringRedisSerializer());
    //     template.setHashKeySerializer(new StringRedisSerializer());
    //
    //     template.setConnectionFactory(redisConnectionFactory);
    //     return template;
    // }
}


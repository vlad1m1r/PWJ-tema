package zvn.tema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class config {

    @Bean
    JedisConnectionFactory JedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6379);
        config.setPassword("redis");
        return new JedisConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> t = new RedisTemplate<>();
        t.setConnectionFactory(JedisConnectionFactory());
        return t;
    }

    public enum BookRepoErr{
        ok,
        alreadyPresent,
        notPresent
    }

}

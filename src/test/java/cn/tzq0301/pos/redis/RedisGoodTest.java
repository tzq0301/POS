package cn.tzq0301.pos.redis;

import cn.tzq0301.pos.entity.Good;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;

/**
 * @author TZQ
 * @Description TODO
 */
@SpringBootTest
public class RedisGoodTest {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    void test() {
        redisTemplate.opsForValue().set("good:001", JSON.toJSONString(new Good("001", "Good1", "This is Good1", new BigDecimal("8.88"))));
    }
}

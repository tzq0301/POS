package cn.tzq0301.pos.redis;

import cn.tzq0301.pos.entity.Good;
import cn.tzq0301.pos.mapper.GoodMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author TZQ
 * @Description TODO
 */
@SpringBootTest
public class RedisGoodTest {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    GoodMapper goodMapper;

    @Disabled
    @Test
    void test() {
        redisTemplate.opsForValue().set("good:001", JSON.toJSONString(
                new Good("001", "Good1", "This is Good1", new BigDecimal("8.88"))));
    }

    @Disabled
    @Test
    void test2() {
        redisTemplate.opsForValue().set("good:002", JSON.toJSONString(
                new Good("002", "Good2", "This is Good2", new BigDecimal("66.6"))));
    }

    @Disabled
    @Test
    void test3() {
        redisTemplate.opsForList().rightPush("good", JSON.toJSONString(
                new Good("001", "Good1", "This is Good1", new BigDecimal("8.88"))));
        redisTemplate.opsForList().rightPush("good", JSON.toJSONString(
                new Good("002", "Good2", "This is Good2", new BigDecimal("66.6"))));
    }

    @Test
    void test4() {
        goodMapper.listAllGoods().forEach(System.out::println);
    }
}

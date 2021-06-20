package cn.tzq0301.pos.redis;

import cn.tzq0301.pos.entity.Good;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author TZQ
 * @Description TODO
 */
@SpringBootTest
class RedisTest {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Disabled
    @Test
    void setGood1() {
        final Good good = new Good("001", "1号", "111", new BigDecimal(11));
        redisTemplate.opsForValue().set("good:" + "001", JSON.toJSONString(good));
    }

    @Disabled
    @Test
    void setGood2() {
        final Good good = new Good("002", "2号", "222", new BigDecimal(22));
        redisTemplate.opsForValue().set("good:" + "002", JSON.toJSONString(good));
    }

    @Test
    void getGood1() {
        final String s = String.valueOf(redisTemplate.opsForValue().get("good:001"));
        final Good good = JSONObject.parseObject(s, Good.class);
        assertEquals("This is Good1", good.getDescription());
        assertEquals("001", good.getId());
        assertEquals("Good1", good.getName());
        assertEquals(new BigDecimal("8.88"), good.getPrice());
    }

    @Test
    void getGood2() {
        final Object o = redisTemplate.opsForValue().get("good:002");
        final Good good = JSONObject.parseObject(String.valueOf(o), Good.class);
        assertEquals("002", good.getId());
    }

    @Test
    void getGood3() {
        assertNull(redisTemplate.opsForValue().get("good:003"));
    }

    @Disabled
    @Test
    void setGood3() {
        final Good good = new Good("002", "2号", "222", new BigDecimal(22));
        redisTemplate.opsForValue().set("good:" + "002", JSON.toJSONString(good));
    }
}

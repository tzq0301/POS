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

/**
 * @author TZQ
 * @Description TODO
 */
@SpringBootTest
public class RedisTest {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Disabled
    @Test
    void set1() {
        final Good good = new Good("001", "1号", "111", new BigDecimal(11));
        redisTemplate.opsForValue().set("good:" + "001", JSON.toJSONString(good));
    }

    @Test
    void get1() {
        final String s = String.valueOf(redisTemplate.opsForValue().get("good:001"));
        System.out.println(s);
        System.out.println(JSONObject.parseObject(s, Good.class));
    }

    @Disabled
    @Test
    void set2() {
        final Good good = new Good("002", "2号", "222", new BigDecimal(22));
        redisTemplate.opsForValue().set("good:" + "002", JSON.toJSONString(good));
    }

    @Test
    void get2() {
        final Object o = redisTemplate.opsForValue().get("good:002");
        System.out.println(JSONObject.parseObject(String.valueOf(o), Good.class));
    }

    @Test
    void set3() {
        final Good good = new Good("002", "2号", "222", new BigDecimal(22));
        redisTemplate.opsForValue().set("good:" + "002", JSON.toJSONString(good));
    }
}

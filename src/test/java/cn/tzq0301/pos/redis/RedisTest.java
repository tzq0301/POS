package cn.tzq0301.pos.redis;

import cn.tzq0301.pos.entity.Good;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

    @Test
    void set() {
        final Good good = new Good("001", "1号", "111", new BigDecimal(11));
        redisTemplate.opsForValue().set("good:" + "001", JSON.toJSONString(good));
    }

    @Test
    void get() {
        final String s = redisTemplate.opsForValue().get("good:001");
        System.out.println(s);
        System.out.println(JSONObject.parseObject(s, Good.class));
    }

}

package cn.tzq0301.pos.mapper.impl;

import cn.tzq0301.pos.entity.Good;
import cn.tzq0301.pos.mapper.GoodMapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author TZQ
 * @Description TODO
 */
@Repository
public class GoodMapperImpl implements GoodMapper {
    private final RedisTemplate<String, String> redisTemplate;

    private static final String GOOD_PREFIX = "good:";

    public GoodMapperImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Good getGoodById(String id) {
        return JSONObject.parseObject(redisTemplate.opsForValue().get(GOOD_PREFIX + id), Good.class);
    }
}

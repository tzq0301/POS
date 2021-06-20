package cn.tzq0301.pos.mapper.impl;

import cn.tzq0301.pos.entity.Good;
import cn.tzq0301.pos.mapper.GoodMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author TZQ
 * @Description the implementation of GoodMapper
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
        return JSON.parseObject(redisTemplate.opsForValue().get(GOOD_PREFIX + id), Good.class);
    }

    @Override
    public List<Good> listAllGoods() {
        return Optional.ofNullable(redisTemplate.opsForList()
                .range("good", 0, Optional.ofNullable(
                        redisTemplate.opsForList().size("good")).orElse(0L)))
                .orElse(new ArrayList<>())
                .stream()
                .map(good -> JSON.parseObject(good, Good.class))
                .collect(Collectors.toList());
    }
}

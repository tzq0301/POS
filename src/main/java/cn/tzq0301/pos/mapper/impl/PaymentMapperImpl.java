package cn.tzq0301.pos.mapper.impl;

import cn.tzq0301.pos.entity.Payment;
import cn.tzq0301.pos.mapper.PaymentMapper;
import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author TZQ
 * @Description TODO
 */
@Repository
public class PaymentMapperImpl implements PaymentMapper {
    private final RedisTemplate<String, String> redisTemplate;

    private static final String PAYMENT_PREFIX = "payment:";
    private static final String PAYMENT_ID_IN_REDIS = "payment:id";

    public PaymentMapperImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void savePayment(Payment payment) {
        final Long paymentId = redisTemplate.opsForValue().increment(PAYMENT_ID_IN_REDIS);
        redisTemplate.opsForValue().set(PAYMENT_PREFIX + paymentId, JSON.toJSONString(payment));
    }
}

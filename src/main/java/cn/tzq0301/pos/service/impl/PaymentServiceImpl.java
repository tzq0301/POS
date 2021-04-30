package cn.tzq0301.pos.service.impl;

import cn.tzq0301.pos.entity.Good;
import cn.tzq0301.pos.entity.Payment;
import cn.tzq0301.pos.mapper.GoodMapper;
import cn.tzq0301.pos.service.PaymentService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author TZQ
 * @Description TODO
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public void addGood(Payment payment, Good good, int amount) {
        payment.addGood(good, amount);
    }

    @Override
    public void showServiceInfo(Payment payment) {
        payment.showServiceInfo();
    }

    @Override
    public BigDecimal getTotal(Payment payment) {
        return Optional.of(payment.getTotal()).orElse(new BigDecimal(0));
    }

    @Override
    public BigDecimal getChange(Payment payment, BigDecimal actual) {
        return Optional.of(actual.subtract(payment.getTotal())).orElse(new BigDecimal(0));
    }

    @Override
    public void finishPayment(Payment payment) {
        payment.finishPayment();
        // TODO: 使用数据库进行数据（Payment）持久化操作
    }
}

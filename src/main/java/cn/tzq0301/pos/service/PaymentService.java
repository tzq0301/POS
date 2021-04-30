package cn.tzq0301.pos.service;

import cn.tzq0301.pos.entity.Good;
import cn.tzq0301.pos.entity.Payment;

import java.math.BigDecimal;

public interface PaymentService {
    void addGood(Payment payment, Good good, int amount);

    void showServiceInfo(Payment payment);

    BigDecimal getTotal(Payment payment);

    BigDecimal getChange(Payment payment, BigDecimal actual);

    void finishPayment(Payment payment);
}

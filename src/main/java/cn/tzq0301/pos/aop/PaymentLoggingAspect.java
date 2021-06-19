package cn.tzq0301.pos.aop;

import cn.tzq0301.pos.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author TZQ
 * @Description TODO
 */
@Aspect
@Component
@Slf4j
public class PaymentLoggingAspect {
    @Pointcut("execution(public void " +
            "cn.tzq0301.pos.mapper.impl.PaymentMapperImpl.savePayment(cn.tzq0301.pos.entity.Payment))")
    public void logPayment() {}

    @Before("logPayment()")
    public void logPaymentBeforeDB(JoinPoint joinPoint) {
        final Object[] args = joinPoint.getArgs();
        Payment payment = (Payment) args[0];
        log.info("\n" + payment.getServiceInfo());
    }
}

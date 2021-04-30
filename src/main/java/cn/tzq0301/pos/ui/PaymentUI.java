package cn.tzq0301.pos.ui;

import cn.tzq0301.pos.entity.Good;
import cn.tzq0301.pos.entity.Payment;
import cn.tzq0301.pos.service.GoodService;
import cn.tzq0301.pos.service.PaymentService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author TZQ
 * @Description TODO
 */
@Component
public class PaymentUI {
    private final PaymentService paymentService;
    private final GoodService goodService;

    private static final String YES = "Y";
    private static final String NO = "N";
    private static final Pattern ALL_ARE_NUMBER = Pattern.compile("^\\d+$");

    public PaymentUI(PaymentService paymentService, GoodService goodService) {
        this.paymentService = paymentService;
        this.goodService = goodService;
    }

    public void init() {
        System.out.println("\n\n\n\n> 正在初始化（加载产品目录）……\n");
    }

    public boolean setup(Scanner in) {
        System.out.print("\n\n> 要开始一次新的销售吗？(Y/N)：");
        final String startNewPayment = in.next();
        if (YES.equalsIgnoreCase(startNewPayment)) {
            System.out.println();
            return true;
        } else if (NO.equalsIgnoreCase(startNewPayment)) {
            System.out.println();
            return false;
        } else {
            System.out.println("> 输入有误，系统结束\n");
            return false;
        }
    }

    public void tacklePayment(Scanner in) {
        Payment payment = new Payment();
        while (true) {
            System.out.print("> 继续加购商品吗？(Y/N)：");
            final String continuePurchase = in.next();
            if (YES.equalsIgnoreCase(continuePurchase)) {
                System.out.print("> 请输入ID：");
                final String id = in.next();
                // TODO: 检验商品ID是否合法
                System.out.print("> 请输入数量：");
                final String amount = in.next();
                if (!ALL_ARE_NUMBER.matcher(amount).find()) {
                    System.out.println("> 输入错误，加购商品失败！请重新进行操作！");
                    continue;
                }
                Good good = goodService.getGoodById(id);
                if (good == null) {
                    System.out.println("> 输入错误，加购商品失败！请重新进行操作！");
                    continue;
                }
                paymentService.addGood(payment, good, Integer.parseInt(amount));
                paymentService.showServiceInfo(payment);
            } else if (NO.equalsIgnoreCase(continuePurchase)) {
                final BigDecimal total = paymentService.getTotal(payment);
                System.out.println("> 应付额：" + total);
                System.out.print("> 实付额：");
                BigDecimal actual = in.nextBigDecimal();
                while (actual.compareTo(total) <= 0) {
                    System.out.print("输入错误！请重新输入！\n> 实付额：");
                    actual = in.nextBigDecimal();
                }
                System.out.println("> 找零：" + paymentService.getChange(payment, actual));

                paymentService.finishPayment(payment);
                return;
            } else {
                System.out.println("> 命令错误！请重新输入！");
            }
            System.out.println();
        }
    }
}

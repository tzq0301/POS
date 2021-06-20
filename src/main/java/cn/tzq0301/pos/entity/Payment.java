package cn.tzq0301.pos.entity;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TZQ
 * @Description 订单
 */
@Slf4j
public class Payment implements Serializable {
    private static final long serialVersionUID = -7720324870937655702L;

    private final Map<Good, Integer> goods;

    private static final String DOUBLE_SPACE = "&nbsp;&nbsp;";

    @Getter
    private BigDecimal total;

    public Payment() {
        goods = new HashMap<>();
        total = new BigDecimal("0");
    }

    public void addGood(Good good, int num) {
        goods.put(good, goods.getOrDefault(good, 0) + num);
        total = total.add(good.getPrice().multiply(new BigDecimal(num)));
    }

    public void removeGood(Good good, int num) {
        // FIXME
        // goods.remove(good);
        // total = total.subtract(good.getPrice());
    }

    public void showServiceInfo() {
        System.out.println("品名\t\t零售价\t\t数量\t\t金额");
        goods.forEach((k, v) ->
                System.out.println(k.getName() + "\t\t" + k.getPrice() + "\t\t"
                        + v + "\t\t" + k.getPrice().multiply(new BigDecimal(v))));
    }

    public String getServiceInfo() {
        StringBuilder stringBuilder = new StringBuilder("品名\t\t零售价\t\t数量\t\t金额\n");
        goods.forEach((k, v) -> stringBuilder
                .append(k.getName()).append("\t\t")
                .append(k.getPrice()).append("\t\t")
                .append(v).append("\t\t")
                .append(k.getPrice().multiply(new BigDecimal(v))).append("\n"));
        return stringBuilder.toString();
    }

    public String getServiceInfoHtml() {
        StringBuilder stringBuilder = new StringBuilder("<html>");
        goods.forEach((k, v) -> stringBuilder
                .append(k.getName()).append(DOUBLE_SPACE)
                .append(k.getPrice()).append(DOUBLE_SPACE)
                .append(v).append(DOUBLE_SPACE)
                .append(k.getPrice().multiply(new BigDecimal(v))).append("<br>"));
        stringBuilder.append("总价：").append(total.toString());
        return stringBuilder.append("</html>").toString();
    }

    public void finishPayment() {
        printReceipt();
    }

    public void printReceipt() {
        System.out.println("正在打印单据……");
    }

    public Map<Good, Integer> getGoods() {
        return Collections.unmodifiableMap(goods);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "goods=" + goods +
                ", total=" + total +
                '}';
    }
}

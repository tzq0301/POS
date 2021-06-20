package cn.tzq0301.pos.entity.adaptor;

import cn.tzq0301.pos.entity.Payment;

import java.math.BigDecimal;

/**
 * @author TZQ
 * @Description {@link cn.tzq0301.pos.entity.Payment} 的适配器
 */
public class PaymentPrinterAdapter implements PaymentPrinter {
    private final Payment payment;

    private static final String DOUBLE_SPACE = "&nbsp;&nbsp;";
    private static final String DOUBLE_SPACE_ONE_DOUBLE_SPACE = "&nbsp;&nbsp;-&nbsp;&nbsp;";

    public PaymentPrinterAdapter(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String printHtml() {
        StringBuilder stringBuilder = new StringBuilder("<html>");
        payment.getGoods().forEach((k, v) -> stringBuilder
                .append(k.getName()).append(DOUBLE_SPACE)
                .append(k.getPrice()).append(DOUBLE_SPACE)
                .append(v).append(DOUBLE_SPACE)
                .append(k.getPrice().multiply(new BigDecimal(v))).append("<br>"));
        stringBuilder.append("总价：").append(payment.getTotal().toString());
        return stringBuilder.append("</html>").toString();
    }

    @Override
    public String printPlainText() {
        StringBuilder stringBuilder = new StringBuilder("品名\t\t零售价\t\t数量\t\t金额\n");
        payment.getGoods().forEach((k, v) ->
                stringBuilder.append(k.getName()).append("\t\t")
                        .append(k.getPrice()).append("\t\t")
                        .append(v).append("\t\t")
                        .append(k.getPrice().multiply(new BigDecimal(v)))
                        .append("\n"));
        return stringBuilder.toString();
    }

    @Override
    public String printPlainTextInHtml() {
        StringBuilder stringBuilder = new StringBuilder("<html>");
        payment.getGoods().forEach((k, v) -> stringBuilder
                .append(k.getName()).append(DOUBLE_SPACE_ONE_DOUBLE_SPACE)
                .append(k.getPrice()).append(DOUBLE_SPACE_ONE_DOUBLE_SPACE)
                .append(v).append(DOUBLE_SPACE_ONE_DOUBLE_SPACE)
                .append(k.getPrice().multiply(new BigDecimal(v))).append("<br>"));
        stringBuilder.append("总价：").append(payment.getTotal().toString());
        return stringBuilder.append("</html>").toString();
    }
}

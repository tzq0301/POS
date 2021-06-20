package cn.tzq0301.pos.entity.adaptor;

/**
 * @author TZQ
 */
public interface PaymentPrinter {
    /**
     * 以 HTML 的形式打印订单信息
     * @return HTML 订单信息
     */
    String printHtml();

    /**
     * 以纯文本的形式打印订单信息
     * @return 纯文本订单信息
     */
    String printPlainText();

    /**
     * 在 HTML 中以纯文本的形式打印订单信息
     * @return HTML 中的纯文本订单信息
     */
    String printPlainTextInHtml();
}

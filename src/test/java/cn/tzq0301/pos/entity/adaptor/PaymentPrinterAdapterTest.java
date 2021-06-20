package cn.tzq0301.pos.entity.adaptor;

import cn.tzq0301.pos.entity.Payment;
import cn.tzq0301.pos.mapper.GoodMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PaymentPrinterAdapterTest {

    @Autowired
    GoodMapper goodMapper;

    @Test
    void printHtml() {
        final Payment payment = new Payment();
        payment.addGood(goodMapper.getGoodById("002"), 2);
        final PaymentPrinterAdapter adapter = new PaymentPrinterAdapter(payment);
        final String actual = adapter.printPlainText();
        final String expected = "品名\t\t零售价\t\t数量\t\t金额\n"
                + "Good2\t\t66.6\t\t2\t\t133.2\n";
        assertEquals(expected, actual);
    }

    @Disabled
    @Test
    void printPlainText() {
    }
}
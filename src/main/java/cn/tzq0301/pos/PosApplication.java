package cn.tzq0301.pos;

import cn.tzq0301.pos.ui.PaymentUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author TZQ
 */
@SpringBootApplication
public class PosApplication implements CommandLineRunner {
    private final PaymentUI paymentUI;

    @Autowired
    // public PosApplication(@Qualifier("paymentConsoleUI") PaymentUI paymentUi) {
    public PosApplication(@Qualifier("paymentGUI") PaymentUI paymentUi) {
        this.paymentUI = paymentUi;
    }

    public static void main(String[] args) {
        SpringApplication.run(PosApplication.class, args);
    }

    @Override
    public void run(String... args) {
        init();
        while (setup()) {
            tacklePayment();
        }
    }

    private void init() {
        paymentUI.init();
    }

    private boolean setup() {
        return paymentUI.setup();
    }

    private void tacklePayment() {
        paymentUI.tackle();
    }
}

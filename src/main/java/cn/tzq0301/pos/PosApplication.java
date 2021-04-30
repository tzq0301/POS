package cn.tzq0301.pos;

import cn.tzq0301.pos.ui.PaymentUI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class PosApplication implements CommandLineRunner {
    private final Scanner in = new Scanner(System.in);
    private final PaymentUI ui;

    public PosApplication(PaymentUI ui) {
        this.ui = ui;
    }

    public static void main(String[] args) {
        SpringApplication.run(PosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        init();
        while (setup()) {
            tacklePayment();
        }
    }

    private void init() {
        ui.init();
    }

    private boolean setup() {
        return ui.setup(in);
    }

    private void tacklePayment() {
        ui.tacklePayment(in);
    }
}

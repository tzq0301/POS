package cn.tzq0301.pos.ui.impl;

import cn.tzq0301.pos.entity.Good;
import cn.tzq0301.pos.entity.Payment;
import cn.tzq0301.pos.service.GoodService;
import cn.tzq0301.pos.service.PaymentService;
import cn.tzq0301.pos.ui.PaymentUI;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author TZQ
 * @Description TODO
 */
@Component("paymentGUI")
public class PaymentGUI implements PaymentUI {
    private final PaymentService paymentService;
    private final GoodService goodService;

    private final JFrame operationsFrame;
    private final JFrame paymentFrame;

    private final Semaphore semaphore;

    private Payment payment;

    public PaymentGUI(PaymentService paymentService, GoodService goodService) {
        this.paymentService = paymentService;
        this.goodService = goodService;

        this.semaphore = new Semaphore(2);

        operationsFrame = new JFrame("请选择操作");
        operationsFrame.setSize(300, 400);
        operationsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        paymentFrame = new JFrame("新建订单");
        paymentFrame.setSize(700, 300);
        paymentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel operationsPanel = new JPanel();
        operationsFrame.add(operationsPanel);

        JButton newPaymentButton = new JButton("新建订单");
        operationsPanel.add(newPaymentButton);
        newPaymentButton.setBounds(10, 80, 80, 25);
        newPaymentButton.addActionListener(event -> {
            operationsFrame.setVisible(false);
            paymentFrame.setVisible(true);
        });

        final List<Good> goods = goodService.listAllGoods();
        String[] goodStrings = new String[goods.size()];
        for (int i = 0; i < goods.size(); i++) {
            final Good good = goods.get(i);
            goodStrings[i] = good.getId() + " - " + good.getName() + " - " + good.getDescription();
        }
        JList<String> goodJList = new JList<>(goodStrings);
        JPanel listPanel = new JPanel();
        listPanel.add(goodJList);
        paymentFrame.add(listPanel, BorderLayout.WEST);

        JPanel operatePaymentPanel = new JPanel(new GridLayout(4, 2, 20, 10));

        JPanel idPanel = new JPanel();
        JLabel idLabel = new JLabel("商品ID：");
        idPanel.add(idLabel);
        JTextField idTextField = new JTextField();
        idTextField.setColumns(30); // 设置输入框（input）的宽度
        idPanel.add(idTextField);
        operatePaymentPanel.add(idPanel);

        JPanel numPanel = new JPanel();
        JLabel numLabel = new JLabel("数量：");
        numPanel.add(numLabel);
        JTextField numTextField = new JTextField();
        numTextField.setColumns(30); // 设置输入框（input）的宽度
        numPanel.add(numTextField);
        operatePaymentPanel.add(numPanel);

        JLabel paymentLabel = new JLabel();

        JPanel addPanel = new JPanel();
        JButton addButton = new JButton("添加");
        addButton.addActionListener(event -> {
            paymentService.addGood(payment,
                    goodService.getGoodById(idTextField.getText()),
                    Integer.parseInt(numTextField.getText()));
            idTextField.setText("");
            numTextField.setText("");
            paymentLabel.setText(payment.getServiceInfoHTML());
        });
        addPanel.add(addButton);
        JButton clearButton = new JButton("清空");
        clearButton.addActionListener(event -> {
            idTextField.setText("");
            numTextField.setText("");
        });
        addPanel.add(clearButton);
        operatePaymentPanel.add(addPanel);

        JPanel submitPanel = new JPanel();
        submitPanel.add(paymentLabel);
        JButton submitButton = new JButton("提交");
        submitButton.addActionListener(event -> {
            paymentService.finishPayment(payment);
            final String payInput = JOptionPane.showInputDialog(
                    "总价" + payment.getTotal() + "，支付金额为：");
            JOptionPane.showMessageDialog(null,
                    "总价：" + payment.getTotal() + "\n"
                            + "实付：" + payInput + "\n"
                            + "找零：" + new BigDecimal(payInput).subtract(payment.getTotal()));
            semaphore.release();
            paymentFrame.setVisible(false);
            operationsFrame.setVisible(true);
        });
        submitPanel.add(submitButton);
        operatePaymentPanel.add(submitPanel);

        paymentFrame.add(operatePaymentPanel, BorderLayout.EAST);
    }

    @Override
    public void init() {
        JOptionPane.showMessageDialog(null, "欢迎您使用POS系统！");
    }

    @Override
    public boolean setup() {
        payment = new Payment(); // 每次处理都新建一个Payment对象
        operationsFrame.setVisible(true);
        return true;
    }

    @Override
    public void tackle() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

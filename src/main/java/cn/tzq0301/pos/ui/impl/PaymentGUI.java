package cn.tzq0301.pos.ui.impl;

import cn.tzq0301.pos.entity.Good;
import cn.tzq0301.pos.entity.Payment;
import cn.tzq0301.pos.entity.adaptor.PaymentPrinter;
import cn.tzq0301.pos.entity.adaptor.PaymentPrinterAdapter;
import cn.tzq0301.pos.service.GoodService;
import cn.tzq0301.pos.service.PaymentService;
import cn.tzq0301.pos.ui.PaymentUI;
import cn.tzq0301.pos.ui.gui.GuiState;
import cn.tzq0301.pos.ui.gui.impl.GuiInitState;
import cn.tzq0301.pos.ui.gui.impl.GuiSetupState;
import cn.tzq0301.pos.ui.gui.impl.GuiTackleState;
import jdk.nashorn.internal.scripts.JO;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author TZQ
 * @Description GUI for payment
 */
@Component("paymentGUI")
public class PaymentGUI implements PaymentUI {
    private final PaymentService paymentService;
    private final GoodService goodService;

    private final JFrame operationsFrame;
    private final JFrame paymentFrame;

    private final Semaphore semaphore;

    private Payment payment;

    private GuiState guiState;
    private final GuiState initState;
    private final GuiState setupState;
    private final GuiState tackleState;

    public PaymentGUI(PaymentService paymentService, GoodService goodService) {
        this.paymentService = paymentService;
        this.goodService = goodService;

        operationsFrame = new JFrame("请选择操作");
        operationsFrame.setSize(300, 400);
        operationsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.semaphore = new Semaphore(2);
        this.initState = new GuiInitState();
        this.setupState = new GuiSetupState(operationsFrame);
        this.tackleState = new GuiTackleState(semaphore);

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
        JList<String> goodList = new JList<>(goodStrings);
        JPanel listPanel = new JPanel();
        listPanel.add(goodList);
        paymentFrame.add(listPanel, BorderLayout.WEST);

        JPanel operatePaymentPanel = new JPanel(new GridLayout(4, 2, 20, 10));

        JPanel idPanel = new JPanel();
        JLabel idLabel = new JLabel("商品ID：");
        idPanel.add(idLabel);
        JTextField idTextField = new JTextField();
        // 设置输入框（input）的宽度
        idTextField.setColumns(30);
        idPanel.add(idTextField);
        operatePaymentPanel.add(idPanel);

        JPanel numPanel = new JPanel();
        JLabel numLabel = new JLabel("数量：");
        numPanel.add(numLabel);
        JTextField numTextField = new JTextField();
        // 设置输入框（input）的宽度
        numTextField.setColumns(30);
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
            paymentLabel.setText(payment.getServiceInfoHtml());
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
            paymentLabel.setText("");
            Object[] options = {"HTML", "纯文本"};
            String option = String.valueOf(JOptionPane.showInputDialog(null,
                    "请选择订单样式", "打印订单", JOptionPane.PLAIN_MESSAGE,
                    null, options, "样式"));
            PaymentPrinter paymentPrinter = new PaymentPrinterAdapter(payment);
            switch (option) {
                case "HTML":
                    JOptionPane.showMessageDialog(null, paymentPrinter.printHtml());
                    break;
                case "纯文本":
                    JOptionPane.showMessageDialog(null, paymentPrinter.printPlainTextInHtml());
                    break;
                default:
                    JOptionPane.showMessageDialog(null, paymentPrinter.printPlainText());
                    break;
            }
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
        setState(initState);
        guiState.handle();
    }

    @Override
    public boolean setup() {
        // 每次处理都新建一个Payment对象
        payment = new Payment();
        setState(setupState);
        guiState.handle();
        return true;
    }

    @Override
    public void tackle() {
        setState(tackleState);
        guiState.handle();
    }

    private void setState(GuiState guiState) {
        this.guiState = guiState;
    }
}

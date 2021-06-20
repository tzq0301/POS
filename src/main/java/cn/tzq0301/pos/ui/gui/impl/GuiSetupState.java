package cn.tzq0301.pos.ui.gui.impl;

import cn.tzq0301.pos.entity.Payment;
import cn.tzq0301.pos.ui.gui.GuiState;

import javax.swing.*;

/**
 * @author TZQ
 * @Description GUI 建立环境状态
 */
public class GuiSetupState implements GuiState {
    private final JFrame operationsFrame;

    public GuiSetupState(JFrame operationsFrame) {
        this.operationsFrame = operationsFrame;
    }

    @Override
    public void handle() {
        operationsFrame.setVisible(true);
    }
}

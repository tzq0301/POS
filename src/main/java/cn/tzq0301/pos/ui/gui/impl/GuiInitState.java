package cn.tzq0301.pos.ui.gui.impl;

import cn.tzq0301.pos.ui.gui.GuiState;

import javax.swing.*;

/**
 * @author TZQ
 * @Description GUI 初始状态
 */
public class GuiInitState implements GuiState {
    @Override
    public void handle() {
        JOptionPane.showMessageDialog(null, "欢迎您使用POS系统！");
    }
}

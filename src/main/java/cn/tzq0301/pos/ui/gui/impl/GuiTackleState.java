package cn.tzq0301.pos.ui.gui.impl;

import cn.tzq0301.pos.ui.gui.GuiState;

import java.util.concurrent.Semaphore;

/**
 * @author TZQ
 * @Description GUI 订单处理状态
 */
public class GuiTackleState implements GuiState {
    private final Semaphore semaphore;

    public GuiTackleState(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void handle() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}

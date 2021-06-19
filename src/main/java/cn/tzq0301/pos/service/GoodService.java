package cn.tzq0301.pos.service;

import cn.tzq0301.pos.entity.Good;

import java.util.List;

public interface GoodService {
    Good getGoodById(String id);

    List<Good> listAllGoods();
}

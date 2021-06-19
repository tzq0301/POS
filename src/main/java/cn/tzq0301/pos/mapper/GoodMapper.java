package cn.tzq0301.pos.mapper;

import cn.tzq0301.pos.entity.Good;

import java.util.List;

public interface GoodMapper {
    Good getGoodById(String id);

    List<Good> listAllGoods();
}

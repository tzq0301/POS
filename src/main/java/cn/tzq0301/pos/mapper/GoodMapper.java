package cn.tzq0301.pos.mapper;

import cn.tzq0301.pos.entity.Good;

import java.util.List;

/**
 * @author TZQ
 */
public interface GoodMapper {
    /**
     * 根据 ID 获取 Good 实例
     * @param id 要获取的 Good 的 ID
     * @return Good
     */
    Good getGoodById(String id);

    /**
     * 获得所有的 Good 实例
     * @return 包含所有 Good 实例的 List
     */
    List<Good> listAllGoods();
}

package cn.tzq0301.pos.service.impl;

import cn.tzq0301.pos.entity.Good;
import cn.tzq0301.pos.mapper.GoodMapper;
import cn.tzq0301.pos.service.GoodService;
import org.springframework.stereotype.Service;

/**
 * @author TZQ
 * @Description TODO
 */
@Service
public class GoodServiceImpl implements GoodService {
    private final GoodMapper goodMapper;

    public GoodServiceImpl(GoodMapper goodMapper) {
        this.goodMapper = goodMapper;
    }

    @Override
    public Good getGoodById(String id) {
        return goodMapper.getGoodById(id);
    }
}

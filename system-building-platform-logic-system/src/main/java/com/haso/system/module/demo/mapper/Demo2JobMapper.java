package com.haso.system.module.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haso.system.module.demo.view.View4Demo2;

import java.util.List;

public interface Demo2JobMapper  extends BaseMapper<View4Demo2> {

    public List<View4Demo2> findAll();
}

package com.haso.system.module.demo.db2mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haso.system.module.demo.entity.Emp;

import java.util.List;

//@Mapper
public interface Emp2Mapper extends BaseMapper<Emp> {

    public List<Emp> findAll();
}

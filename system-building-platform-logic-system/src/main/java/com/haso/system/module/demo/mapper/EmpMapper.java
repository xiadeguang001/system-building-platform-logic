package com.haso.system.module.demo.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haso.system.module.demo.entity.Emp;

import java.util.List;


public interface EmpMapper  extends BaseMapper<Emp> {

    public List<Emp> findAll();
}

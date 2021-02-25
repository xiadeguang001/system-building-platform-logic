package com.haso.system.module.demo.db2mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haso.system.module.demo.entity.Bonus;

//@Mapper
public interface Bonus2Mapper extends BaseMapper<Bonus> {
//    public void insert(Bonus bonus);
    public void deleteBonusAll();
}

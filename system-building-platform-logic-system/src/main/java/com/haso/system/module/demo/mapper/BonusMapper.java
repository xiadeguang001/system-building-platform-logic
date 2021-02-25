package com.haso.system.module.demo.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haso.system.module.demo.entity.Bonus;


public interface BonusMapper  extends BaseMapper<Bonus> {
//    public void insert(Bonus bonus);
    public void deleteBonusAll();
}

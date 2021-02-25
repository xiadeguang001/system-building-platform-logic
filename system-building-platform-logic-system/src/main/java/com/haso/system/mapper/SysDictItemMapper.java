package com.haso.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haso.system.entity.SysDictItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 系统管理：字典表 Mapper 接口
 *
 * <pre>
 *
 * Ver.   变更/障害No.    修改日期      作成/变更者       修改内容
 * ------ ------------- ------------ --------------- -----------------------------
 * 1.0    #13914        2020-05-10   Fengbo.Ge       初期作成
 * </pre>
 */
@Mapper
public interface SysDictItemMapper extends BaseMapper<SysDictItem> {

    /**
     *  根据树形的ID取得对应的明细数据
     * @param mainId
     * @return List<SysDictItem>
     */
    @Select("SELECT * FROM sys_dict_item WHERE DICT_ID = #{mainId} order by sort_order asc, item_value asc")
    public List<SysDictItem> selectItemsByMainId(Integer mainId);
}

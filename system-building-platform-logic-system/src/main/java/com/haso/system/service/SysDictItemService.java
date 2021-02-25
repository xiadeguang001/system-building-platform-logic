package com.haso.system.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haso.system.entity.SysDictItem;
import com.haso.system.mapper.SysDictItemMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统管理：字典表 服务实现类
 *
 * <pre>
 *
 * Ver.   变更/障害No.    修改日期      作成/变更者       修改内容
 * ------ ------------- ------------ --------------- -----------------------------
 * 1.0    #13914        2020-05-10   Fengbo.Ge       初期作成
 * </pre>
 */
@Service
public class SysDictItemService extends ServiceImpl<SysDictItemMapper, SysDictItem> {

    /**
     * 字典列表Mapper
     */
    @Resource
    private SysDictItemMapper sysDictItemMapper;

    /**
     * 根据树形中选中的数据ID取得明细
     * dictTableCache采用redis缓存有效期10分钟
     *
     * @param mainId 父节点ID
     * @return List<SysDictItem>
     */
    public List<SysDictItem> selectItemsByMainId(Integer mainId) {
        return sysDictItemMapper.selectItemsByMainId(mainId);
    }
}

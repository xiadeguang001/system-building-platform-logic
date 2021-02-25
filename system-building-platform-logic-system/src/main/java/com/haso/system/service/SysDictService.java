package com.haso.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haso.common.constant.CacheConstant;
import com.haso.common.constant.CommonConstant;
import com.haso.common.system.vo.DictModel;
import com.haso.common.util.ConvertUtil;
import com.haso.system.entity.SysDict;
import com.haso.system.entity.SysDictItem;
import com.haso.system.mapper.SysDictItemMapper;
import com.haso.system.mapper.SysDictMapper;
import com.haso.system.model.DuplicateCheckVo;
import com.haso.system.model.TreeSelectModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
@Slf4j
public class SysDictService extends ServiceImpl<SysDictMapper, SysDict> {

    /**
     * 字典表Mapper
     */
    @Resource
    private SysDictMapper sysDictMapper;

    /**
     * 字典列表Mapper
     */
    @Resource
    private SysDictItemMapper sysDictItemMapper;

    /**
     * 通过查询指定code 获取字典
     *
     * @param code 字典code
     * @return 处理结果
     */
    @Cacheable(value = CacheConstant.SYS_DICT_CACHE, key = "#code")
    public List<DictModel> queryDictItemsByCode(String code) {
        log.info("无缓存dictCache的时候调用这里！");
        return sysDictMapper.queryDictItemsByCode(code);
    }

    /**
     * 通过查询指定code 获取字典值text
     *
     * @param code 字典code
     * @param key  字典key
     * @return 处理结果
     */
    @Cacheable(value = CacheConstant.SYS_DICT_CACHE, key = "#code+':'+#key")
    public String queryDictTextByKey(String code, String key) {
        log.info("无缓存dictText的时候调用这里！");
        return sysDictMapper.queryDictTextByKey(code, key);
    }

    /**
     * 通过查询指定table的 text code 获取字典
     * dictTableCache采用redis缓存有效期10分钟
     *
     * @param table 查询指定table
     * @param text  字典数据
     * @param code  字典code
     * @return 处理结果
     */
    public List<DictModel> queryTableDictItemsByCode(String table, String text, String code) {
        log.info("无缓存dictTableList的时候调用这里！");
        return sysDictMapper.queryTableDictItemsByCode(table, text, code);
    }

    /**
     * 通过查询指定table的 text code 获取字典
     *
     * @param table 查询指定table
     * @param text  字典数据
     * @param code  字典code
     * @return 处理结果
     */
    public List<DictModel> queryTableDictItemsByCodeAndFilter(String table, String text, String code, String filterSql) {
        log.info("无缓存dictTableList的时候调用这里！");
        return sysDictMapper.queryTableDictItemsByCodeAndFilter(table, text, code, filterSql);
    }

    /**
     * 通过查询指定table的 text code 获取字典值text
     * dictTableCache采用redis缓存有效期10分钟
     *
     * @param table 查询指定table
     * @param text  字典数据
     * @param code  字典code
     * @param key   字典key
     * @return 处理结果
     */
    @Cacheable(value = CacheConstant.SYS_DICT_TABLE_CACHE)
    public String queryTableDictTextByKey(String table, String text, String code, String key) {
        log.info("无缓存dictTable的时候调用这里！");
        return sysDictMapper.queryTableDictTextByKey(table, text, code, key);
    }

    /**
     * 通过查询指定table的 text code 获取字典，包含text和value
     * dictTableCache采用redis缓存有效期10分钟
     *
     * @param table    查询指定table
     * @param text     字典数据
     * @param code     字典code
     * @param keyArray 字典keyArray
     * @return 处理结果
     */
    @Cacheable(value = CacheConstant.SYS_DICT_TABLE_CACHE)
    public List<String> queryTableDictByKeys(String table, String text, String code, String[] keyArray) {
        List<DictModel> dicts = sysDictMapper.queryTableDictByKeys(table, text, code, keyArray);
        List<String> texts = new ArrayList<>(dicts.size());
        // 查询出来的顺序可能是乱的，需要排个序
        for (String key : keyArray) {
            for (DictModel dict : dicts) {
                if (key.equals(dict.getValue())) {
                    texts.add(dict.getText());
                    break;
                }
            }
        }
        return texts;
    }

    /**
     * 根据字典类型id删除关联表中其对应的数据
     *
     * @param sysDict 字典表对象
     * @return 处理结果
     */
    public boolean deleteByDictId(SysDict sysDict) {
        sysDict.setDelFlag(CommonConstant.DEL_FLAG_1);
        return this.updateById(sysDict);
    }

    /**
     * 添加一对多
     *
     * @param sysDict         字典表对象
     * @param sysDictItemList 字典明细表对象
     * @return 处理结果
     */
    @Transactional
    public void saveMain(SysDict sysDict, List<SysDictItem> sysDictItemList) {

        sysDictMapper.insert(sysDict);
        if (sysDictItemList != null) {
            for (SysDictItem entity : sysDictItemList) {
                entity.setDictId(sysDict.getId());
                sysDictItemMapper.insert(entity);
            }
        }
    }

    /**
     * 查询所有部门 作为字典信息 id -->value,departName -->text
     *
     * @return 处理结果
     */
    public List<DictModel> queryAllDepartBackDictModel() {
        return baseMapper.queryAllDepartBackDictModel();
    }

    /**
     * 查询所有用户  作为字典信息 username -->value,realname -->text
     *
     * @return 处理结果
     */
    public List<DictModel> queryAllUserBackDictModel() {
        return baseMapper.queryAllUserBackDictModel();
    }

    /**
     * SQL注入校验（查询条件SQL 特殊check，此方法仅供此处使用）
     *
     * @param table   查询指定table
     * @param text    字典数据
     * @param code    字典code
     * @param keyword 字典keyword
     * @return 处理结果
     */
    public List<DictModel> queryTableDictItems(String table, String text, String code, String keyword) {
        return baseMapper.queryTableDictItems(table, text, code, "%" + keyword + "%");
    }

    /**
     * 根据表名——显示字段-存储字段 pid 加载树形数据
     *
     * @param table         查询指定table
     * @param text          字典数据
     * @param code          字典code
     * @param pidField      树形数据的字段
     * @param pid           树形数据的id
     * @param hasChildField 子菜单字段
     * @return 处理结果
     */
    public List<TreeSelectModel> queryTreeList(Map<String, String> query, String table, String text, String code, String pidField, String pid, String hasChildField) {
        return baseMapper.queryTreeList(query, table, text, code, pidField, pid, hasChildField);
    }

    /**
     * 物理删除
     *
     * @param id 选择行ID
     * @return 处理结果
     */
    public void deleteOneDictPhysically(String id) {
        this.baseMapper.deleteOneById(ConvertUtil.getInteger(id));
        this.sysDictItemMapper.delete(new LambdaQueryWrapper<SysDictItem>().eq(SysDictItem::getDictId, ConvertUtil.getInteger(id)));
    }

    /**
     * 把已删除的数据重新取回
     *
     * @param delFlag 删除标记
     * @param id      处理结果
     * @return 处理结果
     */
    public void updateDictDelFlag(int delFlag, Integer id) {
        baseMapper.updateDictDelFlag(delFlag, id);
    }

    /**
     * 查询被删除的数据
     *
     * @return 处理结果
     */
    public List<SysDict> queryDeleteList() {
        return baseMapper.queryDeleteList();
    }

    /**
     * 重复检查SQL
     * @param duplicateCheckVo
     * @return
     */
    public Long duplicateCheckCountSql(DuplicateCheckVo duplicateCheckVo) {
        return sysDictMapper.duplicateCheckCountSql(duplicateCheckVo);
    }

    /**
     * 重复检查SQL2
     * @param duplicateCheckVo
     * @return
     */
    public Long duplicateCheckCountSqlNoDataId(DuplicateCheckVo duplicateCheckVo) {
        return sysDictMapper.duplicateCheckCountSqlNoDataId(duplicateCheckVo);
    }
}

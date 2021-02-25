package com.haso.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haso.common.system.vo.DictModel;
import com.haso.system.entity.SysDict;
import com.haso.system.model.DuplicateCheckVo;
import com.haso.system.model.TreeSelectModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

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

public interface SysDictMapper extends BaseMapper<SysDict> {

    /**
     *  重复检查SQL
     * @return Long
     */
    public Long duplicateCheckCountSql(DuplicateCheckVo duplicateCheckVo);
    public Long duplicateCheckCountSqlNoDataId(DuplicateCheckVo duplicateCheckVo);

    /**
     * 通过查询指定code 获取字典
     *
     * @param code 字典code
     * @return 查询结果
     */
    public List<DictModel> queryDictItemsByCode(@Param("code") String code);

    /**
     * 通过查询指定table的 text code 获取字典
     * dictTableCache采用redis缓存有效期10分钟
     *
     * @param table 查询指定table
     * @param text 字典数据
     * @param code 字典code
     * @return 查询结果
     */
    public List<DictModel> queryTableDictItemsByCode(@Param("table") String table,@Param("text") String text,@Param("code") String code);

    /**
     * 通过查询指定table的 text code 获取字典
     * dictTableCache采用redis缓存有效期10分钟
     *
     * @param table 查询指定table
     * @param text 字典数据
     * @param code 字典code
     * @return 查询结果
     */
    public List<DictModel> queryTableDictItemsByCodeAndFilter(@Param("table") String table,@Param("text") String text,@Param("code") String code,@Param("filterSql") String filterSql);

    /**
     * 通过查询指定code 获取字典值text
     *
     * @param code 字典code
     * @param key 字典key
     * @return 查询结果
     */
    public String queryDictTextByKey(@Param("code") String code,@Param("key") String key);

    /**
     * 通过查询指定table的 text code 获取字典值text
     * dictTableCache采用redis缓存有效期10分钟
     *
     * @param table 查询指定table
     * @param text 字典数据
     * @param code 字典code
     * @param key 字典key
     * @return 查询结果
     */
    public String queryTableDictTextByKey(@Param("table") String table,@Param("text") String text,@Param("code") String code,@Param("key") String key);

    /**
     * 通过查询指定table的 text code 获取字典，包含text和value
     *
     * dictTableCache采用redis缓存有效期10分钟
     * @param table 查询指定table
     * @param text 字典数据
     * @param code 字典code
     * @param keyArray 字典keyArray
     * @return 查询结果
     */
    public List<DictModel> queryTableDictByKeys(@Param("table") String table, @Param("text") String text, @Param("code") String code, @Param("keyArray") String[] keyArray);

    /**
     * 查询所有部门 作为字典信息 id -->value,departName -->text
     * @return 查询结果
     */
    public List<DictModel> queryAllDepartBackDictModel();

    /**
     * 查询所有用户  作为字典信息 username -->value,realname -->text
     * @return 查询结果
     */
    public List<DictModel> queryAllUserBackDictModel();

    /**
     * 通过关键字查询出字典表
     *
     * @param table 查询指定table
     * @param text 字典数据
     * @param code 字典code
     * @param keyword 字典keyword
     * @return 查询结果
     */
    public List<DictModel> queryTableDictItems(@Param("table") String table,@Param("text") String text,@Param("code") String code,@Param("keyword") String keyword);

    /**
     * 根据表名、显示字段名、存储字段名 查询树
     *
     * @param table 查询指定table
     * @param text 字典数据
     * @param code 字典code
     * @param pidField 树形数据的字段
     * @param pid 树形数据的id
     * @param hasChildField 子菜单字段
     * @return 查询结果
     */
    List<TreeSelectModel> queryTreeList(@Param("query") Map<String, String> query, @Param("table") String table, @Param("text") String text, @Param("code") String code, @Param("pidField") String pidField, @Param("pid") String pid, @Param("hasChildField") String hasChildField);

    /**
     * 删除
     * @param id 选择行的ID
     */
    @Select("delete from sys_dict where id = #{id}")
    public void deleteOneById(@Param("id") Integer id);

    /**
     * 查询被逻辑删除的数据
     * @return 删除结果
     */
    @Select("select * from sys_dict where del_flag = 1")
    public List<SysDict> queryDeleteList();

    /**
     * 修改状态值
     * @param delFlag 1
     * @param id  选择行的ID
     */
    @Update("update sys_dict set del_flag = #{flag,jdbcType=INTEGER} where id = #{id,jdbcType=VARCHAR}")
    public void updateDictDelFlag(@Param("flag") int delFlag, @Param("id") Integer id);

}

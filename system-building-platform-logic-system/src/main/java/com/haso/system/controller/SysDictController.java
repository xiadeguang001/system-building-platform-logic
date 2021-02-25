    package com.haso.system.controller;

    import com.alibaba.fastjson.JSON;
    import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
    import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
    import com.baomidou.mybatisplus.core.metadata.IPage;
    import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
    import com.haso.common.api.vo.Result;
import com.haso.common.constant.CacheConstant;
import com.haso.common.constant.CommonConstant;
    import com.haso.common.constant.MessageConstant;
    import com.haso.common.system.QueryGenerator;
    import com.haso.common.system.vo.DictModel;
    import com.haso.common.util.ConvertUtil;
    import com.haso.common.util.MessageUtil;
    import com.haso.common.util.SqlInjectionUtil;
    import com.haso.system.entity.SysDict;
    import com.haso.system.model.SysDictTree;
    import com.haso.system.model.TreeSelectModel;
    import com.haso.system.service.SysDictItemService;
    import com.haso.system.service.SysDictService;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

    import javax.servlet.http.HttpServletRequest;
    import java.util.*;

//import org.springframework.data.redis.core.RedisTemplate;

/**
 * 系统管理：字典表 前端控制器。
 *
 * <pre>
 *
 * Ver.   变更/障害No.    修改日期      作成/变更者       修改内容
 * ------ ------------- ------------ --------------- -----------------------------
 * 1.0    #13914        2020-05-10   Fengbo.Ge       初期作成
 * </pre>
 */
@RestController
@RequestMapping("/sys/dict")
@Slf4j
public class SysDictController {

    /**
     * 字典表 服务类
     */
    @Autowired
    private SysDictService sysDictService;

    /**
     * 字典列表（字典配置）
     */
    @Autowired
    private SysDictItemService sysDictItemService;

    /**
     * Redis模板类（缓存处理用）
     */
    @Autowired
    public RedisTemplate<String, Object> redisTemplate;

    /**
     * 字典表数据查询
     *
     * @param sysDict  字典表对象
     * @param pageNo   当前页数
     * @param pageSize 一页显示件数
     * @param req      Http请求
     * @return 处理结果
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<IPage<SysDict>> queryPageList(SysDict sysDict, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Result<IPage<SysDict>> result = new Result<IPage<SysDict>>();
        QueryWrapper<SysDict> queryWrapper = QueryGenerator.initQueryWrapper(sysDict, req.getParameterMap());
        Page<SysDict> page = new Page<SysDict>(pageNo, pageSize);
        // 数据取得
        IPage<SysDict> pageList = sysDictService.page(page, queryWrapper);
        log.debug("查询当前页：" + pageList.getCurrent());
        log.debug("查询当前页数量：" + pageList.getSize());
        log.debug("查询结果数量：" + pageList.getRecords().size());
        log.debug("数据总数：" + pageList.getTotal());
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 获取树形字典数据
     *
     * @param sysDict  字典表对象
     * @param pageNo   当前页数
     * @param pageSize 一页显示件数
     * @param req      Http请求
     * @return 处理结果
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public Result<List<SysDictTree>> treeList(SysDict sysDict, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Result<List<SysDictTree>> result = new Result<>();
        LambdaQueryWrapper<SysDict> query = new LambdaQueryWrapper<>();
        // 构造查询条件
        String dictName = sysDict.getDictName();
        if (ConvertUtil.isNotEmpty(dictName)) {
            query.like(true, SysDict::getDictName, dictName);
        }
        query.orderByDesc(true, SysDict::getCreateTime);
        // 数据取得
        List<SysDict> list = sysDictService.list(query);
        List<SysDictTree> treeList = new ArrayList<>();
        for (SysDict node : list) {
            treeList.add(new SysDictTree(node));
        }
        result.setSuccess(true);
        result.setResult(treeList);
        return result;
    }

    /**
     * 获取字典数据
     *
     * @param dictCode 字典表配置信息
     *                 表名,文本字段,code字段  | 举例：sys_user,realname,id
     * @return 处理结果
     */
    @RequestMapping(value = "/getDictItems/{dictCode}", method = RequestMethod.GET)
    public Result<List<DictModel>> getDictItems(@PathVariable String dictCode) {
        log.info(" dictCode : " + dictCode);
        Result<List<DictModel>> result = new Result<List<DictModel>>();
        List<DictModel> ls = null;
        try {
            if (dictCode.contains(",")) {
                //关联表字典（举例：sys_user,realname,id）
                String[] params = dictCode.split(",");

                // 字典参数的check
                if (params.length < 3) {
                    // 字典参数格式不正确
                    result.error500(MessageUtil.getMsg(MessageConstant.DICT_PARAMETER_NOT_INCORRECT));
                    return result;
                }
                //SQL注入校验（只限制非法串改数据库）
                final String[] sqlInjCheck = {params[0], params[1], params[2]};
                SqlInjectionUtil.filterContent(sqlInjCheck);

                if (params.length == 4) {
                    //SQL注入校验（查询条件SQL 特殊check，此方法仅供此处使用）
                    SqlInjectionUtil.specialFilterContent(params[3]);
                    ls = sysDictService.queryTableDictItemsByCodeAndFilter(params[0], params[1], params[2], params[3]);
                } else if (params.length == 3) {
                    ls = sysDictService.queryTableDictItemsByCode(params[0], params[1], params[2]);
                } else {
                    // 字典参数格式不正确
                    result.error500(MessageUtil.getMsg(MessageConstant.DICT_PARAMETER_NOT_INCORRECT));
                    return result;
                }
            } else {
                //字典表
                ls = sysDictService.queryDictItemsByCode(dictCode);
            }

            result.setSuccess(true);
            result.setResult(ls);
            log.info(result.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            // 操作失败
            result.error500(MessageUtil.getMsg(MessageConstant.SAVE_FAIL));
            return result;
        }

        return result;
    }

    /**
     * 获取字典数据
     *
     * @param dictCode 字典表配置信息
     *                 表名,文本字段,code字段  | 举例：sys_user,realname,id
     * @param key      指定字典code
     * @return 处理结果
     */
    @RequestMapping(value = "/getDictText/{dictCode}/{key}", method = RequestMethod.GET)
    public Result<String> getDictItems(@PathVariable("dictCode") String dictCode, @PathVariable("key") String key) {
        log.info(" dictCode : " + dictCode);
        Result<String> result = new Result<String>();
        String text = null;
        try {
            text = sysDictService.queryDictTextByKey(dictCode, key);
            result.setSuccess(true);
            result.setResult(text);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            // 操作失败
            result.error500(MessageUtil.getMsg(MessageConstant.SAVE_FAIL));
            return result;
        }
        return result;
    }

    /**
     * 新增
     *
     * @param sysDict 字典表对象
     * @return 处理结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<SysDict> add(@RequestBody SysDict sysDict) {
        Result<SysDict> result = new Result<SysDict>();
        try {
            sysDict.setCreateTime(new Date());
            sysDict.setDelFlag(CommonConstant.DEL_FLAG_0);
            sysDictService.save(sysDict);
            // 保存成功
            result.success(MessageUtil.getMsg(MessageConstant.SAVE_SUCCESS));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            // 操作失败
            result.error500(MessageUtil.getMsg(MessageConstant.SAVE_FAIL));
        }
        return result;
    }

    /**
     * 编辑
     *
     * @param sysDict 字典表对象
     * @return 处理结果
     */
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public Result<SysDict> edit(@RequestBody SysDict sysDict) {
        Result<SysDict> result = new Result<SysDict>();
        SysDict sysdict = sysDictService.getById(sysDict.getId());
        if (sysdict == null) {
            // 此{0}数据不存在
            result.error500(MessageUtil.getMsg(MessageConstant.DATA_NOT_FIND, MessageUtil.getLab("dict")));
        } else {
            try {
                sysDict.setUpdateTime(new Date());
                // DB处理
                boolean ok = sysDictService.updateById(sysDict);
                if (ok) {
                    // 编辑成功
//                    result.success(MessageUtils.getMsg(MessageConstant.EDIT_SUCCESS));
                    result.success("修改成功!");
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                result.error500("操作失败");
//				result.error500(MessageUtils.getMsg(MessageConstant.SAVE_FAIL));
            }
        }
        return result;
    }

    /**
     * 删除
     *
     * @param id 删除行的id
     * @return 处理结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    //@CacheEvict(value = CacheConstant.SYS_DICT_CACHE, allEntries = true)
    public Result<SysDict> delete(@RequestParam(name = "id", required = true) String id) {
        Result<SysDict> result = new Result<SysDict>();
        // DB处理
        boolean ok = sysDictService.removeById(ConvertUtil.getInteger(id));
        if (ok) {
            // 删除成功
            result.success(MessageUtil.getMsg(MessageConstant.DELETE_SUCCESS));
        } else {
            // 删除失败
            result.error500(MessageUtil.getMsg(MessageConstant.DELETE_FAIL));
        }
        return result;
    }

    /**
     * 批量删除
     *
     * @param ids 删除行的id
     * @return 处理结果
     */
    @RequestMapping(value = "/deleteBatch", method = RequestMethod.DELETE)
    //@CacheEvict(value = CacheConstant.SYS_DICT_CACHE, allEntries = true)
    public Result<SysDict> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<SysDict> result = new Result<SysDict>();
        if (ConvertUtil.isEmpty(ids)) {
            // 参数不识别
            result.error500(MessageUtil.getMsg(MessageConstant.PARAMETER_NOT_INCORRECT));
        } else {
            // DB处理
            sysDictService.removeByIds(Arrays.asList(ids.split(",")));
            // 删除成功
            result.success(MessageUtil.getMsg(MessageConstant.DELETE_SUCCESS));
        }
        return result;
    }

    /**
     * 刷新缓存
     *
     * @return 处理结果
     */
    @RequestMapping(value = "/refleshCache")
    public Result<?> refleshCache() {
        Result<?> result = new Result<SysDict>();
        //清空字典缓存
        Set keys = redisTemplate.keys(CacheConstant.SYS_DICT_CACHE + "*");
        Set keys2 = redisTemplate.keys(CacheConstant.SYS_DICT_TABLE_CACHE + "*");
        redisTemplate.delete(keys);
        redisTemplate.delete(keys2);
        return result;
    }

    /**
     * 大数据量的字典表 走异步加载  即前端输入内容过滤数据
     *
     * @param dictCode 字典表配置信息
     *                 表名,文本字段,code字段  | 举例：sys_user,realname,id
     * @param keyword  字典指定key
     * @return 处理结果
     */
    @RequestMapping(value = "/loadDict/{dictCode}", method = RequestMethod.GET)
    public Result<List<DictModel>> loadDict(@PathVariable String dictCode, @RequestParam(name = "keyword") String keyword) {
        log.info(" 加载字典表数据,加载关键字: " + keyword);
        Result<List<DictModel>> result = new Result<List<DictModel>>();
        List<DictModel> ls = null;
        try {
            if (dictCode.indexOf(",") != -1) {
                String[] params = dictCode.split(",");
                if (params.length != 3) {
                    // 字典Code格式不正确
                    result.error500(MessageUtil.getMsg(MessageConstant.SAVE_FAIL, MessageUtil.getLab(dictCode)));
                    return result;
                }
                ls = sysDictService.queryTableDictItems(params[0], params[1], params[2], keyword);
                result.setSuccess(true);
                result.setResult(ls);
                log.info(result.toString());
            } else {
                // 字典Code格式不正确
                result.error500(MessageUtil.getMsg(MessageConstant.SAVE_FAIL, MessageUtil.getLab(dictCode)));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500(MessageUtil.getMsg(MessageConstant.SAVE_FAIL));
            return result;
        }

        return result;
    }

    /**
     * 根据字典code加载字典text 返回
     *
     * @param dictCode 字典表配置信息
     *                 表名,文本字段,code字段  | 举例：sys_user,realname,id
     * @param key      字典指定key
     * @return 处理结果
     */
    @RequestMapping(value = "/loadDictItem/{dictCode}", method = RequestMethod.GET)
    public Result<List<String>> loadDictItem(@PathVariable String dictCode, @RequestParam(name = "key") String key) {
        Result<List<String>> result = new Result<>();
        try {
            // 字典Code格式check
            if (dictCode.indexOf(",") != -1) {
                String[] params = dictCode.split(",");
                if (params.length != 3) {
                    // 字典Code格式不正确
                    result.error500(MessageUtil.getMsg(MessageConstant.SAVE_FAIL, MessageUtil.getLab(dictCode)));
                    return result;
                }
                // 根据字典Code取得名称
                List<String> texts = sysDictService.queryTableDictByKeys(params[0], params[1], params[2], key.split(","));

                result.setSuccess(true);
                result.setResult(texts);
                log.info(result.toString());
            } else {
                // 字典Code格式不正确
                result.error500(MessageUtil.getMsg(MessageConstant.SAVE_FAIL, MessageUtil.getLab(dictCode)));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            // 操作失败
            result.error500(MessageUtil.getMsg(MessageConstant.SAVE_FAIL));
            return result;
        }

        return result;
    }

    /**
     * 根据表名——显示字段-存储字段 pid 加载树形数据
     *
     * @param pid           树形数据的id
     * @param pidField      树形数据的字段
     * @param tbname        树形数据的表名
     * @param text          树形数据的text
     * @param code          树形数据的code
     * @param hasChildField 子菜单字段
     * @param condition     条件
     * @return 处理结果
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/loadTreeData", method = RequestMethod.GET)
    public Result<List<TreeSelectModel>> loadDict(@RequestParam(name = "pid") String pid, @RequestParam(name = "pidField") String pidField,
                                                  @RequestParam(name = "tableName") String tbname,
                                                  @RequestParam(name = "text") String text,
                                                  @RequestParam(name = "code") String code,
                                                  @RequestParam(name = "hasChildField") String hasChildField,
                                                  @RequestParam(name = "condition") String condition) {
        Result<List<TreeSelectModel>> result = new Result<List<TreeSelectModel>>();
        Map<String, String> query = null;
        if (ConvertUtil.isNotEmpty(condition)) {
            query = JSON.parseObject(condition, Map.class);
        }
        List<TreeSelectModel> ls = sysDictService.queryTreeList(query, tbname, text, code, pidField, pid, hasChildField);
        result.setSuccess(true);
        result.setResult(ls);
        return result;
    }


    /**
     * 查询被删除的列表
     *
     * @return 处理结果
     */
    @RequestMapping(value = "/deleteList", method = RequestMethod.GET)
    public Result<List<SysDict>> deleteList() {
        Result<List<SysDict>> result = new Result<List<SysDict>>();
        // 查询被删除的数据
        List<SysDict> list = this.sysDictService.queryDeleteList();
        result.setSuccess(true);
        result.setResult(list);
        return result;
    }

    /**
     * 物理删除
     *
     * @param id 选择行的id
     * @return 处理结果
     */
    @RequestMapping(value = "/deletePhysic/{id}", method = RequestMethod.DELETE)
    public Result<?> deletePhysic(@PathVariable String id) {
        try {
            sysDictService.deleteOneDictPhysically(id);
            // 删除成功
            return Result.ok(MessageUtil.getMsg(MessageConstant.DELETE_SUCCESS));
        } catch (Exception e) {
            
            // 删除失败
            return Result.error(MessageUtil.getMsg(MessageConstant.DELETE_FAIL));
        }
    }

    /**
     * 取回
     *
     * @param id 选择行的id
     * @return 处理结果
     */
    @RequestMapping(value = "/back/{id}", method = RequestMethod.PUT)
    public Result<?> back(@PathVariable String id) {
        try {
            // 把已删除的数据重新取回
            sysDictService.updateDictDelFlag(0, ConvertUtil.getInteger(id));
            // 操作成功
            return Result.ok(MessageUtil.getMsg(MessageConstant.OPRATE_SUCCESS));
        } catch (Exception e) {
            
            // 操作失败
            return Result.error(MessageUtil.getMsg(MessageConstant.SAVE_FAIL));
        }
    }

}

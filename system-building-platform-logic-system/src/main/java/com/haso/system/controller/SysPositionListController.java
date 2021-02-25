package com.haso.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haso.common.api.vo.Result;
import com.haso.common.constant.MessageConstant;
import com.haso.common.system.QueryGenerator;
import com.haso.common.util.MessageUtil;
import com.haso.system.entity.SysPosition;
import com.haso.system.service.SysPositionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 职位表对象
 *
 * <pre>
 *
 * Ver.   变更/障害No.    修改日期      作成/变更者       修改内容
 * ------ ------------- ------------ --------------- -----------------------------
 * 1.0    #13914         2020-05-13    hejunnan         初期作成
 * </pre>
 */
@RestController
@RequestMapping("/sys/position")
@Slf4j
public class SysPositionListController {

    /**
     * 职位表 服务类
     */
    @Autowired
    private SysPositionService iPositionService;
//    @Autowired
//    private IDataNumberControlService dataNumberControlService;

    /**
     * 职位查询
     *
     * @param sysPosition 职位表对象
     * @param pageNo      当前页数
     * @param pageSize    一页显示件数
     * @param req         Http请求
     * @return 处理结果
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<IPage<SysPosition>> queryPageList(SysPosition sysPosition,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        Result<IPage<SysPosition>> result = new Result<IPage<SysPosition>>();
        //查询条件语句
        QueryWrapper<SysPosition> queryWrapper = QueryGenerator.initQueryWrapper(sysPosition, req.getParameterMap());
        //分页
        Page<SysPosition> page = new Page<SysPosition>(pageNo, pageSize);
        //数据查询
        IPage<SysPosition> pageList = iPositionService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 职务添加数据
     *
     * @param sysPosition 职务表对象
     * @return 处理结果
     */
    @PostMapping(value = "/add")
    public Result<SysPosition> add(@RequestBody SysPosition sysPosition) {
        Result<SysPosition> result = new Result<SysPosition>();
        try {
            // 编号规则表更新
//            dataNumberControlService.getNewCode("sys_position", sysPosition.getCode());

            iPositionService.save(sysPosition);
            result.success(MessageUtil.getMsg(MessageConstant.SAVE_SUCCESS));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500(MessageUtil.getMsg(MessageConstant.SAVE_FAIL));
        }
        return result;
    }

    /**
     * 职务编辑
     *
     * @param sysPosition 职务表对象
     * @return 处理结果
     */
    @PutMapping(value = "/edit")
    public Result<SysPosition> edit(@RequestBody SysPosition sysPosition) {
        Result<SysPosition> result = new Result<SysPosition>();
        SysPosition PositionEntity = iPositionService.getById(sysPosition.getId());
        if (PositionEntity == null) {
            result.error500(MessageUtil.getMsg(MessageConstant.DATA_NOT_FIND, MessageUtil.getLab("standardUnitConversion")));
        } else {
            boolean ok = iPositionService.updateById(sysPosition);
            if (ok) {
                // 编辑成功
                result.success(MessageUtil.getMsg(MessageConstant.EDIT_SUCCESS));
            }
        }
        return result;
    }

    /**
     * 通过id删除
     *
     * @param id 职务ID
     * @return 处理结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result<SysPosition> delete(@RequestParam(name = "id", required = true) Integer id) {
        Result<SysPosition> result = new Result<SysPosition>();
        SysPosition PositionEntity = iPositionService.getById(id);
        if (PositionEntity == null) {
            result.error500(MessageUtil.getMsg(MessageConstant.DATA_NOT_FIND, MessageUtil.getLab("standardUnitConversion")));
        } else {
            boolean ok = iPositionService.removeById(id);
            if (ok) {
                // 删除成功
                result.success(MessageUtil.getMsg(MessageConstant.DELETE_SUCCESS));
            } else {
                // 删除失败
                result.error500(MessageUtil.getMsg(MessageConstant.DELETE_FAIL));
            }
        }

        return result;
    }

    /**
     * 批量删除
     *
     * @param ids 多个职务ID
     * @return 处理结果
     */
    @RequestMapping(value = "/deleteBatch", method = RequestMethod.DELETE)
    public Result<SysPosition> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<SysPosition> result = new Result<SysPosition>();
        List<Integer> list = new ArrayList<>();
        for (String a : ids.split(",")) {
            list.add(Integer.parseInt(a));
        }
        boolean ok = iPositionService.removeByIds(list);
        if (ok) {
            // 删除成功
            result.success(MessageUtil.getMsg(MessageConstant.DELETE_SUCCESS));
        } else {
            // 删除失败
            result.error500(MessageUtil.getMsg(MessageConstant.DELETE_FAIL));
        }
        return result;
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Result<Object> check(HttpServletRequest request) {
        Long num = null;
        //tableName   fieldName  fieldVal
        //判断是否为空
        if (StringUtils.isNotBlank(request.getParameter("dataId"))) {
            //true 不为空 编辑
            num = iPositionService.editDuplicateCheck(request.getParameter("tableName"),request.getParameter("fieldName"),request.getParameter("newFieldVal"),request.getParameter("oldFieldVal"));
        } else {
            //false 为空 新建
            num = iPositionService.addDuplicateCheck(request.getParameter("tableName"),request.getParameter("fieldName"),request.getParameter("newFieldVal"));
        }
        if (num == null || num == 0) {
            // 该值可用
            return Result.ok("该值可用！");
        } else {
            // 该值不可用
            log.info("该值不可用，系统中已存在！");
            return Result.error("该值不可用，系统中已存在！");
        }
    }


}

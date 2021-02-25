package com.haso.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haso.common.api.vo.Result;
import com.haso.common.system.QueryGenerator;
import com.haso.common.util.MessageUtil;
import com.haso.common.util.OConvertUtil;
import com.haso.system.entity.SysError;
import com.haso.system.service.SysErrLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 错误日志表 前端控制器
 * </p>
 *
 * @Author houxinyuan
 * @since 2020-06-30
 */
@RestController
@RequestMapping("/sys/err")
@Slf4j
public class SysErrLogController {

    @Autowired
    private SysErrLogService sysErrLogService;

    /**
     * @功能：查询日志记录
     * @param sysErrlog
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<IPage<SysError>> queryPageList(SysError sysErrlog, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize, HttpServletRequest req) {
        Result<IPage<SysError>> result = new Result<IPage<SysError>>();
        QueryWrapper<SysError> queryWrapper = QueryGenerator.initQueryWrapper(sysErrlog, req.getParameterMap());
        Page<SysError> page = new Page<SysError>(pageNo, pageSize);
        //タスク名
        String jobname = req.getParameter("jobname");
        if(OConvertUtil.isNotEmpty(jobname)) {
            queryWrapper.like("errormsg",jobname);
        }
        //発生時刻
        String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        if(OConvertUtil.isNotEmpty(startTime) && OConvertUtil.isNotEmpty(endTime)) {
            queryWrapper.between("errortime", startTime,endTime);
        }
        // 対応状況
        String status = req.getParameter("status");
        if(OConvertUtil.isNotEmpty(status)) {
            queryWrapper.like("status_flg",status);
        }
        IPage<SysError> pageList = sysErrLogService.page(page, queryWrapper);
        log.info("查询当前页："+pageList.getCurrent());
        log.info("查询当前页数量："+pageList.getSize());
        log.info("查询结果数量："+pageList.getRecords().size());
        log.info("数据总数："+pageList.getTotal());
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     *  编辑
     * @param errlog
     * @return
     */
    @PutMapping(value = "/edit")
    public Result<SysError> edit(@RequestBody SysError errlog) {
        Result<SysError> result = new Result<SysError>();
        SysError sysError = sysErrLogService.getById(errlog.getId());
        if(sysError==null) {
            // 該当するデータが存在しません。（{0}）
            result.error500(MessageUtil.get("message.0011","id:" + errlog.getId()));
        }else {
            boolean ok = sysErrLogService.updateById(sysError);
            if(ok) {
                // 更新しました。
                result.success(MessageUtil.get("message.0002"));
            }
        }
        return result;
    }

    /**
     * 查找错误日志
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Result<SysError> queryById(@RequestParam(name = "id", required = true) Integer id) {
        Result<SysError> result = new Result<SysError>();
        SysError sysError = sysErrLogService.getById(id);
        if (sysError == null) {
            // 該当するデータが存在しません。（{0}）
            result.error500(MessageUtil.get("message.0011","id:" + id));
        } else {
            result.setResult(sysError);
            result.setSuccess(true);
        }
        return result;
    }
}

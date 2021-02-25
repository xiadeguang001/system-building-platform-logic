package com.haso.batch.quartz.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import com.haso.common.api.vo.Result;
import com.haso.common.util.CommonQueryUtil;
import com.haso.batch.quartz.entity.BatchTaskRecord;
import com.haso.batch.quartz.service.BatchTaskRecordService;
import com.haso.batch.quartz.vo.BatchTaskRecordView;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
/**
 * @Description: Task在线管理
 * @Author: jeecg-boot
 * @Date: 2019-01-02
 * @Version:V1.0
 */
@RestController
@RequestMapping("/sys/taskRecord")
@Api(tags = "job接口")
@Slf4j
public class BatchTaskRecordController {
	@Autowired
	private BatchTaskRecordService batchTaskRecordService;
	
	/**
	 * 分页列表查询
	 * 
	 * @param batchTaskRecord
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result<?> queryPageList(BatchTaskRecordView view, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
								   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Result<Page<BatchTaskRecordView>> result = new Result<>();
		CommonQueryUtil.updateOrderColumn(req, view);
		view.setSearchTimeStart(transfer2String(view.getSearchTimeStart()));
		view.setSearchTimeEnd(transfer2String(view.getSearchTimeEnd()));
		Page<BatchTaskRecordView> page = new Page<BatchTaskRecordView>(pageNo, pageSize);
		Page<BatchTaskRecordView> list = batchTaskRecordService.searchList(page, view);
		
		log.debug("查询当前页：" + list.getCurrent());
        log.debug("查询当前页数量：" + list.getSize());
        log.debug("查询结果数量：" + list.getRecords().size());
        log.debug("数据总数：" + list.getTotal());
        result.setSuccess(true);
        result.setResult(list);
		return result;

	}
	
	private String transfer2String(String dateString) {
		if (Strings.isNullOrEmpty(dateString)) return null;
		return dateString.replaceAll(":", "").replaceAll(" ", "").replaceAll("-", "");
	}
	
	/**
	 * 更新Job
	 * 
	 * @param BatchJobInfo
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public Result<?> edit(@RequestBody BatchTaskRecordView view) {
		BatchTaskRecord entity = batchTaskRecordService.getById(view.getId());
		entity.setErrorHandleContent(view.getErrorHandleContent());
		entity.setErrorHandleStatus(view.getErrorHandleStatus());
		entity.setErrorHandleTime(view.getErrorHandleTime());
		batchTaskRecordService.updateById(entity);
	    return Result.ok("更新TaskRecord成功!");
	}


}

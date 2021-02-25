package com.haso.batch.quartz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haso.common.api.vo.Result;
import com.haso.common.exception.AppException;
import com.haso.common.util.CommonQueryUtil;
import com.haso.batch.quartz.entity.BatchJobInfo;
import com.haso.batch.quartz.entity.BatchTaskJobs;
import com.haso.batch.quartz.service.BatchJobInfoService;
import com.haso.batch.quartz.service.BatchTaskJobsService;
import com.haso.batch.quartz.vo.BatchJobView;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
/**
 * @Description: Job在线管理
 * @Author: jeecg-boot
 * @Date: 2019-01-02
 * @Version:V1.0
 */
@RestController
@RequestMapping("/sys/job")
@Api(tags = "job接口")
@Slf4j
public class BatchJobInfoController {
	@Autowired
	private BatchJobInfoService batchJobInfoService;
	
	@Autowired
	private BatchTaskJobsService batchTaskJobsService;

	/**
	 * 分页列表查询
	 * 
	 * @param batchJobInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result<?> queryPageList(BatchJobView view, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
								   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Result<Page<BatchJobView>> result = new Result<>();
		CommonQueryUtil.updateOrderColumn(req, view);
		
		Page<BatchJobView> page = new Page<BatchJobView>(pageNo, pageSize);
		Page<BatchJobView> list = batchJobInfoService.searchList(page, view);
		log.debug("查询当前页：" + list.getCurrent());
        log.debug("查询当前页数量：" + list.getSize());
        log.debug("查询结果数量：" + list.getRecords().size());
        log.debug("数据总数：" + list.getTotal());
        result.setSuccess(true);
        result.setResult(list);
		return result;

	}
	
	/**
	 * 获取全部jobs
	 * @return
	 */
	@RequestMapping(value = "/allJobs", method = RequestMethod.GET)
	public Result<List<BatchJobInfo>> getAllJobs() {
		Result<List<BatchJobInfo>> result = new Result<>();
		result.setSuccess(true);
        result.setResult(batchJobInfoService.getAllJobs());
		return result;
	}

	/**
	 * 添加Job
	 * 
	 * @param view
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result<?> add(@RequestBody BatchJobView view) {
		BatchJobInfo entity = new BatchJobInfo();
		BeanUtils.copyProperties(view, entity);
		batchJobInfoService.save(entity);
		return Result.ok("创建Job成功");
	}

	/**
	 * 更新Job
	 * 
	 * @param BatchJobInfo
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public Result<?> edit(@RequestBody BatchJobView view) {
		BatchJobInfo entity = new BatchJobInfo();
		BeanUtils.copyProperties(view, entity);
		batchJobInfoService.updateById(entity);
	    return Result.ok("更新Job成功!");
	}

	/**
	 * 通过id删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.PUT)
	public Result<?> delete(@RequestBody BatchJobView view) {
		
		List<BatchTaskJobs> taskJobs = batchTaskJobsService.getTaskJobsByJobId(view.getId());
		if (CollectionUtils.isEmpty(taskJobs)) {
			batchJobInfoService.removeById(view.getId());
		} else {
			throw new AppException("存在任务使用该工作，不能删除！");
		}
        return Result.ok("删除成功!");

	}

	/**
	 * 通过id查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryById", method = RequestMethod.GET)
	public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
		BatchJobInfo BatchJobInfo = batchJobInfoService.getById(id);
        return Result.ok(BatchJobInfo);
	}

}

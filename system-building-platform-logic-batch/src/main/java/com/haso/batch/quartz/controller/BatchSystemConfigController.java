package com.haso.batch.quartz.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haso.common.api.vo.Result;
import com.haso.batch.quartz.entity.BatchSystemConfig;
import com.haso.batch.quartz.service.BatchSystemConfigService;
import com.haso.batch.quartz.vo.BatchSystemConfigView;

import io.swagger.annotations.Api;
/**
 * @Description: Batch system config
 * @Author: jeecg-boot
 * @Date: 2019-01-02
 * @Version:V1.0
 */
@RestController
@RequestMapping("/sys/batchSystemConfig")
@Api(tags = "batchSystemConfig接口")
public class BatchSystemConfigController {
	@Autowired
	private BatchSystemConfigService batchSystemConfigService;

	/**
	 * 分页列表查询
	 * 
	 * @param batchJobInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public Result<?> queryPageList() {
		Result<BatchSystemConfig> result = new Result<>();
		BatchSystemConfig configInfo = batchSystemConfigService.getConfigInfo();
        result.setSuccess(true);
        result.setResult(configInfo);
		return result;

	}

	/**
	 * 更新Job
	 * 
	 * @param BatchJobInfo
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public Result<?> edit(@RequestBody BatchSystemConfigView view) {
		BatchSystemConfig entity = null;
		if (view.getId() != null) {
			entity = batchSystemConfigService.getById(view.getId());
		}
		if (entity == null) {
			entity = new BatchSystemConfig();
		}
		entity = new BatchSystemConfig();
		BeanUtils.copyProperties(view, entity);
		batchSystemConfigService.saveOrUpdate(entity);
	    return Result.ok("更新成功!");
	}

}

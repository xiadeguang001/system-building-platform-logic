package com.haso.batch.quartz.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haso.common.util.CommonQueryUtil;
import com.haso.batch.quartz.entity.BatchTaskJobs;
import com.haso.batch.quartz.mapper.BatchTaskJobsMapper;
import com.haso.batch.quartz.vo.BatchTaskJobsView;
/**
 * @Description: job管理
 * @Author: jeecg-boot
 * @Date: 2019-04-28
 * @Version: V1.1
 */
@Service
public class BatchTaskJobsService extends ServiceImpl<BatchTaskJobsMapper, BatchTaskJobs> {
	@Resource
	private BatchTaskJobsMapper batchTaskJobsMapper;
 
	/**
	 * 查询计划任务关联分页
	 * @param page 分页
	 * @param view 查询条件
	 * @return
	 */
	public Page<BatchTaskJobsView> searchList(Page<BatchTaskJobsView> page, BatchTaskJobsView view) {
		return batchTaskJobsMapper.searchList(page, view);
	}
	
	/**
	 * 根据计划Id获取所有计划任务关联信息
	 * @param taskId
	 * @return
	 */
	public List<BatchTaskJobsView> getTaskJobsByTask(Integer taskId) {
		return batchTaskJobsMapper.getTaskJobsByTask(taskId);
	}
	
	
	/**
	 * 根据任务Id获取计划任务关联信息
	 * @param jobId
	 * @return
	 */
	public List<BatchTaskJobs> getTaskJobsByJobId(Integer jobId) {
		LambdaQueryWrapper<BatchTaskJobs> query = new LambdaQueryWrapper<BatchTaskJobs>();
		query.eq(BatchTaskJobs :: getDeleteFlg, CommonQueryUtil.logic_flg_normal);
		query.eq(BatchTaskJobs :: getJobId, jobId);
		return list(query);
	}

}

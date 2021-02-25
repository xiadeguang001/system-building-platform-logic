package com.haso.batch.quartz.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haso.batch.quartz.entity.BatchTaskRecord;
import com.haso.batch.quartz.mapper.BatchTaskRecordMapper;
import com.haso.batch.quartz.vo.BatchTaskRecordView;
/**
 * @Description: job管理
 * @Author: jeecg-boot
 * @Date: 2019-04-28
 * @Version: V1.1
 */
@Service
public class BatchTaskRecordService extends ServiceImpl<BatchTaskRecordMapper, BatchTaskRecord> {
	@Resource
	private BatchTaskRecordMapper batchTaskRecordMapper;

	/**
	 * 根据taskId获取执行记录
	 * @param taskId
	 * @return
	 */
	public List<BatchTaskRecordView> getTaskRecordByTask(Integer taskId) {
		return batchTaskRecordMapper.getTaskRecordByTask(taskId);
	}
	
	
	/**
	 * 根据查询条件获取执行记录分页
	 * @param page 分页信息
	 * @param view 查询条件
	 * @return
	 */
	public Page<BatchTaskRecordView> searchList(Page<BatchTaskRecordView> page, BatchTaskRecordView view) {
		return batchTaskRecordMapper.searchList(page, view);
	}

}

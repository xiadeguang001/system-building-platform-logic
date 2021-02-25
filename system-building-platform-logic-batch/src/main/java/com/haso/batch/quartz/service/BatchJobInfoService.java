package com.haso.batch.quartz.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haso.common.util.CommonQueryUtil;
import com.haso.batch.quartz.entity.BatchJobInfo;
import com.haso.batch.quartz.mapper.BatchJobInfoMapper;
import com.haso.batch.quartz.vo.BatchJobView;
/**
 * @Description: job管理
 * @Author: jeecg-boot
 * @Date: 2019-04-28
 * @Version: V1.1
 */
@Service
public class BatchJobInfoService extends ServiceImpl<BatchJobInfoMapper, BatchJobInfo> {
	@Resource
	private BatchJobInfoMapper batchJobInfoMapper;

	/**
	 * 根据查询条件查询任务分页信息
	 * @param page 分页
	 * @param view 查询条件
	 * @return
	 */
	public Page<BatchJobView> searchList(Page<BatchJobView> page, BatchJobView view) {
		return batchJobInfoMapper.searchBatchJobs(page, view);
	}
	
	/**
	 * 获取所有任务
	 * @return
	 */
	public List<BatchJobInfo> getAllJobs() {
		LambdaQueryWrapper<BatchJobInfo> query = new LambdaQueryWrapper<BatchJobInfo>();
		query.eq(BatchJobInfo :: getDeleteFlg, CommonQueryUtil.logic_flg_normal);
		return list(query);
	}

}

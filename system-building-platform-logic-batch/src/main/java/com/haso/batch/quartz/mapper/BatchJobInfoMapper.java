package com.haso.batch.quartz.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haso.batch.quartz.entity.BatchJobInfo;
import com.haso.batch.quartz.vo.BatchJobView;

public interface BatchJobInfoMapper extends BaseMapper<BatchJobInfo>{
	
	public Page<BatchJobView> searchBatchJobs(@Param("page") Page<BatchJobView> page, @Param("view") BatchJobView view);
	
	public Page<BatchJobView> searchBatchJobsNew(@Param("page") Page<BatchJobView> page, @Param("view") Map<String, Object> view);
	
	public Integer countUsedTasks(@Param("jobId")Integer jobId);
}

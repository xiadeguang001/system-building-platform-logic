package com.haso.batch.quartz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haso.batch.quartz.entity.BatchTaskJobs;
import com.haso.batch.quartz.vo.BatchTaskJobsView;

public interface BatchTaskJobsMapper extends BaseMapper<BatchTaskJobs>{
	
	public Page<BatchTaskJobsView> searchList(@Param("page") Page<BatchTaskJobsView> page, @Param("view") BatchTaskJobsView view);
	
	public List<BatchTaskJobsView> getTaskJobsByTask(@Param("taskId") Integer taskId);
}

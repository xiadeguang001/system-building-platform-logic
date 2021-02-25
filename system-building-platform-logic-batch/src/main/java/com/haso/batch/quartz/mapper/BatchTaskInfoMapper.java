package com.haso.batch.quartz.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haso.batch.quartz.entity.BatchTaskInfo;
import com.haso.batch.quartz.vo.BatchTaskView;

public interface BatchTaskInfoMapper extends BaseMapper<BatchTaskInfo>{
	
	public Page<BatchTaskView> searchList(@Param("page") Page<BatchTaskView> page, @Param("view") BatchTaskView view);
	
	public Page<BatchTaskView> searchListNew(@Param("page") Page<BatchTaskView> page, @Param("view") Map<String, Object> view);
	
	public List<BatchTaskView> getAllTasks();
	
	public void transferJobDetailToConcurrent(@Param("taskName")String taskName);
}

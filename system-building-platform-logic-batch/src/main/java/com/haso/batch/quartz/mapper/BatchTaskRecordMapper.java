package com.haso.batch.quartz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haso.batch.quartz.entity.BatchTaskRecord;
import com.haso.batch.quartz.vo.BatchTaskRecordView;

public interface BatchTaskRecordMapper extends BaseMapper<BatchTaskRecord>{
	
	public List<BatchTaskRecordView> getTaskRecordByTask(@Param("taskId") Integer taskId);
	
	public Page<BatchTaskRecordView> searchList(@Param("page") Page<BatchTaskRecordView> page, @Param("view") BatchTaskRecordView view);
}

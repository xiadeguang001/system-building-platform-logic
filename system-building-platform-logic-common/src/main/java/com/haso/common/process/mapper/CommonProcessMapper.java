package com.haso.common.process.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haso.common.api.entity.ComHasoEntity;

public interface CommonProcessMapper extends BaseMapper<ComHasoEntity> {
	
	public void insertTestTable(@Param("cm") Map<String, Object> map);

}

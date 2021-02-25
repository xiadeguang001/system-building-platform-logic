package com.haso.common.process.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haso.common.api.vo.Result;
import com.haso.common.process.CommonProcessHelper;
import com.haso.common.process.CommonProcessRequest;
import com.haso.common.process.ProcessType;
import com.haso.common.util.ApplicationContextUtil;
import com.haso.common.util.MapperUtils;
import com.haso.common.util.ReflectionUtil;

@Component
public class PageSelectHandler implements CommonProcessHandler {

	@Override
	public void afterPropertiesSet() throws Exception {
		CommonProcessHelper.registHandler(ProcessType.PAGE_SELECT, this);

	}
	
	@Override
	public Result<?> handle(CommonProcessRequest request) {
		String mapperName = MapperUtils.getMapperNameByTableName(request.getDbDomain());
		Object mapper = ApplicationContextUtil.getBean(mapperName);
		
		Map<String, Object> param = request.getParams().get(0);
		Page<Map<String, Object>> page = new Page<Map<String, Object>>((Integer)param.get("pageNo"), (Integer)param.get("pageSize"));
		Object result = ReflectionUtil.invokeMethod(mapper, request.getSqlId(), page, param);
		return Result.ok(result);
	}

}

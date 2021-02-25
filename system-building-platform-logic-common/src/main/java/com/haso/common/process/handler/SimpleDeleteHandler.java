package com.haso.common.process.handler;

import org.springframework.stereotype.Component;

import com.haso.common.api.vo.Result;
import com.haso.common.process.CommonProcessHelper;
import com.haso.common.process.CommonProcessRequest;
import com.haso.common.process.ProcessType;
import com.haso.common.util.ApplicationContextUtil;
import com.haso.common.util.MapperUtils;
import com.haso.common.util.ReflectionUtil;

@Component
public class SimpleDeleteHandler implements CommonProcessHandler {

	@Override
	public void afterPropertiesSet() throws Exception {
		CommonProcessHelper.registHandler(ProcessType.DELETE, this);

	}

	@Override
	public Result<?> handle(CommonProcessRequest request) {
		String mapperName = MapperUtils.getMapperNameByTableName(request.getDbDomain());
		Object mapper = ApplicationContextUtil.getBean(mapperName);
		ReflectionUtil.invokeMethod(mapper, "deleteByMap", request.getParams().get(0));
		return Result.ok();
	}

}

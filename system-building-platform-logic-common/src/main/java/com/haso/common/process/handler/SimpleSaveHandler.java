package com.haso.common.process.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.haso.common.api.vo.Result;
import com.haso.common.process.CommonProcessHelper;
import com.haso.common.process.CommonProcessRequest;
import com.haso.common.process.ProcessType;
import com.haso.common.util.ApplicationContextUtil;
import com.haso.common.util.MapperUtils;
import com.haso.common.util.ReflectionUtil;

@Component
public class SimpleSaveHandler implements CommonProcessHandler {

	@Override
	public void afterPropertiesSet() throws Exception {
		CommonProcessHelper.registHandler(ProcessType.SAVE, this);

	}

	@Override
	public Result<Object> handle(CommonProcessRequest request) {
		String mapperName = MapperUtils.getMapperNameByTableName(request.getDbDomain());
		Object mapper = ApplicationContextUtil.getBean(mapperName);
		Class<?> entityClass = MapperUtils.getEntityClassByMapper(mapper.getClass());
		for (Map<String, Object> param : request.getParams()) {
			Object entity;
			try {
				entity = ReflectionUtil.mapToObject(param, entityClass);
				if (param.get("id") != null) {
					ReflectionUtil.invokeMethod(mapper, "updateById", entity);
				} else {
					ReflectionUtil.invokeMethod(mapper, "insert", entity);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Result.ok();
	}

}

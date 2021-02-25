package com.haso.common.process.handler;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.base.Strings;
import com.haso.common.api.vo.Result;
import com.haso.common.process.CommonProcessHelper;
import com.haso.common.process.CommonProcessRequest;
import com.haso.common.process.ProcessType;
import com.haso.common.process.mapper.CommonProcessMapper;
import com.haso.common.util.ApplicationContextUtil;
import com.haso.common.util.MapperUtils;
import com.haso.common.util.ReflectionUtil;

@Component
public class SqlProcessHandler implements CommonProcessHandler {
	@Autowired
	private CommonProcessMapper commonProcessMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		CommonProcessHelper.registHandler(ProcessType.SQL, this);

	}

	@Override
	public Result<Object> handle(CommonProcessRequest request) {
		String sqlId = request.getSqlId();
		List<Map<String, Object>> params = request.getParams();
		Object result = null;
		if (Strings.isNullOrEmpty(request.getDbDomain())) {
			for (Map<String, Object> p : params) {
				ReflectionUtil.invokeMethod(commonProcessMapper, sqlId, p);
			}
		} else {
			String mapperName = MapperUtils.getMapperNameByTableName(request.getDbDomain());
			BaseMapper<?> mapper = (BaseMapper<?>)ApplicationContextUtil.getBean(mapperName);
			
			if (CollectionUtils.isNotEmpty(params)) {
				for (Map<String, Object> p : params) {
					result = ReflectionUtil.invokeMethod(mapper, sqlId, p);
				}
			} else {
				result = ReflectionUtil.invokeMethod(mapper, sqlId);
			}
		}
		return Result.ok(result);
	}
	

}

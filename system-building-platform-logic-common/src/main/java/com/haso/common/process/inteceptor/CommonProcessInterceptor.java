package com.haso.common.process.inteceptor;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.haso.common.process.CommonProcessHelper;
import com.haso.common.process.CommonProcessRequest;

public interface CommonProcessInterceptor extends InitializingBean{
	
	void checkParams(List<Map<String, Object>> params);

	void handle(CommonProcessRequest request);
	
	default void afterPropertiesSet() {
		CommonProcessHelper.registInteceptor(StringUtils.firstToLowerCase(this.getClass().getSimpleName()), this);
	}
	
}

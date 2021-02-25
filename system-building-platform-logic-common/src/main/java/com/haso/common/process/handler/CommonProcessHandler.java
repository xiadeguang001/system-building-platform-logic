package com.haso.common.process.handler;

import org.springframework.beans.factory.InitializingBean;

import com.haso.common.api.vo.Result;
import com.haso.common.process.CommonProcessRequest;

public interface CommonProcessHandler extends InitializingBean{

	Result<?> handle(CommonProcessRequest request);
	
}

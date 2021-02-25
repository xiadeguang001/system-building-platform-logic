package com.haso.common.process;

import java.util.HashMap;
import java.util.Map;

import com.haso.common.api.vo.Result;
import com.haso.common.exception.AppException;
import com.haso.common.process.handler.CommonProcessHandler;
import com.haso.common.process.inteceptor.CommonProcessInterceptor;

public class CommonProcessHelper {
	
	private static Map<ProcessType, CommonProcessHandler> processMap = new HashMap<>();
	private static Map<String, CommonProcessInterceptor> inteceptorMap = new HashMap<>();
	
	public static Result<?> process(CommonProcessRequest request) {
		CommonProcessHandler handler = processMap.get(request.getOperationMod());
		return handler.handle(request);
	}
	
	public static void registHandler(ProcessType type, CommonProcessHandler handler) {
		processMap.put(type, handler);
	}
	
	public static void registInteceptor(String inteceptorName, CommonProcessInterceptor inteceptor) {
		if (inteceptorMap.get(inteceptorName) != null) {
			throw new AppException("拦截器名称重复！" + inteceptorName);
		}
		inteceptorMap.put(inteceptorName, inteceptor);
	}
	
	public static void intercept(String inteceptorName, CommonProcessRequest request) {
		CommonProcessInterceptor interceptor = inteceptorMap.get(inteceptorName);
		if (interceptor != null) {
			interceptor.checkParams(request.getParams());
			interceptor.handle(request);
		}
	}
}

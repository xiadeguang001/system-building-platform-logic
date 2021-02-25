package com.haso.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haso.common.exception.AppException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReflectionUtil {

	public static Object invokeMethod(Object obj, String methodName, Object... params) {
		Object result = null;
		List<Object> paramList = Arrays.asList(params);
		List<?> classList = paramList.stream().map(p -> p.getClass()).collect(Collectors.toList());
		try {
			Method method = searchMethod(obj.getClass(), methodName, classList.toArray(new Class[] {}));
			result = method.invoke(obj, params);
		} catch (Exception e) {
			throw new AppException(e);
		}
		return result;
	}
	
	public static Object mapToObject(Map<String, Object> map, Class<?> objClazz) {
		Object object = null;
		try {
			ObjectMapper om = new ObjectMapper();
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			String json = om.writeValueAsString(map);
			object = om.readValue(json, objClazz);
		} catch(Exception e) {
			throw new AppException(e);
		}
		
		return object;
	}
	
	public static Field searchField(Class<?> clazz, String fieldName) {
		Field field = null;
		if (clazz.equals(Object.class)) return null;
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (f.getName().equals(fieldName)) {
				field = f;
				break;
			}
		}
		
		if (field == null) {
			return searchField(clazz.getSuperclass(), fieldName);
		} else {
			return field;
		}
	}
	
	public static Method searchMethod(Class<?> clazz, String methodName, Class<?>... types) {
		Method method = null;
		if (clazz.equals(Object.class)) return null;
		for (Method m : clazz.getDeclaredMethods()) {
			if (m.getName().equals(methodName)) {
				method = m;
				break;
			}
		}
		
		if (method == null) {
			return searchMethod(clazz.getSuperclass(), methodName, types);
		} else {
			return method;
		}
	}
	
}

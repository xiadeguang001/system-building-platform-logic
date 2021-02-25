package com.haso.common.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class MapperUtils {
	
	public static String getMapperNameByTableName(String tableName) {
		String mapperNamePrefix = StringUtils.underlineToCamel(tableName);
		return mapperNamePrefix + "Mapper";
	}
	
	public static Class<?> getEntityClassByMapper(Class<?> mapperClazz) {
		Class<?> entityClass = null;
		if (Object.class.equals(mapperClazz)) return null;
		Class<?> genericInterfaces = (Class<?>)mapperClazz.getGenericInterfaces()[0];
		Type type = genericInterfaces.getGenericInterfaces()[0];
		if (type instanceof ParameterizedType) {
			Type[] actualTypeArguments = ((ParameterizedType) type)
					.getActualTypeArguments();
			if (actualTypeArguments != null && actualTypeArguments.length > 0) {
				entityClass = (Class<?>) actualTypeArguments[0];
			}
		}
		
		if (entityClass == null) {
			return getEntityClassByMapper(mapperClazz.getSuperclass());
		} else {
			return entityClass;
		}

		
	}

}

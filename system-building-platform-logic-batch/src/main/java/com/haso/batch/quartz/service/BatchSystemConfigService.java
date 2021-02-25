package com.haso.batch.quartz.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haso.common.util.CommonQueryUtil;
import com.haso.batch.quartz.entity.BatchSystemConfig;
import com.haso.batch.quartz.mapper.BatchJobInfoMapper;
import com.haso.batch.quartz.mapper.BatchSystemConfigMapper;
/**
 * @Description: system config管理
 * @Author: jeecg-boot
 * @Date: 2019-04-28
 * @Version: V1.1
 */
@Service
public class BatchSystemConfigService extends ServiceImpl<BatchSystemConfigMapper, BatchSystemConfig> {
	@Resource
	private BatchJobInfoMapper batchJobInfoMapper;

	/**
	 * 获取基本设定信息
	 * @return
	 */
	public BatchSystemConfig getConfigInfo() {
		LambdaQueryWrapper<BatchSystemConfig> query = new LambdaQueryWrapper<BatchSystemConfig>();
		query.eq(BatchSystemConfig :: getDeleteFlg, CommonQueryUtil.logic_flg_normal);
		List<BatchSystemConfig> result = list(query);
		if (CollectionUtils.isEmpty(result)) return null;
		return result.get(0);
	}

}

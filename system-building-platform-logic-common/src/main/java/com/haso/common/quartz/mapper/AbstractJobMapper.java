package com.haso.common.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haso.common.system.vo.QuartzEntity;
import com.haso.common.system.vo.QuartzErrorEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AbstractJobMapper extends BaseMapper {

    /**
     * 保存需执行Job
     * @param entity
     * @return
     */
    public Integer addRunningJob(QuartzEntity entity);

    /**
     * 获取正在执行中的JOB（按组）
     * @param entity
     * @return
     */
    public List<Map<String, Object>> searchRJobByGroupName(QuartzEntity entity);

    /**
     * 删除JOB（按主键）
     * @param id
     */
    public void deleteById(Integer id);

    /**
     * 删除所有JOB记录（工程重新启动时调用）
     */
    public void deleteAll();

    /**
     * 保存Job错误信息
     * @param entity
     * @return
     */
    public Integer addErrorMessage(QuartzErrorEntity entity);
}

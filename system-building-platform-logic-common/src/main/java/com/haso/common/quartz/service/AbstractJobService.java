package com.haso.common.quartz.service;


import com.haso.common.system.vo.QuartzEntity;
import com.haso.common.system.vo.QuartzErrorEntity;
import com.haso.common.quartz.mapper.AbstractJobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 共通Job Service
 * @author Gong Lingxiao
 */
@Service
public class AbstractJobService {

    @Autowired
    private AbstractJobMapper abstractJobMapper;

    /**
     * 保存需执行Job
     * @param entity
     * @return
     */
    public QuartzEntity addRunningJob(QuartzEntity entity) {
        abstractJobMapper.addRunningJob(entity);
        return entity;
    }

    /**
     * 根据group name获取当前执行中的JOB
     * @param entity
     * @return
     */
    public List<Map<String, Object>> searchRJobByGroupName(QuartzEntity entity) {
        List<Map<String, Object>> datas = abstractJobMapper.searchRJobByGroupName(entity);
        return datas;
    }

    /**
     * 删除JOB
     * @param id
     */
    public void deleteById(Integer id) {
        abstractJobMapper.deleteById(id);
    }

    /**
     * 删除所有JOB记录（工程重新启动时调用）
     */
    public void deleteAll() {
        abstractJobMapper.deleteAll();
    }

    /**
     * 保存Job错误信息
     * @param entity
     * @return
     */
    public QuartzErrorEntity addErrorMessage(QuartzErrorEntity entity) {
        abstractJobMapper.addErrorMessage(entity);
        return entity;
    }
}

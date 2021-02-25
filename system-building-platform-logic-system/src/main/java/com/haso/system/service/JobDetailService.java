package com.haso.system.service;

import com.haso.system.mapper.JobDetailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class JobDetailService {

    @Resource
    private JobDetailMapper jobDetailMapper;

    public void updatePrevFireTimeByCondition(String schedName, String jobName, String jobGroup, String prevFireTime) {
        jobDetailMapper.updatePrevFireTimeByCondition(schedName, jobName, jobGroup, Long.parseLong(prevFireTime));
    }
}

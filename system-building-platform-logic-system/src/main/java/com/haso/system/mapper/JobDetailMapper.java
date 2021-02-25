package com.haso.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface JobDetailMapper {

    public void updatePrevFireTimeByCondition(@Param("schedName") String schedName, @Param("jobName") String jobName, @Param("jobGroup") String jobGroup, @Param("prevFireTime") long prevFireTime);

}

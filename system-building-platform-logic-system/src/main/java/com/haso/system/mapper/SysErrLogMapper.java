package com.haso.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haso.system.entity.SysError;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 错误日志表 Mapper接口
 * </p>
 *
 * @Author houxinyuan
 * @since 2020-06-30
 */
@Mapper
public interface SysErrLogMapper extends BaseMapper<SysError> {

    /**
     * @功能：清空所有日志记录
     */
    public void removeAll();
}

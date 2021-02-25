package com.haso.system.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haso.system.entity.SysError;
import com.haso.system.mapper.SysErrLogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 错误日志表 服务实现类
 * </p>
 *
 * @Author houxinyuan
 * @since 2020-06-30
 */
@Service
public class SysErrLogService extends ServiceImpl<SysErrLogMapper, SysError> {

    @Resource
    private SysErrLogMapper sysErrLogMapper;

    /**
     * @功能：清空所有日志记录
     */
    public void removeAll() {
        sysErrLogMapper.removeAll();
    }
}


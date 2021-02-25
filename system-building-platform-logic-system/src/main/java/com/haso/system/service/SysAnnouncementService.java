package com.haso.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haso.common.constant.CommonConstant;
import com.haso.common.util.ConvertUtil;
import com.haso.system.entity.SysAnnouncement;
import com.haso.system.entity.SysAnnouncementSend;
import com.haso.system.mapper.SysAnnouncementMapper;
import com.haso.system.mapper.SysAnnouncementSendMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description: 系统通告表
 * @Author: jeecg-boot
 * @Date:  2019-01-02
 * @Version: V1.0
 */
@Service
public class SysAnnouncementService extends ServiceImpl<SysAnnouncementMapper, SysAnnouncement> {

    @Resource
    private SysAnnouncementMapper sysAnnouncementMapper;

    @Resource
    private SysAnnouncementSendMapper sysAnnouncementSendMapper;

    @Transactional
    public void saveAnnouncement(SysAnnouncement sysAnnouncement) {
        if(sysAnnouncement.getMsgType().equals(CommonConstant.MSG_TYPE_ALL)) {
            sysAnnouncementMapper.insert(sysAnnouncement);
        }else {
            // 1.插入通告表记录
            sysAnnouncementMapper.insert(sysAnnouncement);
            // 2.插入用户通告阅读标记表记录
            String userId = sysAnnouncement.getUserIds();
            String[] userIds = userId.substring(0, (userId.length())).split(",");
            String anntId = ConvertUtil.getString(sysAnnouncement.getId());
            Date refDate = new Date();
            for(int i=0;i<userIds.length;i++) {
                SysAnnouncementSend announcementSend = new SysAnnouncementSend();
                announcementSend.setAnntId(Integer.parseInt(anntId));
                announcementSend.setUserId(Integer.parseInt(userIds[i]));
                announcementSend.setReadFlag(CommonConstant.NO_READ_FLAG);
                announcementSend.setReadTime(refDate);
                sysAnnouncementSendMapper.insert(announcementSend);
            }
        }
    }

    /**
     * @功能：编辑消息信息
     */
    @Transactional
    public boolean upDateAnnouncement(SysAnnouncement sysAnnouncement) {
        // 1.更新系统信息表数据
        sysAnnouncementMapper.updateById(sysAnnouncement);
        String userId = sysAnnouncement.getUserIds();
        if(ConvertUtil.isNotEmpty(userId)&&sysAnnouncement.getMsgType().equals(CommonConstant.MSG_TYPE_UESR)) {
            // 2.补充新的通知用户数据
            String[] userIds = userId.substring(0, (userId.length()-1)).split(",");
            String anntId = ConvertUtil.getString(sysAnnouncement.getId());
            Date refDate = new Date();
//            for(int i=0;i<userIds.length;i++) {
//                LambdaQueryWrapper<SysAnnouncementSend> queryWrapper = new LambdaQueryWrapper<SysAnnouncementSend>();
//                queryWrapper.eq(SysAnnouncementSend::getAnntId, anntId);
//                queryWrapper.eq(SysAnnouncementSend::getUserId, userIds[i]);
//                List<SysAnnouncementSend> announcementSends=sysAnnouncementSendMapper.selectList(queryWrapper);
//                if(announcementSends.size()<=0) {
//                    SysAnnouncementSend announcementSend = new SysAnnouncementSend();
//                    announcementSend.setAnntId(anntId);
//                    announcementSend.setUserId(userIds[i]);
//                    announcementSend.setReadFlag(CommonConstant.NO_READ_FLAG);
//                    announcementSend.setReadTime(refDate);
//                    sysAnnouncementSendMapper.insert(announcementSend);
//                }
//            }
//            // 3. 删除多余通知用户数据
//            Collection<String> delUserIds = Arrays.asList(userIds);
//            LambdaQueryWrapper<SysAnnouncementSend> queryWrapper = new LambdaQueryWrapper<SysAnnouncementSend>();
//            queryWrapper.notIn(SysAnnouncementSend::getUserId, delUserIds);
//            queryWrapper.eq(SysAnnouncementSend::getAnntId, anntId);
//            sysAnnouncementSendMapper.delete(queryWrapper);
        }
        return true;
    }

    // @功能：流程执行完成保存消息通知
    public void saveSysAnnouncement(String title, String msgContent) {
        SysAnnouncement announcement = new SysAnnouncement();
        announcement.setTitile(title);
        announcement.setMsgContent(msgContent);
        announcement.setSender("JEECG BOOT");
        announcement.setPriority(CommonConstant.PRIORITY_L);
        announcement.setMsgType(CommonConstant.MSG_TYPE_ALL);
        announcement.setSendStatus(CommonConstant.HAS_SEND);
        announcement.setSendTime(new Date());
        announcement.setDelFlag(CommonConstant.DEL_FLAG_0.toString());
        sysAnnouncementMapper.insert(announcement);
    }

    public Page<SysAnnouncement> querySysCementPageByUserId(Page<SysAnnouncement> page, String userId,String msgCategory) {
        return page.setRecords(sysAnnouncementMapper.querySysCementListByUserId(page, ConvertUtil.getInteger(userId), msgCategory));
    }

}

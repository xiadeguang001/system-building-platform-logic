package com.haso.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haso.common.util.ConvertUtil;
import com.haso.system.entity.SysAnnouncementSend;
import com.haso.system.mapper.SysAnnouncementSendMapper;
import com.haso.system.model.AnnouncementSendModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 用户通告阅读标记表
 * @Author: jeecg-boot
 * @Date:  2019-02-21
 * @Version: V1.0
 */
@Service
public class SysAnnouncementSendService extends ServiceImpl<SysAnnouncementSendMapper, SysAnnouncementSend> {

	@Resource
	private SysAnnouncementSendMapper sysAnnouncementSendMapper;
	
	public List<String> queryByUserId(String userId) {
		return sysAnnouncementSendMapper.queryByUserId(ConvertUtil.getInteger(userId));
	}

	public Page<AnnouncementSendModel> getMyAnnouncementSendPage(Page<AnnouncementSendModel> page,
																 AnnouncementSendModel announcementSendModel) {
		 return page.setRecords(sysAnnouncementSendMapper.getMyAnnouncementSendList(page, announcementSendModel));
	}

}

package com.jet.vframe.appwebmin.channel.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jet.vframe.appwebmin.channel.dao.ChannelDao;
import com.jet.vframe.appwebmin.channel.pojo.Channel;
import com.jet.vframe.appwebmin.channel.service.ChannelService;
import com.jet.vframe.sys.base.service.impl.BaseServiceImpl;
import com.jet.vframe.sys.tool.HQLUtils;

@Service
public class ChannelServiceImpl extends BaseServiceImpl<Channel> implements ChannelService {

	@Override
	public String getIdByName(int channel_name) {
		// TODO Auto-generated method stub
		return (String) this.baseDao.queryUnique("id", "name=:name", HQLUtils.generalMapParamsSingle("name", channel_name));
	}

	@Override
	public List<Integer> getNamesByUser(String user_name) {
		// TODO Auto-generated method stub
		return ((ChannelDao) this.baseDao).getNamesByUser(user_name);
	}

}

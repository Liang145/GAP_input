package com.jet.vframe.appwebmin.channel.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jet.vframe.appwebmin.channel.dao.ChannelDao;
import com.jet.vframe.appwebmin.channel.pojo.Channel;
import com.jet.vframe.sys.base.dao.impl.BaseDaoImpl;
import com.jet.vframe.sys.tool.HQLUtils;

@Repository
public class ChannelDaoImpl extends BaseDaoImpl<Channel> implements ChannelDao {

	@Override
	public int deleteByUserName(String user_name) {
		// TODO Auto-generated method stub
		return this.batchDelete("user_name=:user_name", HQLUtils.generalMapParamsSingle("user_name", user_name));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getNamesByUser(String user_name) {
		// TODO Auto-generated method stub
		return this.query("name", "user_name=:user_name", null, null, HQLUtils.generalMapParamsSingle("user_name", user_name));
	}

}

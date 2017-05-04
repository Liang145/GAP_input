package com.jet.vframe.appwebmin.channel.dao;

import java.util.List;

import com.jet.vframe.appwebmin.channel.pojo.Channel;
import com.jet.vframe.sys.base.dao.BaseDao;

public interface ChannelDao extends BaseDao<Channel> {

	int deleteByUserName(String user_name);

	List<Integer> getNamesByUser(String user_name);

}

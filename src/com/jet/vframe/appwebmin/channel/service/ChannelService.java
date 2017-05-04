package com.jet.vframe.appwebmin.channel.service;

import java.util.List;

import com.jet.vframe.appwebmin.channel.pojo.Channel;
import com.jet.vframe.sys.base.service.BaseService;

public interface ChannelService extends BaseService<Channel> {

	String getIdByName(int channel_name);

	List<Integer> getNamesByUser(String user_name);

}

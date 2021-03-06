package com.jet.vframe.appwebmin.datalog.service;

import java.util.List;

import com.jet.vframe.appwebmin.datalog.pojo.DataLog;
import com.jet.vframe.sys.base.service.BaseService;
import com.jet.vframe.sys.user.pojo.User;

public interface DataLogService extends BaseService<DataLog> {

	List<String> getCsvList(List<Integer> channelNames, User webUser, int role, String start_date, String end_date);

	void deleteAll();

	void deleteByUser(String user_name);

	int createIndex(String string);

}

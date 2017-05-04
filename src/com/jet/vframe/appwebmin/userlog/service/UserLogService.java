package com.jet.vframe.appwebmin.userlog.service;

import java.util.List;

import com.jet.vframe.appwebmin.userlog.pojo.UserLog;
import com.jet.vframe.sys.base.service.BaseService;

public interface UserLogService extends BaseService<UserLog> {

	void addLog(String user_name, String operateIp, String operateName, String operateType);

	List<String> getCsvList();

}

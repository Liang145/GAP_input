package com.jet.vframe.appwebmin.userlog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jet.vframe.appwebmin.userlog.pojo.UserLog;
import com.jet.vframe.appwebmin.userlog.service.UserLogService;
import com.jet.vframe.sys.base.service.impl.BaseServiceImpl;
import com.jet.vframe.sys.tool.UUID;

@Service
public class UserLogServiceImpl extends BaseServiceImpl<UserLog>implements UserLogService {

	@Override
	public void addLog(String user_name, String operateIp, String operateName, String operateType) {
		// TODO Auto-generated method stub
		UserLog userLog = new UserLog();
		userLog.setCreate_date(new Date());
		userLog.setId(UUID.generate());
		userLog.setOperate_ip(operateIp);
		userLog.setOperate_name(operateName);
		userLog.setOperate_type(operateType);
		userLog.setUser_name(user_name);
		this.baseDao.insert(userLog);
	}

	@Override
	public List<String> getCsvList() {
		// TODO Auto-generated method stub
		List<String> csvList = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<Object[]> objList = this.baseDao.queryFields(
				new String[] { "id","user_name", "operate_name", "operate_ip", "operate_type", "create_date" }, null,
				"create_date DESC", null);
		if (objList != null && objList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			int len = objList.size();
			for (int i = 0; i < len; i++) {
				sb.delete(0, sb.length());
				Object[] arr = objList.get(i);
				for (int j = 0; j < arr.length; j++) {
					sb.append("\"");
					sb.append(arr[j].toString().replace("\"", "\"\""));
					sb.append("\"");
					sb.append(",");
				}
				sb.deleteCharAt(sb.length() - 1);
				csvList.add(sb.toString());
			}
		}
		return csvList;
	}
}

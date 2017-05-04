package com.jet.vframe.appwebmin.datalog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jet.vframe.appwebmin.channel.dao.ChannelDao;
import com.jet.vframe.appwebmin.datalog.pojo.DataLog;
import com.jet.vframe.appwebmin.datalog.service.DataLogService;
import com.jet.vframe.sys.base.service.impl.BaseServiceImpl;
import com.jet.vframe.sys.tool.HQLUtils;
import com.jet.vframe.sys.user.pojo.User;

@Service
public class DataLogServiceImpl extends BaseServiceImpl<DataLog> implements
		DataLogService {
	@Autowired
	private ChannelDao channelDao;

	@Override
	public List<String> getCsvList(List<Integer> channelNames, User webUser,
			int role, String start_date, String end_date) {
		// TODO Auto-generated method stub
		List<String> csvList = null;
		if (1 == role) {
			csvList = new ArrayList<String>();
			@SuppressWarnings("unchecked")
			List<Object[]> objList = this.baseDao.queryFields(new String[] {
					"id", "channel_name", "content", "content_type", "status",
					"create_date" }, "create_date BETWEEN '" + start_date
					+ "' AND '" + end_date + "'", "create_date DESC", null);
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
		} else if (3 == role) {
			if (channelNames != null && channelNames.size() > 0) {
				csvList = new ArrayList<String>();
				@SuppressWarnings("unchecked")
				List<Object[]> objList = this.baseDao.queryFields(new String[] {
						"id", "channel_name", "content", "content_type",
						"status", "create_date" }, "create_date BETWEEN '"
						+ start_date + "' AND '" + end_date
						+ "' AND channel_name IN (:channelNames)",
						"create_date DESC", HQLUtils.generalMapParamsSingle(
								"channelNames", channelNames.toArray()));
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
			}
		}
		return csvList;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		this.baseDao.batchDelete(null, null);
	}

	@Override
	public void deleteByUser(String user_name) {
		// TODO Auto-generated method stub
		List<Integer> channelNames = channelDao.getNamesByUser(user_name);
		this.baseDao.batchDelete("channel_name IN :names", HQLUtils.generalMapParamsSingle("names", channelNames));
	}

	@Override
	public int createIndex(String string) {
		// TODO Auto-generated method stub
		return this.baseDao.excuteSQL(string);
	}
}

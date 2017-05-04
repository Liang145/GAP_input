package com.jet.vframe.sys.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jet.vframe.appwebmin.channel.dao.ChannelDao;
import com.jet.vframe.sys.base.service.impl.BaseServiceImpl;
import com.jet.vframe.sys.tool.HQLUtils;
import com.jet.vframe.sys.user.ModifyPw;
import com.jet.vframe.sys.user.pojo.User;
import com.jet.vframe.sys.user.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User>implements UserService {

	@Autowired
	private ChannelDao channelDao;

	@Override
	public User login(String userName, String password) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_name", userName);
		map.put("password", password);
		return (User) baseDao.queryUnique("", "user_name = :user_name AND password = :password", map);

	}

	@Override
	public boolean modifyPassword(ModifyPw modifyPw) {
		// TODO Auto-generated method stub
		int res = baseDao.update("password = :password1", "user_name = :user_name AND password = :password",
				HQLUtils.generalMapParams(new String[] { "user_name", "password", "password1" },
						new Object[] { modifyPw.getUserName(), modifyPw.getPassword(), modifyPw.getPassword1() }));
		return res > 0;
	}

	@Override
	public boolean isExist(String userName) {
		// TODO Auto-generated method stub

		return this.baseDao.queryCount(new String[] { "user_name" }, "user_name=:user_name",
				HQLUtils.generalMapParams(new String[] { "user_name" }, new Object[] { userName })) > 0;
	}

	@Override
	public int updateLevel(User form) {
		// TODO Auto-generated method stub
		return this.baseDao.update("level=:level", "user_name=:user_name", HQLUtils.generalMapParams(
				new String[] { "level", "user_name" }, new Object[] { form.getLevel(), form.getUser_name() }));
	}

	@Override
	public int updateInfo(User form) {
		// TODO Auto-generated method stub
		return this.baseDao
				.update("real_name=:real_name,password=:password,filter_ip=:filter_ip,is_filter=:is_filter,enable=:enable",
						"user_name=:user_name",
						HQLUtils.generalMapParams(
								new String[] { "real_name", "password", "filter_ip", "is_filter", "enable",
										"user_name" },
								new Object[] { form.getReal_name(), form.getPassword(), form.getFilter_ip(),
										form.getIs_filter(), form.getEnable(), form.getUser_name() }));
	}

	@Override
	public int deleteUserAndChannel(String user_name) {
		// TODO Auto-generated method stub
		if (user_name != null) {
			channelDao.deleteByUserName(user_name);
			return this.baseDao.deleteById(user_name);
		}

		return 0;
	}

	@Override
	public void updateLastLoginInfo(String user_name, String ip, Date date) {
		// TODO Auto-generated method stub
		this.baseDao.update("last_login_ip=:last_login_ip,last_login_time=:last_login_time", "user_name=:user_name",
				HQLUtils.generalMapParams(new String[] { "last_login_ip", "last_login_time", "user_name" },
						new Object[] { ip, date, user_name }));
	}
}

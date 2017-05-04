package com.jet.vframe.sys.user.service;

import java.util.Date;

import com.jet.vframe.sys.base.service.BaseService;
import com.jet.vframe.sys.user.ModifyPw;
import com.jet.vframe.sys.user.pojo.User;

public interface UserService extends BaseService<User> {
	/**
	 * 判断用户登录是否成功
	 * 
	 * @param userName
	 * @param password
	 * @return 登录成功返回用户对象 , 登录失败返回null
	 */
	public User login(String userName, String password);

	public boolean modifyPassword(ModifyPw modifyPw);

	public boolean isExist(String userName);

	public int updateLevel(User form);

	public int updateInfo(User form);

	public int deleteUserAndChannel(String user_name);

	public void updateLastLoginInfo(String user_name, String ip, Date date);
}

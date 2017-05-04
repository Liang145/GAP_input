package com.jet.vframe.appwebmin.sysconfig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.jet.vframe.appwebmin.sysconfig.dao.RoleDao;
import com.jet.vframe.appwebmin.sysconfig.pojo.Role;
import com.jet.vframe.sys.base.service.BaseService;


public interface  RoleService extends BaseService<Role>{
	
	void addRole(String keyword);
	
	void updateRole(Role role);
	
	boolean isExist(String keyword);
	
	int deleteRoleKeyword(String keyword);
	
	Role queryById(String id);
	
	List<String> getCsvList();
	
}

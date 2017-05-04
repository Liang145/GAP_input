package com.jet.vframe.appwebmin.sysconfig.dao;


import com.jet.vframe.appwebmin.sysconfig.pojo.Role;
import com.jet.vframe.sys.base.dao.BaseDao;



public interface RoleDao extends BaseDao<Role>{

	int deleteRoleByKeyword(String keyword);

	

}

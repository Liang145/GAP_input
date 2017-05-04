package com.jet.vframe.appwebmin.sysconfig.dao.impl;


import org.springframework.stereotype.Repository;

import com.jet.vframe.appwebmin.sysconfig.dao.RoleDao;
import com.jet.vframe.appwebmin.sysconfig.pojo.Role;
import com.jet.vframe.sys.base.dao.impl.BaseDaoImpl;
import com.jet.vframe.sys.tool.HQLUtils;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{

	@Override
	public int deleteRoleByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return this.batchDelete("keyword=:keyword", HQLUtils.generalMapParamsSingle("keyword", keyword));
	}
}

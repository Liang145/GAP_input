package com.jet.vframe.appwebmin.sysconfig.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jet.vframe.appwebmin.sysconfig.dao.RoleDao;
import com.jet.vframe.appwebmin.sysconfig.pojo.Role;
import com.jet.vframe.appwebmin.sysconfig.service.RoleService;
import com.jet.vframe.sys.base.service.impl.BaseServiceImpl;
import com.jet.vframe.sys.tool.HQLUtils;


@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public void addRole(String keyword){
		Role role = new Role();
		role.setKeyword(keyword);
		this.baseDao.insert(role);
	}
	
	@Override
	public void updateRole(Role role){
		this.baseDao.update(role);
	}
	
	
	@Override
	public Role queryById(String id){
        return baseDao.queryById(id);
	}
	
	@Override
	public List<String> getCsvList() {
		List<String> csvList = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<Object[]> objList = this.baseDao.queryFields(
				new String[] { "id","keyword"}, null,
				null, null);
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
	
	@Override
	public int deleteRoleKeyword(String keyword){
		if (keyword != null) {
			roleDao.deleteRoleByKeyword(keyword);
			return this.baseDao.deleteById(keyword);
		}

		return 0;
	}
	
	@Override
	public boolean isExist(String keyword) {
		// TODO Auto-generated method stub

		return this.baseDao.queryCount(new String[] { "keyword" }, "keyword=:keyword",
				HQLUtils.generalMapParams(new String[] { "keyword" }, new Object[] { keyword })) > 0;
	}
}

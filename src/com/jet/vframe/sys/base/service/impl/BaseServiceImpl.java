package com.jet.vframe.sys.base.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.jet.vframe.sys.base.dao.BaseDao;
import com.jet.vframe.sys.base.service.BaseService;
import com.jet.vframe.sys.func.datagrid.DataGrid;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	@Autowired
	protected BaseDao<T> baseDao;

	public BaseServiceImpl() {
		super();
	}

	@Override
	public void insert(T t) {
		// TODO Auto-generated method stub
		baseDao.insert(t);
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		baseDao.delete(t);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return baseDao.deleteById(id);
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		baseDao.update(t);
	}

	@Override
	public T queryById(String id) {
		// TODO Auto-generated method stub
		return baseDao.queryById(id);
	}

	@Override
	public void saveOrUpdate(T t) {
		// TODO Auto-generated method stub
		this.baseDao.saveOrUpdate(t);
	}

	@Override
	public DataGrid<T> getDataGridByPage(String where, String orderBy, Map<String, Object> params, int offset, int length) {
		// TODO Auto-generated method stub

		return this.getDataGridByPage(null, where, orderBy, params, offset, length);
	}

	@Override
	public DataGrid<T> getDataGridByPage(String[] fields, String where, String orderBy, Map<String, Object> params, int offset, int length) {
		// TODO Auto-generated method stub
		List<T> aaData = baseDao.queryPage(fields, where, orderBy, params, offset, length);
		long count = baseDao.queryCount(null, where, params);
		DataGrid<T> dataGrid = new DataGrid<T>();
		dataGrid.setAaData(aaData);
		dataGrid.setiDisplayStart(offset);
		dataGrid.setiDisplayLength(length);
		dataGrid.setiTotalDisplayRecords(count);
		dataGrid.setiTotalRecords(count);
		return dataGrid;
	}

	@Override
	public DataGrid<T> getAllByPage(int offset, int length) {
		// TODO Auto-generated method stub
		return this.getDataGridByPage(null, null, null, offset, length);
	}
	
	@Override
	public DataGrid<T> getNull() {
		// TODO Auto-generated method stub
		DataGrid<T> dataGrid = new DataGrid<T>();
		//dataGrid.setAaData(aaData);
		dataGrid.setiDisplayStart(0);
		dataGrid.setiDisplayLength(0);
		dataGrid.setiTotalDisplayRecords(0);
		dataGrid.setiTotalRecords(0);
		return dataGrid;
	}
	
}

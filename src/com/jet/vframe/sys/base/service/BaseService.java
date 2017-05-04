package com.jet.vframe.sys.base.service;

import java.util.Map;

import com.jet.vframe.sys.func.datagrid.DataGrid;

public abstract interface BaseService<T> {
	void insert(T t);

	void delete(T t);

	int deleteById(String id);

	void update(T t);

	void saveOrUpdate(T t);

	T queryById(String id);

	DataGrid<T> getDataGridByPage(String where, String orderBy, Map<String, Object> params, int offset, int length);

	DataGrid<T> getDataGridByPage(String[] fields, String where, String orderBy, Map<String, Object> params, int offset, int length);

	DataGrid<T> getAllByPage(int offset, int length);
	
	DataGrid<T> getNull();

}

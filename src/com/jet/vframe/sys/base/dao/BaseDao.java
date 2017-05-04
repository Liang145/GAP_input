package com.jet.vframe.sys.base.dao;

import java.util.List;
import java.util.Map;

public abstract interface BaseDao<T> {
	void insert(T t);

	void delete(T t);

	int deleteById(String id);

	@SuppressWarnings("rawtypes")
	List query(String select, String where, String groupBy, String orderBy, Map<String, Object> params);

	int batchDelete(String where, Map<String, Object> params);

	void update(T t);

	void saveOrUpdate(T t);

	T queryById(String id);

	/**
	 * 杩斿洖:鍗曚釜瀹炰緥鎴栬�卬ull, //褰撹繑鍥炵殑瀹炰緥澶т簬涓�涓殑鏃跺�欑殑鎶涘嚭NonUniqueResultException
	 * 
	 * @param fields
	 * @param where
	 * @param params
	 * @return
	 */
	Object queryUnique(String[] fields, String where, Map<String, Object> params);

	/**
	 * 鎸塷rderBy鎺掑簭瀛楁鏌ヨ绗琲ndex鏉¤褰�
	 * 
	 * @param fields
	 * @param where
	 * @param orderBy
	 * @param index
	 * @param params
	 * @return
	 */
	T queryUniqueBySort(String[] fields, String where, String orderBy, int index, Map<String, Object> params);

	/**
	 * 鏌ヨ澶氫釜璁板綍涓殑绗竴鏉¤褰�
	 * 
	 * @param fields
	 * @param where
	 * @param params
	 * @return
	 */
	T queryTopOne(String[] fields, String where, Map<String, Object> params);

	List<T> queryAll();

	List<T> query(String where, String orderBy, Map<String, Object> params);

	@SuppressWarnings("rawtypes")
	List queryFields(String[] fields, String where, String orderBy, Map<String, Object> params);

	@SuppressWarnings("rawtypes")
	List queryField(String fields, String where, String orderBy, Map<String, Object> params);

	/**
	 * 鏌ヨ榛樿绫诲瀷T.class鐨勫垎椤垫暟鎹紝榛樿绫诲瀷T.class闇�鎻愪緵鍜宖ields瀵瑰簲鐨勬瀯閫犲嚱鏁�
	 * @param fields
	 * @param where
	 * @param orderBy
	 * @param params
	 * @param offset
	 * @param length
	 * @return 杩斿洖缁撴灉涓洪粯璁ょ被鍨婽.class鐨凩IST
	 */
	List<T> queryPage(String[] fields, String where, String orderBy, Map<String, Object> params, int offset, int length);

	/**
	 * 鏌ヨ浠绘剰鎸囧畾绫诲瀷鐨勫垎椤垫暟鎹�
	 * @param fields
	 * @param where
	 * @param orderBy
	 * @param params
	 * @param offset
	 * @param length
	 * @return 杩斿洖缁撴灉涓烘寚瀹氱被鍨嬬殑LIST
	 */
	@SuppressWarnings("rawtypes")
	List queryPage(String fields, String where, String orderBy, Map<String, Object> params, int offset, int length);

	long queryCount(String[] fields, String where, Map<String, Object> params);

	@SuppressWarnings("rawtypes")
	List querySumByGroup(String sumField, String where, Map<String, Object> params, String group);

	@SuppressWarnings("rawtypes")
	List queryByGroup(String[] fields, String where, Map<String, Object> params, String[] groups, String[] orderBys);

	@SuppressWarnings("rawtypes")
	List querySum(String[] sumFields, String where, Map<String, Object> params);
	
	Object querySum(String sumField, String where, Map<String, Object> params);

	int update(String set, String where, Map<String, Object> params);

	Object queryUnique(String fieldString, String where, Map<String, Object> params);

	@SuppressWarnings("rawtypes")
	List queryByHQL(String hql, Map<String, Object> params);

	int excuteSQL(String sql);

}

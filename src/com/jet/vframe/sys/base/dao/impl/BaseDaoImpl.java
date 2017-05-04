package com.jet.vframe.sys.base.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.AbstractEntityPersister;

import com.jet.vframe.sys.base.dao.BaseDao;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	private Class<T> entityClass;

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		super();
		this.entityClass = (Class<T>) (((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0]);
	}

	public BaseDaoImpl(Class<T> clazz) {
		this.entityClass = clazz;
	}

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	@Override
	public void insert(T t) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(t);
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub

		AbstractEntityPersister classMetadata = (AbstractEntityPersister) sessionFactory
				.getClassMetadata(this.entityClass);
		String idPropertyName = classMetadata.getIdentifierPropertyName();
		StringBuilder hql = new StringBuilder("DELETE FROM ");
		hql.append(this.entityClass.getCanonicalName());
		hql.append(" WHERE ");
		hql.append(idPropertyName);
		hql.append(" = :");
		hql.append(idPropertyName);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(idPropertyName, id);
		this.setQueryParams(query, params);
		return query.executeUpdate();
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(t);

	}

	@Override
	public void saveOrUpdate(T t) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T queryById(String id) {
		// TODO Auto-generated method stub
		return (T) sessionFactory.getCurrentSession().get(this.entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryAll() {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(entityClass.getCanonicalName());
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> query(String where, String orderBy, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return queryField(null, where, orderBy, params);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List queryFields(String[] fields, String where, String orderBy, Map<String, Object> params) {
		// TODO Auto-generated method stub
		StringBuilder fieldsStr = null;
		if (fields != null && fields.length > 0) {
			fieldsStr = new StringBuilder();
			for (int i = 0; i < fields.length; i++) {
				fieldsStr.append(fields[i]);
				fieldsStr.append(",");
			}
			fieldsStr.deleteCharAt(fieldsStr.length() - 1);
		}

		return queryField(fieldsStr.toString(), where, orderBy, params);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List queryField(String fields, String where, String orderBy, Map<String, Object> params) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();

		if (fields != null && fields.length() > 0) {
			hql.append("select ");
			hql.append(fields);
		}

		hql.append(" from ");

		hql.append(entityClass.getCanonicalName());
		if (where != null) {
			hql.append(" WHERE ");
			hql.append(where);
		}
		if (orderBy != null) {
			hql.append(" order by ");
			hql.append(orderBy);
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if (params != null) {
			this.setQueryParams(query, params);
		}
		return query.list();
	}

	@Override
	public long queryCount(String[] fields, String where, Map<String, Object> params) {
		// TODO Auto-generated method stub

		StringBuilder countHql = new StringBuilder();
		countHql.append("select count(");
		if (fields != null) {
			for (String f : fields) {
				countHql.append(f);
				countHql.append(",");
			}
			countHql.deleteCharAt(countHql.length() - 1);
		} else {
			countHql.append("*");
		}
		countHql.append(") from ");
		countHql.append(entityClass.getCanonicalName());
		if (where != null) {
			countHql.append(" WHERE ");
			countHql.append(where);
		}

		Query query = sessionFactory.getCurrentSession().createQuery(countHql.toString());
		if (params != null) {
			this.setQueryParams(query, params);
		}

		long totalCount = ((Number) query.uniqueResult()).longValue();
		return totalCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryPage(String[] fields, String where, String orderBy, Map<String, Object> params, int offset,
			int length) {
		// TODO Auto-generated method stub
		String fieldString = null;
		if (fields != null && fields.length > 0) {
			StringBuilder builder = new StringBuilder("");
			builder.append("new ");
			builder.append(entityClass.getCanonicalName());
			builder.append("(");
			for (int i = 0; i < fields.length; i++) {
				builder.append(fields[i]);
				builder.append(",");
			}
			builder.deleteCharAt(builder.length() - 1);
			builder.append(")");
			fieldString = builder.toString();
		}
		return queryPage(fieldString, where, orderBy, params, offset, length);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List queryPage(String fields, String where, String orderBy, Map<String, Object> params, int offset,
			int length) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder("");
		if (fields != null && fields.length() > 0) {
			hql.append("select ");
			hql.append(fields);
		}

		hql.append(" from ");
		hql.append(entityClass.getCanonicalName());
		if (where != null) {
			hql.append(" WHERE ");
			hql.append(where);
		}
		if (orderBy != null) {
			hql.append(" order by ");
			hql.append(orderBy);
		}

		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if (params != null) {
			setQueryParams(query, params);
		}

		query.setFirstResult(offset);
		query.setMaxResults(length);
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	private void setQueryParams(Query query, Map<String, Object> params) {
		Iterator<Entry<String, Object>> iter = params.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Object> entry = iter.next();
			if (entry.getValue() instanceof Collection) {
				query.setParameterList(entry.getKey().toString(), (Collection) entry.getValue());
			} else if (entry.getValue() instanceof Object[]) {
				query.setParameterList(entry.getKey().toString(), (Object[]) entry.getValue());
			} else {
				query.setParameter(entry.getKey().toString(), entry.getValue());
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sys.base.dao.BaseDao#queryByGroup(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */

	@SuppressWarnings("rawtypes")
	@Override
	public List querySumByGroup(String sumField, String where, Map<String, Object> params, String group) {
		// TODO Auto-generated method stub
		if (group == null)
			return null;
		StringBuilder hql = new StringBuilder("SELECT ");

		if (sumField != null) {
			hql.append("SUM(");
			hql.append(sumField);
			hql.append(") ");
			hql.append(",");
		}
		hql.append(group);
		// hql.deleteCharAt(hql.length() - 1);

		hql.append(" FROM ");
		hql.append(entityClass.getCanonicalName());
		if (where != null) {
			hql.append(" WHERE ");
			hql.append(where);
		}
		hql.append(" GROUP BY ");
		hql.append(group);
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if (params != null) {
			setQueryParams(query, params);
		}
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List queryByGroup(String[] fields, String where, Map<String, Object> params, String[] groups,
			String[] orderBys) {
		// TODO Auto-generated method stub
		if (groups == null)
			return null;
		StringBuilder hql = new StringBuilder();
		if (fields != null) {
			hql.append("SELECT ");
			for (String field : fields) {
				hql.append(field);
				hql.append(",");
			}
			hql.deleteCharAt(hql.length() - 1);
		}

		hql.append(" FROM ");
		hql.append(entityClass.getCanonicalName());
		if (where != null) {
			hql.append(" WHERE ");
			hql.append(where);
		}

		hql.append(" GROUP BY ");
		for (String group : groups) {
			hql.append(group);
			hql.append(",");
		}
		hql.deleteCharAt(hql.length() - 1);

		hql.append(" ORDER BY ");
		for (String orderBy : orderBys) {
			hql.append(orderBy);
			hql.append(",");
		}
		hql.deleteCharAt(hql.length() - 1);

		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if (params != null) {
			setQueryParams(query, params);
		}
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List querySum(String[] sumFields, String where, Map<String, Object> params) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();
		if (sumFields != null) {
			hql.append("SELECT ");
			for (String sumField : sumFields) {
				hql.append("SUM(");
				hql.append(sumField);
				hql.append(") ");
				hql.append(",");
			}
			hql.deleteCharAt(hql.length() - 1);
		} else {
			return null;
		}

		hql.append(" FROM ");
		hql.append(entityClass.getCanonicalName());
		if (where != null) {
			hql.append(" WHERE ");
			hql.append(where);
		}

		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if (params != null) {
			setQueryParams(query, params);
		}
		return query.list();
	}

	@Override
	public Object querySum(String sumField, String where, Map<String, Object> params) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();
		if (sumField != null) {
			hql.append("SELECT SUM(");
			hql.append(sumField);
			hql.append(") ");
		} else {
			return null;
		}

		hql.append(" FROM ");
		hql.append(entityClass.getCanonicalName());
		if (where != null) {
			hql.append(" WHERE ");
			hql.append(where);
		}

		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if (params != null) {
			setQueryParams(query, params);
		}
		return query.uniqueResult();
	}

	@Override
	public int update(String set, String where, Map<String, Object> params) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();

		hql.append("UPDATE ");
		hql.append(entityClass.getCanonicalName());
		if (set != null && set.length() > 0) {
			hql.append(" SET ");
			hql.append(set);
		} else {
			return 0;
		}
		if (where != null) {
			hql.append(" WHERE ");
			hql.append(where);
		}

		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if (params != null) {
			this.setQueryParams(query, params);
		}
		return query.executeUpdate();
	}

	@Override
	public Object queryUnique(String[] fields, String where, Map<String, Object> params) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();

		if (fields != null && fields.length > 0) {
			hql.append("select ");
			for (int i = 0; i < fields.length; i++) {
				hql.append(fields[i]);
				hql.append(",");
			}
			hql.deleteCharAt(hql.length() - 1);
		}

		hql.append(" from ");

		hql.append(entityClass.getCanonicalName());
		if (where != null) {
			hql.append(" WHERE ");
			hql.append(where);
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if (params != null) {
			this.setQueryParams(query, params);
		}
		return query.uniqueResult();
	}

	@Override
	public int batchDelete(String where, Map<String, Object> params) {
		// TODO Auto-generated method stub

		StringBuilder hql = new StringBuilder("DELETE ");
		hql.append(this.entityClass.getCanonicalName());
		if (where != null) {
			hql.append(" WHERE ");
			hql.append(where);
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if (null != params) {
			this.setQueryParams(query, params);
		}
		return query.executeUpdate();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List query(String select, String where, String groupBy, String orderBy, Map<String, Object> params) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();
		if (select != null) {
			hql.append("SELECT ");
			hql.append(select);
			hql.append(" ");
		}

		hql.append("FROM ");
		hql.append(entityClass.getCanonicalName());
		if (where != null) {
			hql.append(" WHERE ");
			hql.append(where);
			hql.append(" ");
		}

		if (groupBy != null) {
			hql.append(" GROUP BY ");
			hql.append(groupBy);
			hql.append(" ");
		}

		if (orderBy != null) {
			hql.append(" ORDER BY ");
			hql.append(orderBy);
		}

		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if (params != null) {
			setQueryParams(query, params);
		}
		return query.list();
	}

	@Override
	public T queryTopOne(String[] fields, String where, Map<String, Object> params) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder("");
		if (fields != null && fields.length > 0) {
			// hql.append("select ");
			hql.append("select new ");
			hql.append(entityClass.getCanonicalName());
			hql.append("(");
			for (int i = 0; i < fields.length; i++) {
				hql.append(fields[i]);
				hql.append(",");
			}
			hql.deleteCharAt(hql.length() - 1);
			hql.append(")");
		}

		hql.append(" from ");
		hql.append(entityClass.getCanonicalName());
		if (where != null) {
			hql.append(" WHERE ");
			hql.append(where);
		}

		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if (params != null) {
			setQueryParams(query, params);
		}

		query.setFirstResult(0);
		query.setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public Object queryUnique(String fieldString, String where, Map<String, Object> params) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();

		if (fieldString != null && fieldString.length() > 0) {
			hql.append("select ");
			hql.append(fieldString);

		}

		hql.append(" from ");

		hql.append(entityClass.getCanonicalName());
		if (where != null) {
			hql.append(" WHERE ");
			hql.append(where);
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if (params != null) {
			this.setQueryParams(query, params);
		}
		return query.uniqueResult();
	}

	@Override
	public T queryUniqueBySort(String[] fields, String where, String orderBy, int index, Map<String, Object> params) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder("");
		if (fields != null && fields.length > 0) {
			// hql.append("select ");
			hql.append("select new ");
			hql.append(entityClass.getCanonicalName());
			hql.append("(");
			for (int i = 0; i < fields.length; i++) {
				hql.append(fields[i]);
				hql.append(",");
			}
			hql.deleteCharAt(hql.length() - 1);
			hql.append(")");
		}

		hql.append(" from ");
		hql.append(entityClass.getCanonicalName());
		if (where != null) {
			hql.append(" WHERE ");
			hql.append(where);
		}
		if (orderBy != null) {
			hql.append(" order by ");
			hql.append(orderBy);
		}

		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		if (params != null) {
			setQueryParams(query, params);
		}

		query.setFirstResult(index - 1);
		query.setMaxResults(index);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List queryByHQL(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if (params != null) {
			setQueryParams(query, params);
		}
		return query.list();
	}

	@Override
	public int excuteSQL(String sql) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return query.executeUpdate();
	}

}

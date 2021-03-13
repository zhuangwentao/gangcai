package com.gc.common.persistence.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
/**
 * File Name:PageResult.java
 * Package Name:com.wenxing.constants
 * Date:Jun 24, 20153:36:30 PM
*/
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gc.common.persistence.BaseDao;
import com.gc.common.web.PageResult;

/**
 * 
 * @author zhuang_wt
 *
 * @param <T>
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

	private final Logger log = LoggerFactory.getLogger(super.getClass());

	private Class<T> entityClass;

	@PersistenceContext(unitName="pstUnit")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		//判断T为空
		if (!(ParameterizedType.class.isAssignableFrom(super.getClass()
				.getGenericSuperclass().getClass())))
			return;
		Type[] actualTypeArguments = ((ParameterizedType) super.getClass()
				.getGenericSuperclass()).getActualTypeArguments();
		this.entityClass = (Class<T>) actualTypeArguments[0];
	}

//	private void addAuditInfo(T obj) {
//		if (obj instanceof AuditEntity) {
//			AuditEntity obj1 = (AuditEntity) obj;
//
//			Long id = obj1.getId();
//			if ((id == null) || (id.longValue() <= 0L)) {
//				// TODO 设置当前登录用户的ID
//				obj1.setCreatedById(1L);
//				if (obj1.getCreatedDate() == null) {
//					obj1.setCreatedDate(Calendar.getInstance().getTime());
//				}
//			}
//			// TODO 设置当前登录用户的ID
//			obj1.setUpdatedById(1L);
//			obj1.setUpdatedDate(Calendar.getInstance().getTime());
//		}
//	}

	public T findById(int id) {
		return entityManager.find(this.entityClass, id);
	}

	public T findByIdNoWaitLock(int id) {
		return entityManager.find(this.entityClass, id,
				LockModeType.PESSIMISTIC_READ);
	}

	public List<T> findAll() {
		return findEntityAll(this.entityClass);
	}

	public void eagerLoad(T obj) {
		// TODO Auto-generated method stub

	}

	public void evict(T obj) {
		// TODO Auto-generated method stub

	}

	public void updateHQL(String hql, Map<String, ?> params) {
		setQueryParam(entityManager.createQuery(hql), params).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(String queryName, Map<String, Object> params) {
		Query query = entityManager.createNamedQuery(queryName);
		return setQueryParam(query, params).getResultList();
	}

	public void flush() {
		entityManager.flush();
	}

	public void clear() {
		entityManager.clear();
	}

	public void lock(T entity, LockModeType lockMode) {
		entityManager.lock(entity, lockMode);
	}

	@SuppressWarnings({ "rawtypes" })
	public int nativeQueryCountSQL(String countSql, Map<String, ?> params) {
		List list = setQueryParam(
				entityManager.createNativeQuery(countSql), params)
				.getResultList();
		if (list != null && !list.isEmpty()) {
			if(list.get(0) != null){
				return ((Integer) list.get(0)).intValue();
			}
			
		}
		return 0;
	}
	
	@SuppressWarnings({ "rawtypes" })
	public Long nativeQueryBigCountSQL(String countSql, Map<String, ?> params) {
		List list = setQueryParam(
				entityManager.createNativeQuery(countSql), params)
				.getResultList();
		if (list != null && !list.isEmpty()) {
			if(list.get(0) != null){
				return ((BigInteger) list.get(0)).longValue();
			}
			
		}
		return 0L;
	}

    @SuppressWarnings({ "rawtypes" })
    public Double nativeQueryDoubleCountSQL(String countSql, Map<String, ?> params) {
        List list = setQueryParam(
                entityManager.createNativeQuery(countSql), params)
                .getResultList();
        if (list != null && !list.isEmpty()) {
            if(list.get(0) != null){
                return ((Double) list.get(0));
            }

        }
        return 0d;
    }
	
	@SuppressWarnings({ "rawtypes" })
	public int nativeQueryCountSQLNew(String countSql, Map<String, ?> params) {
		List list = setQueryParam(
				entityManager.createNativeQuery(countSql), params)
				.getResultList();
		if (list != null && !list.isEmpty()) {
			Integer result = (Integer)list.get(0);
			return result.intValue();
		}
		return 0;
	}

	@SuppressWarnings({ "unchecked" })
	public PageResult<T> nativeQuerySQL(String sql, String countSql,
			Map<String, ?> params, int offsetIndex, int pageSize) {
		PageResult<T> pr = new PageResult<T>();

		pr.setCurrentIndex(offsetIndex);
		pr.setPageSize(pageSize);

		if (countSql != null) {
			pr.setTotalCount(nativeQueryCountSQL(countSql, params));
		}

		if (pageSize != 0) {
			pr.setTotalPage((pr.getTotalCount() + pageSize - 1) / pageSize);
			pr.setCurrentPage((offsetIndex + pageSize) / pageSize);
		}
		Query query = setQueryParam(
				entityManager
						.createNativeQuery(sql, this.entityClass), params)
				.setFirstResult(offsetIndex);
		if (pageSize != 0)
			query.setMaxResults(pageSize);
		pr.setContent(query.getResultList());
		return pr;
	}
	@SuppressWarnings({ "unchecked" })
	public PageResult<Object[]> nativeQuerySQLObject(String sql, String countSql,
			Map<String, ?> params, int offsetIndex, int pageSize) {
		PageResult<Object[]> pr = new PageResult<Object[]>();
		
		pr.setCurrentIndex(offsetIndex);
		pr.setPageSize(pageSize);
		
		if (countSql != null) {
			pr.setTotalCount(nativeQueryBigCountSQL(countSql, params).intValue());
		}
		
		if (pageSize != 0) {
			pr.setTotalPage((pr.getTotalCount() + pageSize - 1) / pageSize);
			pr.setCurrentPage((offsetIndex + pageSize) / pageSize);
		}
		Query query = setQueryParam(
				entityManager
				.createNativeQuery(sql), params)
				.setFirstResult(offsetIndex);
		if (pageSize != 0)
			query.setMaxResults(pageSize);
		pr.setContent(query.getResultList());
		return pr;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> nativeQuerySQLN(String sql,Map<String, ?> params) {
		Query query = setQueryParam(entityManager.createNativeQuery(sql, this.entityClass), params);
		return query.getResultList();
	}

	public int nativeUpdateSQL(String sql, Map<String, ?> params) {
		return setQueryParam(entityManager.createNativeQuery(sql),
				params).executeUpdate();
	}

	@SuppressWarnings({ "unchecked" })
	public List<T> query(String hql, Map<String, ?> params) {
		return setQueryParam(entityManager.createQuery(hql), params)
				.getResultList();
	}

	@SuppressWarnings({ "unchecked" })
	public PageResult<T> query(String hql, String countHql,
			Map<String, ?> params, int offsetIndex, int pageSize) {
		PageResult<T> pr = new PageResult<T>();

		pr.setCurrentIndex(offsetIndex);
		pr.setPageSize(pageSize);

		if (countHql != null) {
			pr.setTotalCount(queryCount(countHql, params));
		}

		if (pageSize != 0) {
			pr.setTotalPage((pr.getTotalCount() + pageSize - 1) / pageSize);
			pr.setCurrentPage((offsetIndex + pageSize) / pageSize);
		}
		Query query = setQueryParam(entityManager.createQuery(hql),
				params).setFirstResult(offsetIndex);
		if (pageSize != 0)
			query.setMaxResults(pageSize);
		pr.setContent(query.getResultList());
		return pr;
	}

	@SuppressWarnings("rawtypes")
	public int queryCount(String countHql, Map<String, ?> params) {
		List list = setQueryParam(
				entityManager.createQuery(countHql), params)
				.getResultList();
		if (list != null && !list.isEmpty()) {
			return ((Long) list.get(0)).intValue();
		}
		return 0;
	}

	/**
	 * 保存一个新对象
	 */
	public void save(T obj) {
		entityManager.persist(obj);
	}

	public void saveObjects(Collection<T> objs) {
		for (T obj : objs) {
			save(obj);
		}
	}

	public T mergeObject(T obj) {

        T result = entityManager.merge(obj);

        return result;
	}

	public void remove(T obj) {
		entityManager.remove(obj);
	}

	public void removeObjects(Collection<T> objs) {
		for (T obj : objs) {
			this.remove(obj);
		}
	}

	@SuppressWarnings({ "unchecked" })
	public List<T> findEntityAll(Class<T> clazz) {
		return entityManager.createQuery(" from " + clazz.getName())
				.getResultList();
	}

	public int updateByNamedQuery(String queryName, Map<String, Object> params) {
		return setQueryParam(
				entityManager.createNamedQuery(queryName), params)
				.executeUpdate();
	}

	/**
	 * 更新对象
	 */
	public void update(T obj) {
		entityManager.merge(obj);
	}

//	public void update(T obj, Date updateDate) {
//		
//		this.updateEntity((AuditEntity) obj, updateDate);
//	}

	public void updateObjects(Collection<T> objs) {
		for (Iterator<T> localIterator = objs.iterator(); localIterator
				.hasNext();) {
			Object obj = localIterator.next();
			entityManager.merge(obj);
		}

	}

//	public void updateObjects(Collection<T> objs, Date updateDate) {
//		for (Iterator<T> localIterator = objs.iterator(); localIterator
//				.hasNext();) {
//			Object obj = localIterator.next();
//			updateEntity((AuditEntity) obj, updateDate);
//		}
//	}

	public long getsSequenceNextval(String sequenceName) {
		String sql = "select " + sequenceName + ".Nextval as seq from dual";
		return ((Long) entityManager.createNativeQuery(sql)
				.getResultList().get(0)).longValue();
	}

	public List<String> getTables(String schemaPattern, String tableNamePattern) {
		List<String> tables = new ArrayList<String>();

		try {
			Connection con = entityManager.unwrap(
					java.sql.Connection.class);
			DatabaseMetaData meta = con.getMetaData();
			ResultSet rs = meta.getTables(null, schemaPattern,
					tableNamePattern, new String[] { "TABLE" });
			while (rs.next())
				tables.add(rs.getString(3));
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}

		return tables;
	}

	public List<String> getColumns(String tableName) {
		List<String> columns = new ArrayList<String>();

		String sql = "SELECT * FROM " + tableName + " WHERE 1 != 1";

		try {
			Connection con = entityManager.unwrap(
					java.sql.Connection.class);
			ResultSet rs = con.createStatement().executeQuery(sql);
			ResultSetMetaData meta = rs.getMetaData();
			int cols = meta.getColumnCount();
			for (int i = 1; i <= cols; ++i)
				columns.add(meta.getColumnName(i));
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}

		return columns;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	private Query setQueryParam(Query query, Map<String, ?> params) {
		if (params != null) {
			Set<String> set = params.keySet();
			for (String key : set) {
				query.setParameter(key, params.get(key));
			}
		}
		return query;
	}

//	private void updateEntity(AuditEntity obj, Date updateDate) {
//		if (updateDate == null) {
//			obj.setUpdatedDate(Calendar.getInstance().getTime());
//		} else {
//			obj.setUpdatedDate(updateDate);
//		}
//
//		entityManager.merge(obj);
//	}

	@SuppressWarnings("unchecked")
	public List<Object[]> nativeQuerySQL(String sql, Map<String, ?> params) {
		return setQueryParam(entityManager.createNativeQuery(sql),
				params).getResultList();
	}

	public Object nativeQuerySQLSingleResult(String sql, Map<String, ?> params) {
		return setQueryParam(entityManager.createNativeQuery(sql),
				params).getSingleResult();
	}

	@Override
	public Query nativeQuerySQLToQuery(String sql, Map<String, ?> params) {
		return setQueryParam(entityManager.createNativeQuery(sql),
				params);
	}

	@Override
	public T get(String hql) {
		Query q = this.entityManager.createQuery(hql);
		List<T> l = q.getResultList();
		if ((l != null) && (l.size() > 0)) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public T get(String hql, Map<String, Object> params) {
		Query q = entityManager.createQuery(hql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.getResultList();
		if ((l != null) && (l.size() > 0)) {
			return l.get(0);
		}
		return null;
	}


}

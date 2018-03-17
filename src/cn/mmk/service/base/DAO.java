package cn.mmk.service.base;

import java.io.Serializable;
import java.util.LinkedHashMap;

import cn.mmk.bean.QueryResult;

public interface DAO<T> {
	/**
	 * ��ȡ��¼����
	 * @param entityClass ʵ����
	 * @return
	 */
	public long getCount();
	/**
	 * ���һ�����������
	 */
	public void clear();
	/**
	 * ����ʵ��
	 * @param entity ʵ��id
	 */
	public void save(T entity);
	/**
	 * ����ʵ��
	 * @param entity ʵ��id
	 */
	public void update(T entity);
	/**
	 * ɾ��ʵ��
	 * @param entityClass ʵ����
	 * @param entityids ʵ��id����
	 */
	public void delete(Serializable ... entityids);
	/**
	 * ��ȡʵ��
	 * @param <T>
	 * @param entityClass ʵ����
	 * @param entityId ʵ��id
	 * @return
	 */
	public T find(Serializable entityId);
	/**
	 * ��ȡ��ҳ����
	 * @param <T>
	 * @param entityClass ʵ����
	 * @param firstindex ��ʼ����
	 * @param maxresult ��Ҫ��ȡ�ļ�¼��
	 * @return
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby);
	
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams);
	
	public QueryResult<T> getScrollData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby);
	
	public QueryResult<T> getScrollData(int firstindex, int maxresult);
	
	public QueryResult<T> getScrollData();
}

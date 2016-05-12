package com.sys.base;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sys.base.dto.PageResult;
import com.sys.base.dto.QueryParam;
import com.sys.common.AppExpection;


/**
 * @author chenchuan
 * @date 2016��1��22��
 * @param <T>
 * �������ݷ��ʶ���ӿ�
 */
public interface BaseDao<T> {
	public JdbcTemplate getJdbcTemplate();
	/**
	 * ��������
	 * 
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * ����T
	 * 
	 * @return
	 */
	public List<T> find(T t);
	/**
	 * ����T
	 * 
	 * @return
	 */
	public T findById(Integer id);

	/**
	 * ��������
	 * 
	 * @return
	 */
	public List<T> query(String sql);
	/**
	 * ͨ��IDɾ��
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(T t);
	/**
	 * ���ݿ�IDɾ��
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteDB(T t);

	/**
	 * ����һ������
	 * 
	 * @param t
	 * @return
	 */
	public int save(String sql,Object...params);
	/**
	 * ����һ������
	 * 
	 * @param t
	 * @return
	 */
	public List<T> save(T t);

	/**
	 * ����
	 * 
	 * @param t
	 * @return
	 * @throws AppExpection
	 */
	public int update(String sql,Object... params) throws AppExpection;
	
	/**
	 * ����
	 * 
	 * @param t
	 * @return
	 * @throws AppExpection
	 */
	public int update(T t) throws AppExpection;
	
	/**
	 * ��ҳ��ѯ
	 * 
	 * @param params
	 * @return
	 */
	public PageResult<T> queryPage(QueryParam<T> params);
	
	/**
	 * �����ҳ
	 * @param params
	 * @param pageResult
	 * @param totalResult
	 * @return
	 */
	public List<T> countPage(QueryParam<T> params, PageResult<T> pageResult,List<T> totalResult) ;
	/**
	 * ��������Entity����
	 * @param t
	 * @return
	 */
	public List<String> reflectEntity(T t);
}

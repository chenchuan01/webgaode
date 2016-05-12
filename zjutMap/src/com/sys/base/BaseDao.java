package com.sys.base;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sys.base.dto.PageResult;
import com.sys.base.dto.QueryParam;
import com.sys.common.AppExpection;


/**
 * @author chenchuan
 * @date 2016年1月22日
 * @param <T>
 * 基本数据访问对象接口
 */
public interface BaseDao<T> {
	public JdbcTemplate getJdbcTemplate();
	/**
	 * 查找所有
	 * 
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 查找T
	 * 
	 * @return
	 */
	public List<T> find(T t);
	/**
	 * 查找T
	 * 
	 * @return
	 */
	public T findById(Integer id);

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	public List<T> query(String sql);
	/**
	 * 通过ID删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(T t);
	/**
	 * 数据库ID删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteDB(T t);

	/**
	 * 保存一条数据
	 * 
	 * @param t
	 * @return
	 */
	public int save(String sql,Object...params);
	/**
	 * 保存一条数据
	 * 
	 * @param t
	 * @return
	 */
	public List<T> save(T t);

	/**
	 * 更新
	 * 
	 * @param t
	 * @return
	 * @throws AppExpection
	 */
	public int update(String sql,Object... params) throws AppExpection;
	
	/**
	 * 更新
	 * 
	 * @param t
	 * @return
	 * @throws AppExpection
	 */
	public int update(T t) throws AppExpection;
	
	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	public PageResult<T> queryPage(QueryParam<T> params);
	
	/**
	 * 计算分页
	 * @param params
	 * @param pageResult
	 * @param totalResult
	 * @return
	 */
	public List<T> countPage(QueryParam<T> params, PageResult<T> pageResult,List<T> totalResult) ;
	/**
	 * 遍历返回Entity属性
	 * @param t
	 * @return
	 */
	public List<String> reflectEntity(T t);
}

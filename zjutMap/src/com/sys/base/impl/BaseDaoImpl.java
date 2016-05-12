package com.sys.base.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.SpringContextHolder;
import com.sys.base.BaseDao;
import com.sys.base.BaseEntity;
import com.sys.base.dto.PageResult;
import com.sys.base.dto.QueryParam;
import com.sys.common.AppExpection;
import com.sys.common.util.CommonUtil;
import com.sys.common.util.LogUtil;
import com.sys.common.util.StringUtil;
import com.sys.db.DBConstants;

/**
 * @author chenchuan
 * @date 2016��1��22��
 * @param <T>
 *            DAO����ʵ��
 */
public abstract class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private Class<T> clazz;
	private String tableName;
	private String orgTableName;
	private String[] timeFiled = { "activedate","operatime", "addtime"};
	private String[] timeFiledSed = { "finalDate" };

	private static final String DEL_NORMAL = DBConstants.DML_TBOF + "delflag='"
			+ DBConstants.DEL_FLAG_NORMAL + "' ";

	private static final String DEL_DELETE = DBConstants.DML_TBOF + "delflag='"
			+ DBConstants.DEL_FLAG_DELETE + "' ";
	private static final String ID_INDEX = DBConstants.DML_TBOF + "id";

	public BaseDaoImpl() {
		initClss();
	}

	/**
	 * ��ʼ����÷�������
	 */
	@SuppressWarnings("unchecked")
	private void initClss() {
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
		//û�б����
		orgTableName = DBConstants.TABLE_PREFFIX
				+ clazz.getSimpleName().toLowerCase();
		//���������
		tableName = orgTableName + DBConstants.DML_TB;
	}

	/**
	 * ��ѯ����ɾ����־Ϊ0��entity
	 */
	public List<T> findAll() {
		List<T> list = new ArrayList<T>();
		try {
			String sql = DBConstants.DML_SELECT + tableName
					+ DBConstants.DML_WHERE + DEL_NORMAL;
			RowMapper<T> rm = ParameterizedBeanPropertyRowMapper
					.newInstance(clazz);
			LogUtil.infoSql(getClass(), sql.toString());
			list = getJdbcTemplate().query(sql, rm);
		} catch (Exception e) {
			LogUtil.error(clazz, new AppExpection("��ѯ����", "��ѯȫ��[ " + tableName
					+ " ]����", e));
		}
		return list;
	}

	/**
	 * �Զ���������ѯ����
	 */
	public List<T> query(String sql) {
		List<T> list = new ArrayList<T>();
		try {
			RowMapper<T> rm = ParameterizedBeanPropertyRowMapper
					.newInstance(clazz);
			LogUtil.infoSql(getClass(), sql.toString());
			list = getJdbcTemplate().query(sql, rm);
		} catch (Exception e) {
			LogUtil.error(clazz, new AppExpection("��ѯ����", "��ѯȫ��[ " + tableName
					+ " ]����", e));
		}
		return list;
	}

	/**
	 * ԭ��sql�������
	 */
	public int save(String sql, Object... params) {
		int result = 0;
		try {
			LogUtil.infoSql(getClass(), sql.toString());
			result = getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			LogUtil.error(clazz, new AppExpection("�������", "����[ " + tableName
					+ " ]�������", e));
		}
		return result;
	}

	/**
	 * ԭ��sql���¶���
	 * 
	 * @throws AppExpection
	 */
	public int update(String sql, Object... params) throws AppExpection {
		int result = 0;
		try {
			LogUtil.infoSql(getClass(), sql.toString());
			result = getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			AppExpection app = new AppExpection("���´���", "����[ " + tableName
					+ " ]�������", e);
			throw app;
		}
		return result;
	}

	/**
	 * ɾ������
	 */
	public boolean delete(T t) {
		boolean result = false;
		try {
			if (t == null || t.getId() == null) {
				LogUtil.error(clazz, new AppExpection("ɾ������", "ɾ��[ "
						+ tableName + " ]�������;IDΪnull"));
				return result;
			}
			String sql = DBConstants.DML_UPDATE + tableName
					+ DBConstants.DML_SET + DEL_DELETE + DBConstants.DML_WHERE
					+ ID_INDEX + "=?";
			LogUtil.infoSql(getClass(), sql.toString());
			result = getJdbcTemplate().update(sql, t.getId()) != 0;
		} catch (Exception e) {
			LogUtil.error(clazz, new AppExpection("ɾ������", "ɾ��[ " + tableName
					+ " ]�������", e));
		}
		return result;
	}

	/**
	 * ���ݿ�ɾ������
	 */
	public boolean deleteDB(T t) {
		boolean result = false;
		try {
			if (t == null || t.getId() == null) {
				LogUtil.error(clazz, new AppExpection("ɾ������", "ɾ��[ "
						+ tableName + " ]�������;IDΪnull"));
				return result;
			}
			String sql = DBConstants.DML_DELETE + orgTableName
					+ DBConstants.DML_WHERE + "id=" + t.getId();
			LogUtil.infoSql(getClass(), sql.toString());
			result = getJdbcTemplate().update(sql) != 0;
		} catch (Exception e) {
			LogUtil.error(clazz, new AppExpection("ɾ������", "ɾ��[ " + tableName
					+ " ]�������", e));
		}
		return result;
	}

	/**
	 * ��ѯ����
	 */
	public List<T> find(T t) {
		return queryByTfield(t);
	}

	/**
	 * ��ѯ����
	 * 
	 * @return
	 * @return
	 */
	public T findById(Integer id) {
		if (null != id) {
			String sql = DBConstants.DML_SELECT + tableName
					+ DBConstants.DML_WHERE + DBConstants.DML_PK
					+ DBConstants.DML_EQ + id;
			RowMapper<T> rm = ParameterizedBeanPropertyRowMapper
					.newInstance(clazz);
			return getJdbcTemplate().queryForObject(sql, rm);
		}
		return null;
	}

	/**
	 * ��ҳ��ѯ
	 */
	public PageResult<T> queryPage(QueryParam<T> params) {
		PageResult<T> pageResult = new PageResult<T>();
		List<T> totalResult = queryByParams(params);
		List<T> content = countPage(params, pageResult, totalResult);
		pageResult.setContent(content);

		return pageResult;
	}

	public List<T> countPage(QueryParam<T> params, PageResult<T> pageResult,
			List<T> totalResult) {
		int pageSize = params.getPageSize();
		int start = (params.getPage() - 1) * pageSize;
		
		pageResult.setPage(params.getPage());
		pageResult.setTotalReslt(totalResult);
		int total = totalResult.size();
		int totalPage = total / pageSize;
		if (total % pageSize != 0) {
			totalPage++;
		}
		pageResult.setTotalPages(totalPage);
		List<T> content = new ArrayList<T>();
		int end = (start + pageSize) > totalResult.size() ? totalResult.size()
				: start + pageSize;
		for (int i = start; i < end; i++) {
			content.add(totalResult.get(i));
		}
		return content;
	}

	private List<T> queryByParams(QueryParam<T> params) {
		StringBuilder sql = genFiledWhere(params.getParam());
		//ʱ������
		genTimeLimit(params, sql);
		// ����
		querySort(params, sql);
		List<T> totalResult = new ArrayList<T>();
		totalResult = query(sql.toString());
		return totalResult;
	}

	private void querySort(QueryParam<T> params, StringBuilder sql) {
		if (StringUtil.isNotNull(params.getOrderFiled())
				&& StringUtil.isNotNull(params.getOrderType())) {
			sql.append(DBConstants.DML_ORDER);
			sql.append(params.getOrderFiled());
			sql.append(params.getOrderType());
		}
	}

	private void genTimeLimit(QueryParam<T> params, StringBuilder sql) {
		String timeFiledStr = "";
		if(params.getQueryTimeDates()!=null&&
				params.getQueryTimeDates().size()>0){
			timeFiledStr = params.getQueryTimeDates().get(0);
		}
		// ʱ������
		if (StringUtil.isNotNull(params.getStartDate())) {
			sql.append(genFiledWhere(params.getParam(), params.getStartDate(),
					DBConstants.DML_GT,timeFiledStr,timeFiled));
		}
		if (StringUtil.isNotNull(params.getEndDate())) {
			sql.append(genFiledWhere(params.getParam(), params.getEndDate(),
					DBConstants.DML_Lt,timeFiledStr,timeFiled));
		}
		if(params.getQueryTimeDates()!=null&&
				params.getQueryTimeDates().size()>1){
			timeFiledStr = params.getQueryTimeDates().get(1);
		}
		if (StringUtil.isNotNull(params.getStartDate2())) {
			sql.append(genFiledWhere(params.getParam(), params.getStartDate2(),
					DBConstants.DML_GT,timeFiledStr,timeFiledSed));
		}
		if (StringUtil.isNotNull(params.getEndDate2())) {
			sql.append(genFiledWhere(params.getParam(), params.getEndDate2(),
					DBConstants.DML_Lt,timeFiledStr,timeFiledSed));
		}
	}
	public static void main(String[] args) {
		System.out.println("'e'".equalsIgnoreCase("'e'"));
	}
	private List<T> queryByTfield(T t) {
		StringBuilder sql = genFiledWhere(t);
		List<T> totalResult = new ArrayList<T>();
		totalResult = query(sql.toString());
		return totalResult;
	}

	private StringBuilder genFiledWhere(T t) {
		StringBuilder sql = new StringBuilder(DBConstants.DML_SELECT
				+ tableName);
		sql.append(DBConstants.DML_WHERE + DEL_NORMAL);
		List<String> fileds = reflectEntity(t);
		String filedsNames = fileds.get(0);
		String filedsValues = fileds.get(1);
		String[] names = filedsNames.split(DBConstants.FILD_SPLIT);
		String[] values = filedsValues.split(DBConstants.FILD_SPLIT);
		for (int i = 0; i < values.length; i++) {
			
			if (StringUtil.isNotNull(values[i])
					|| DBConstants.EMPTY_FLAG.equalsIgnoreCase(values[i])
					|| DBConstants.NOT_EMPTY_FLAG.equalsIgnoreCase(values[i])) {
				sql.append(DBConstants.DML_AND);
				if (values[i].contains(DBConstants.CHAR_LIKE)) {
					sql.append(DBConstants.DML_TBOF + names[i]
							+ DBConstants.DML_LIKE + values[i]
							+ DBConstants.DML_SPACE);
				} else if (("'" +DBConstants.EMPTY_FLAG+"'") .equalsIgnoreCase(values[i])) {
					sql.append(DBConstants.DML_TBOF + names[i]
							+  DBConstants.DML_EMPTY);
				}else if (("'" +DBConstants.NOT_EMPTY_FLAG+"'").equalsIgnoreCase(values[i]+"")) {
					sql.append(DBConstants.DML_TBOF + names[i]
							+  DBConstants.DML_NOTEMPTY);
				} else {
					sql.append(DBConstants.DML_TBOF + names[i]
							+ DBConstants.DML_EQ + values[i]
							+ DBConstants.DML_SPACE);
				}

			}
		}
		return sql;
	}

	private String genFiledWhere(T t, String timeStr, String flag, String timeFiledStr, String[] timeFiled) {
		
		StringBuilder sql = new StringBuilder();
		List<String> fileds = reflectEntity(t);
		String filedsNames = fileds.get(0);
		String[] names = filedsNames.split(DBConstants.FILD_SPLIT);
		String filedTime = findTimeFiledIndex(names,timeFiledStr,timeFiled);
		if (StringUtil.isNotNull(filedTime)) {
			sql.append(DBConstants.DML_AND);
			sql.append(DBConstants.DML_TBOF+filedTime + flag + "'" + timeStr + "'");
			return sql.toString();
		} else {
			return "";
		}

	}

	private String findTimeFiledIndex(String[] names,String timeFiledStr, String[] timeFiled) {
		if(StringUtil.isNotNull(timeFiledStr)){
			return timeFiledStr;
		}
		for (String name : names) {
			for (String index : timeFiled) {
				if (index.equalsIgnoreCase(name)) {
					return index;
				}
			}
		}
		return null;
	}

	/**
	 * �������
	 */
	public List<T> save(T t) {
		StringBuilder sql = new StringBuilder(DBConstants.DML_INSERT
				+ (tableName.replaceAll(DBConstants.DML_TB, "").trim()));
		List<String> fileds = reflectEntity(t);
		String filedsNames = fileds.get(0);
		String filedsValues = fileds.get(1);
		sql.append("(");
		String[] names = filedsNames.split(DBConstants.FILD_SPLIT);
		String[] values = filedsValues.split(DBConstants.FILD_SPLIT);
		for (int i = 0; i < values.length; i++) {
			if (StringUtil.isNotNull(values[i])) {
				sql.append(names[i]);
				if (i != values.length - 1) {
					sql.append(DBConstants.DML_SPLIT);
				}
			}

		}
		sql.append(") ");
		sql.append(DBConstants.DML_VALUES);
		sql.append(" (");

		for (int i = 0; i < values.length; i++) {
			if (StringUtil.isNotNull(values[i])) {
				sql.append(values[i]);
				if (i != values.length - 1) {
					sql.append(DBConstants.DML_SPLIT);
				}
			}
		}
		sql.append(") ");
		save(sql.toString());
		return queryByTfield(t);

	}

	/**
	 * ���¶���
	 */
	public int update(T t) throws AppExpection {
		StringBuilder sql = new StringBuilder(DBConstants.DML_UPDATE
				+ tableName);
		List<String> fileds = reflectEntity(t);
		String filedsNames = fileds.get(0);
		String filedsValues = fileds.get(1);
		String[] names = filedsNames.split(DBConstants.FILD_SPLIT);
		String[] values = filedsValues.split(DBConstants.FILD_SPLIT);
		sql.append(DBConstants.DML_SET);
		for (int i = 0; i < values.length; i++) {
			if (DBConstants.DML_PK.equalsIgnoreCase(names[i])) {
				continue;
			}
			if (StringUtil.isNotNull(values[i])) {
				sql.append(DBConstants.DML_TBOF + names[i] + DBConstants.DML_EQ
						+ values[i] + DBConstants.DML_SPACE);
				if (i != values.length - 1) {
					sql.append(DBConstants.DML_SPLIT);
				}
			}

		}
		sql.append(DBConstants.DML_WHERE + DBConstants.DML_PK
				+ DBConstants.DML_EQ + t.getId());
		return update(sql.toString());
	}

	/**
	 * ����������� return ����sizeΪ2��List��0��Ϊ�������ƣ�1Ϊ����ֵ
	 */
	public List<String> reflectEntity(T t) {
		StringBuilder fieldNames = new StringBuilder();
		StringBuilder fieldValues = new StringBuilder();
		// �ݹ����T�����Ժ�����ֵ
		CommonUtil.recursive(t, t.getClass(), fieldNames, fieldValues);
		List<String> lists = new ArrayList<String>();
		lists.add(fieldNames.toString());
		lists.add(fieldValues.toString());
		return lists;
	}

	public JdbcTemplate getJdbcTemplate() {
		if (null == jdbcTemplate) {
			jdbcTemplate = SpringContextHolder.getBean("jdbcTemplate");
		}
		return jdbcTemplate;
	}

}

package com.sys.db;
/**
 *
 *@author chenchuan
 *@date 2016年1月22日
 *数据访问相关常量
 */
public class DBConstants {
	/**
	 * 删除标记
	 */
	public static final String DEL_FLAG = "delFlag";
	/**
	 *  
	 */
	public static final String SPACE = " ";
	/**
	 * 未删除
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	/**
	 * 已删除
	 */
	public static final String DEL_FLAG_DELETE = "1";
	
	/**
	 * 表前缀
	 */
	public static final String TABLE_PREFFIX = "tb_";
	
	
	/**
	 * insert
	 */
	public static final String DML_INSERT = " insert into ";
	/**
	 * values
	 */
	public static final String DML_VALUES = " values ";
	
	
	/**
	 * update
	 */
	public static final String DML_UPDATE = " update ";
	
	/**
	 * set
	 */
	public static final String DML_SET = " set ";
	
	/**
	 * delete
	 */
	public static final String DML_DELETE = " delete from ";
	
	/**
	 * select
	 */
	public static final String DML_SELECT = " select * from ";
	
	/**
	 * value split
	 */
	public static final String DML_SPLIT = ",";
	
	
	/**
	 * where
	 */
	public static final String DML_WHERE = " where ";
	/**
	 * and
	 */
	public static final String DML_AND = " and ";
	/**
	 * 表别名
	 */
	public static final String DML_TB = " tb ";
	/**
	 * 表.属性
	 */
	public static final String DML_TBOF = " tb.";
	/**
	 * >
	 */
	public static final String DML_GT = " > ";
	/**
	 * >
	 */
	public static final String DML_Lt = " < ";
	/**
	 * like
	 */
	public static final String DML_LIKE = " like ";
	/**
	 * is
	 */
	public static final String DML_IS = " is ";
	/**
	 * empty
	 */
	public static final String DML_EMPTY = " ='' ";
	/**
	 * not empty
	 */
	public static final String DML_NOTEMPTY = " !='' ";
	/**
	 * =
	 */
	public static final String DML_EQ = "=";
	/**
	 * id
	 */
	public static final String DML_PK = "id";
	/**
	 * space
	 */
	public static final String DML_SPACE = " ";
	/**
	 * order by
	 */
	public static final String DML_ORDER=" order by ";
	/**
	 * asc
	 */
	public static final String DML_ORDER_ASC=" asc ";
	/**
	 * desc
	 */
	public static final String DML_ORDER_DESC=" desc ";
	/**
	 * daoImpl subffix
	 */
	public static final String SER_IMPLSUB = "DaoImpl";
	
	/**
	 * %
	 */
	public static final String CHAR_LIKE = "%";
	
	/**
	 * &
	 */
	public static final String FILD_SPLIT = "&";
	
	/**
	 * 空串条件标志empty flag
	 */
	public static final String EMPTY_FLAG="empty";
	/**
	 * 非空串条件标志empty flag
	 */
	public static final String NOT_EMPTY_FLAG="not empty";
	

}

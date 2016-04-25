package org.nxf.frame.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.nxf.frame.ContextConfig;
import org.nxf.frame.conn.DBConn;
import org.nxf.frame.conn.ProxoolDBConn;;

public class DAO implements IDAO {
	/**
	 * ���ݿ����Ӷ���
	 */
	private Connection conn;

	private java.sql.PreparedStatement ps;
	private String sql;
	/**
	 * ���ݿ��
	 */
	private String table;
	private Class<?> ref;
	private Field[] fields;
	private String[] fieldType;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public Class<?> getRef() {
		return ref;
	}

	public void setBean(String className) {
		try {
			this.ref = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		;
	}

	/**
	 * Ĭ�Ϲ��췽�����ᴴ��һ�����ݿ����ӣ�û��ָ�����ʵ����,����ʹ�ñ����ṩ��CURD����
	 * 
	 */
	public DAO() {
		initConn();
		this.table = "";
		this.ref = null;
	}
	
	private void initConn(){
		if(!ContextConfig.alials.equals("")){
			this.conn = ProxoolDBConn.getConnection();
		}else{
			this.conn = DBConn.getConnection();
		}
	}

	/**
	 * ���췽��������һ��ʵ����ķ��ʶ��󣬲���һָ�������ݿ�������������ݿ��ӳ�䵽ʵ�����ϡ�
	 * 
	 * @param table
	 *            ���ݿ�����
	 * @param className
	 *            ʵ��������� ����Ӱ���
	 */
	public DAO(String table, String className) {
		initConn();
		this.table = table;
		try {
			ref = Class.forName(className);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		fields = ref.getDeclaredFields();
		fieldType = getFieldType(ref);
		if(ContextConfig.ddlauto.equals("create")){
			createTable();
		}
	}

	/**
	 * ��ʼ�����ݿ����������ھʹ�����
	 */
	public void createTable() {
		String sql = "create table if not exists " + table + " ("
				+ fields[0].getName() + " int(11) NOT NULL AUTO_INCREMENT,";
		String strV = "";
		for (int i = 1; i < fields.length; i++) {
			strV += fields[i].getName();
			if ("java.lang.String".equals(fields[i].getType().getName())) {
				strV += " varchar(255)";
			}
			if ("java.lang.Float".equals(fields[i].getType().getName())) {
				strV += " float";
			}
			if ("java.lang.Integer".equals(fields[i].getType().getName())) {
				strV += " int";
			}
			if ("java.lang.Double".equals(fields[i].getType().getName())) {
				strV += " double";
			}
			if ("java.util.Date".equals(fields[i].getType().getName())) {
				strV += " datetime";
			}
			strV += ",";
		}
		strV += "PRIMARY KEY (" + fields[0].getName() + ")"
				+ ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8";

		sql += strV;
		DebugOutString(sql);
		try {
			Statement st = conn.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * �鿴���ݿ���Ƿ����
	 * 
	 * @return boolean����
	 */
	public Boolean IsExists() {
		String sql = "select table_name from information_schema.tables where table_name= '" + table + "'";
		DebugOutString(sql);
		ResultSet rs = null;
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * ���ʵ������ֶθ���
	 * 
	 * @return int
	 */
	public int getFieldLength() {
		Field[] fields = ref.getDeclaredFields();
		return fields.length;
	}

	/**
	 * ���ʵ����ķ�����Ϊget������
	 * 
	 * @param ref
	 *            ʵ�����Class����
	 * @return String[] �Ѷ��get�������ֱ�ŵ�String��������
	 */
	public String[] getGet(Class<?> ref) {

		int count = getFieldLength();

		String[] methods = new String[count];
		for (int i = 0; i < count; i++) {
			String str = fields[i].getName();
			str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
			methods[i] = "get" + str;

		}
		return methods;
	}

	/**
	 * ���ʵ����ķ�����Ϊset������
	 * 
	 * @param ref
	 *            ʵ�����Class����
	 * @return �Ѷ��set�������ֱ�ŵ�String��������
	 */
	public String[] getSet(Class<?> ref) {
		int count = fields.length;
		String[] methods = new String[count];
		for (int i = 0; i < count; i++) {
			String str = fields[i].getName();
			str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
			methods[i] = "set" + str;
		}
		return methods;
	}

	/**
	 * �������ֶε�����
	 * 
	 * @param ref
	 *            Ҫ���������ö���
	 * @return String����
	 */
	private String[] getFieldType(Class<?> ref) {
		Field[] fields = ref.getDeclaredFields();
		String[] type = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			type[i] = fields[i].getType().getName();
		}
		return type;
	}

	/* (non-Javadoc)
	 * @see org.nxf.frame.dao.IDAO#insert(java.lang.Object)
	 */
	public void insert(Object object) throws Exception {
		String strKey = "";
		String strValue = "";
		Field[] fields = ref.getDeclaredFields();
		String[] getMethods = getGet(ref);

		int start = 1;
		Integer value;

		Method method = ref.getMethod(getMethods[0]);
		value = (Integer) method.invoke(object);
		if (value == null || value < 1) {
			start = 1;
		}

		for (int i = start; i < fields.length; i++) {
			strValue += "?,";
		}

		for (int i = start; i < fields.length; i++) {
			strKey += fields[i].getName() + ",";
		}

		strKey = strKey.substring(0, strKey.length() - 1);
		strValue = strValue.substring(0, strValue.length() - 1);
		sql = "insert into " + table + "(" + strKey + ")values(" + strValue
				+ ")";

		DebugOutString(sql);

		ps = conn.prepareStatement(sql);

		for (int i = start; i < getMethods.length; i++) {
			Method m = ref.getMethod(getMethods[i]);
			String returnValue = m.getReturnType().getName();
			if (returnValue.equals("java.lang.String")) {
				String v = (String) m.invoke(object);
				ps.setString(i, v);
			}
			if (returnValue.equals("int")
					|| returnValue.equals("java.lang.Integer")) {
				Integer v = (Integer) m.invoke(object);
				ps.setInt(i, v);
			}

			if (returnValue.equals("float")
					|| returnValue.equals("java.lang.Float")) {
				Float v = (Float) m.invoke(object);
				ps.setFloat(i, v);
			}
			if (returnValue.equals("java.lang.Double")) {
				Double v = (Double) m.invoke(object);
				ps.setDouble(i, v);
			}
			if (returnValue.equals("java.util.Date")) {
				Date d = (java.util.Date) m.invoke(object);
				java.sql.Timestamp v = new java.sql.Timestamp(d.getTime());
				ps.setTimestamp(i, v);
			}
		}
		ps.executeUpdate();
		ps.close();
	}


	public List<Object> select(String str) throws Exception {
		String sql = "SELECT * FROM " + table + " where ";
		if (str == null || ("").equals(str)) {
			str = "1";
		}
		sql += str;
		DebugOutString(sql);
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Object> list = new ArrayList<Object>();

		String[] methods = getSet(ref);
		Method m;
		while (rs.next()) {
			Object o = ref.newInstance();
			for (int i = 0; i < getFieldLength(); i++) {

				if ("java.lang.String".equals(fieldType[i])) {
					String v = rs.getString(i + 1);
					m = ref.getMethod(methods[i], new Class[] { String.class });
					m.invoke(o, v);
				}
				if ("int".equals(fieldType[i])
						|| "java.lang.Integer".equals(fieldType[i])) {
					m = ref.getMethod(methods[i], new Class[] { Integer.class });
					Integer v = rs.getInt(i + 1);
					m.invoke(o, v);
				}

				if ("float".equals(fieldType[i])
						|| "java.lang.Float".equals(fieldType[i])) {
					m = ref.getMethod(methods[i], new Class[] { Float.class });
					Float v = rs.getFloat(i + 1);
					m.invoke(o, v);
				}
				if ("java.lang.Double".equals(fieldType[i])) {
					m = ref.getMethod(methods[i], new Class[] { Double.class });
					Double v = rs.getDouble(i + 1);
					m.invoke(o, v);
				}
				if ("java.util.Date".equals(fieldType[i])) {
					m = ref.getMethod(methods[i], new Class[] { Date.class });
					Date v = rs.getDate(i + 1);
					m.invoke(o, v);
				}
			}
			list.add(o);
		}
		return list;
	}

	/**
	 * ��ʾ������Ϣ
	 * 
	 * @param str Ҫ��ʾ����Ϣ���ַ�������
	 *            
	 */
	protected void DebugOutString(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println("[" + sdf.format(new Date()) + "]:" + str);
	}

	
	public int delete(Integer id) throws Exception {
		String sql = "delete from " + table + " where id=?";
		DebugOutString(sql);
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		int res = ps.executeUpdate();
		return res;
	}


	public int update(Object o) throws Exception {
		String strV = "";
		for (int i = 0; i < fields.length; i++) {
			strV += fields[i].getName() + "=?,";
		}
		strV = strV.substring(0, strV.length() - 1);
		String sql = "update " + table + " set " + strV + " where id=?";
		DebugOutString(sql);
		ps = conn.prepareStatement(sql);
		String[] getMethods = getGet(ref);
		for (int i = 0; i < fields.length; i++) {
			Method m = ref.getMethod(getMethods[i]);
			String returnValue = m.getReturnType().getName();
			if (returnValue.equals("java.lang.String")) {
				String v = (String) m.invoke(o);
				ps.setString(i + 1, v);
			}
			if (returnValue.equals("java.lang.Integer")) {
				Integer v = (Integer) m.invoke(o);
				ps.setInt(i + 1, v);
			}

			if (returnValue.equals("java.lang.Float")) {
				Float v = (Float) m.invoke(o);
				ps.setFloat(i + 1, v);
			}
			if (returnValue.equals("java.lang.Double")) {
				Double v = (Double) m.invoke(o);
				ps.setDouble(i + 1, v);
			}
			if (returnValue.equals("java.util.Date")) {
				Date v = (Date) m.invoke(o);
				ps.setTimestamp(i + 1, new java.sql.Timestamp(v.getTime()));
			}

		}
		Integer v = (Integer) ref.getMethod(getMethods[0]).invoke(o);
		ps.setInt(fields.length + 1, v);
		int res = ps.executeUpdate();
		return res;
	}

	/**
	 * ִ�����ݿ�SQL��ѯ���,����sql������һ����׼��SQL��ѯ��䣬����ִ��insert update �� delete��
	 * 
	 * @param sql
	 *            Ҫִ�е�select��䡣
	 * @return ResultSet ���� ��ѯ���������һ��ResultSet����
	 * @throws SQLException
	 */
	public ResultSet execQuery(String sql) throws SQLException {
		DebugOutString(sql);
		java.sql.Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		return rs;
	}

	/**
	 * ���ָ�����������
	 * 
	 * @return int ����
	 * @throws SQLException
	 */
	public int getRowTotal() throws SQLException {
		java.sql.Statement st = conn.createStatement();
		String sql = "select count(*) from " + table;
		DebugOutString(sql);
		ResultSet rs = st.executeQuery(sql);
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}
};

package zzu.bs.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

public class JDBCUtils {

	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	/**
	 * 获取Connection的方�?
	 * @return
	 * @throws Exception
	 */
	public static  Connection getConnection() throws Exception {
		Connection conn = threadLocal.get();
		if(conn==null){
			Properties prop = new Properties();
			prop.load(JDBCUtils.class.getResourceAsStream("/jdbc.properties"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop);
			threadLocal.set(conn);
		}
		return conn;
	}

	/**
	 * 释放资源
	 * 
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public static void free(ResultSet rs, Statement st, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				st = null;
			}
		}
		if (conn != null) {
			threadLocal.remove();
		}
	}

	/**
	 * �?/�?/改操�? 可变参数:放在方法参数�?后面，相当于Object[]数组 jdk1.5特�??
	 * 
	 * @throws Exception
	 */
	public static boolean update(String sql, Object... params) throws Exception {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		// 设置�?
		if (params != null) {
			int index = 1;
			for (Object obj : params) {
				ps.setObject(index++, obj);
			}
		}
		int count = ps.executeUpdate();
		free(null, ps, null);
		return count > 0 ? true : false;
	}

	public static <T> boolean update(T t, String sql) throws Exception {
		Connection conn = getConnection();
		// 解析字符�?
		// update s_emp set salary=:salary,last_name=:name where id=:id
		String reg = "[:](\\w{1,})";
		// 编译正则
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(sql);
		List<String> list = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			list.add(m.group(1));
			m.appendReplacement(sb, "?");
		}
		PreparedStatement ps = conn.prepareStatement(sb.toString());
		// 设置�?
		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i);
			Field[] fs = t.getClass().getDeclaredFields();
			for (Field f : fs) {
				if (name.equals(f.getName())) {
					f.setAccessible(true);
					ps.setObject(i + 1, f.get(t));
				}
			}
		}
		int count = ps.executeUpdate();
		free(null, ps, null);
		return count > 0 ? true : false;
	}

	/**
	 * 查询多条数据，将每一条数据封装到T类型对象中，并将对象放入list
	 * sql语句只涉及一张表时用这个
	 * @param <T>
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> queryForList(String sql, Class<T> clazz, Object... params) throws Exception {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		if (params != null) {
			int index = 1;
			for (Object obj : params) {
				ps.setObject(index++, obj);
			}
		}
		List<T> list = new ArrayList<T>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			// 创建T类型的对�?
			T t = clazz.newInstance();
			// 获取�?行数据对�?
			ResultSetMetaData data = rs.getMetaData();
			// 获取�?行数据的列数
			int count = data.getColumnCount();
			for (int i = 1; i <= count; i++) {
				// 获取列名
				String columnName = data.getColumnName(i);
				// 获取列类�?
				int columnType = data.getColumnType(i);
				// 获取T类型中所有的属�??
				Field[] fs = clazz.getDeclaredFields();
				for (Field f : fs) {
					// 设置属�?�可被访�?
					f.setAccessible(true);
					// 如果列名和属性名字一直，要将列�?�设置为属�?��??
					if (columnName.equalsIgnoreCase(f.getName())) {System.out.println(f.getName()+"="+columnName);
						Object value = rs.getObject(columnName);
						// 设置t对象属�??
						f.set(t, convert(value, f.getType(), columnType));
					}
				}
			}
			list.add(t);
		}
		free(rs, ps, null);
		return list;
	}

	/**
	 * 查询�?条数�?
	 * 
	 * @param <T>
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 * @return
	 * @throws Exception
	 */
	public static <T> T queryForObject(String sql, Class<T> clazz, Object... params) throws Exception {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		if (params != null) {
			int index = 1;
			for (Object obj : params) {
				ps.setObject(index++, obj);
			}
		}
		ResultSet rs = ps.executeQuery();
		// 创建T类型的对�?
		T t = null;
		if (rs.next()) {
			t = clazz.newInstance();
			// 获取�?行数据对�?
			ResultSetMetaData data = rs.getMetaData();
			// 获取�?行数据的列数
			int count = data.getColumnCount();
			for (int i = 1; i <= count; i++) {
				// 获取列名
				String columnName = data.getColumnName(i);
				// 获取列类�?
				int columnType = data.getColumnType(i);
				// 获取T类型中所有的属�??
				Field[] fs = clazz.getDeclaredFields();
				for (Field f : fs) {
					// 设置属�?�可被访�?
					f.setAccessible(true);
					// 如果列名和属性名字一直，要将列�?�设置为属�?��??
					if (columnName.equalsIgnoreCase(f.getName())) {
						Object value = rs.getObject(columnName);
						// 设置t对象属�??
						f.set(t, convert(value, f.getType(), columnType));
					}
				}
			}
		}
		free(rs, ps, null);
		return t;
	}

	//方法名应为queryForList�?
	/*sql语句涉及多张表时用这个*/
	public static List<Map<String,Object>> queryForList(String sql, Object... params) throws Exception {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		if (params != null) {
			int index = 1;
			for (Object obj : params) {
				ps.setObject(index++, obj);
			}
		}
		ResultSet rs = ps.executeQuery();
		List<Map<String,Object>> list = new ArrayList<>();
		while (rs.next()) {
			Map<String,Object> map = new HashMap<>();
			// 获取�?行数据对�?
			ResultSetMetaData data = rs.getMetaData();
			// 获取�?行数据的列数
			int count = data.getColumnCount();
			for (int i = 1; i <= count; i++) {
				// 获取列名
				String columnName = data.getColumnName(i);
				// 获取列�??
				Object value = rs.getObject(i);
				map.put(columnName, value);
			
			}
			list.add(map);
		}
		free(rs, ps, null);
		return list;
	}
	
	
	/**
	 * 通过查询条件统计记录的�?�条�?
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static int queryForInt(String sql, Object... params) throws Exception {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		int count = 0;
		// 设置参数
		if (params != null) {
			int index = 1;
			for (Object obj : params) {
				ps.setObject(index++, obj);
			}
		}
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			count= rs.getInt(1);
		}
		free(rs, ps, null);
		return count;
	}

	/**
	 * 数据库类型和列类型的转换 
	 * 
	 * @param val
	 * @param type
	 * @param columnType
	 * @return
	 */
	private static Object convert(Object val, Class<?> type, int columnType) throws Exception{

		if (val == null)
			return val;
		if ((type == Byte.class || type == byte.class) && (columnType == Types.NUMERIC || columnType == Types.BIT)) {
			return Byte.parseByte(String.valueOf(val));
		}
		if ((type == Short.class || type == short.class) && (columnType == Types.NUMERIC)) {
			return Short.parseShort(String.valueOf(val));
		}
		if ((type == Integer.class || type == int.class)
				&& (columnType == Types.NUMERIC || columnType == Types.INTEGER)) {
			return Integer.parseInt(String.valueOf(val));
		}
		if ((type == Long.class || type == long.class)
				&& (Types.INTEGER == columnType || Types.NUMERIC == columnType)) {
			return Long.parseLong(String.valueOf(val));
		}
		if ((type == Float.class || type == float.class)
				&& (Types.FLOAT == columnType || Types.NUMERIC == columnType)) {
			return Float.parseFloat(String.valueOf(val));
		}
		if ((type == Double.class || type == double.class)
				&& (Types.DOUBLE == columnType || Types.NUMERIC == columnType)) {
			return Double.parseDouble(String.valueOf(val));
		}
		if ((type == String.class) && (Types.VARCHAR == columnType || Types.CHAR == columnType)) {
			return String.valueOf(val);
		}

		if ((type == Character.class || type == char.class)
				&& (Types.VARCHAR == columnType || Types.CHAR == columnType)) {
			return String.valueOf(val).charAt(0);
		}

		if ((type == java.util.Date.class) && (Types.DATE == columnType || Types.TIMESTAMP == columnType)) {
			return val;
		}
		if(type==byte[].class && Types.BLOB==columnType){
			Blob b = (Blob)val;
			InputStream in = b.getBinaryStream();
			return IOUtils.toByteArray(in);
		}
		throw new RuntimeException("请检查数据库字段类型和返回的类型是否�?致！！！");
	}
}

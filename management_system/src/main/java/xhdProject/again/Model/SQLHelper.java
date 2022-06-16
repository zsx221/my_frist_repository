package xhdProject.again.Model;

import java.lang.reflect.Field;
//此处使用jdbc原型
import java.util.*;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class SQLHelper {// 增删改查操作处
	private static JdbcTemplate jdbc;

	public  SQLHelper() {
		if(jdbc==null)//如果jdbc为空，就去得到这个连接
			jdbc=getjdbc();
	}
	public JdbcTemplate getjdbc() {//连接池私有化
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl(
				"jdbc:mysql://124.222.210.165:3306/magagment_system?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8");
		dataSource.setUsername("magagment_system");
		dataSource.setPassword("123456");
		jdbc = new JdbcTemplate(dataSource);//创建连接
		return jdbc;
	}
	public List<Map<String, Object>> Select_List(String Table) {// 查询整表
		String sql = "select  *  from  " + Table;
		List<Map<String, Object>> maps = jdbc.queryForList(sql);
		return maps;
	}

	public List<Map<String, Object>> Select_List(String Table, String Conditions) {// 给条件查询整表信息
		String sql = "select * from " + Table + " where " + Conditions;
		List<Map<String, Object>> maps = jdbc.queryForList(sql);
		return maps;
	}
	public List<Map<String, Object>> Select_Listsome(String Table, String Field) {// 给条件查询表的一些信息
		String sql = "select " + Field + " from " + Table ;
		List<Map<String, Object>> maps = jdbc.queryForList(sql);
		return maps;
	}
	public List<Map<String, Object>> Select_List(String Table, String Field, String Conditions) {// 给条件查询表的一些信息
		String sql = "select " + Field + " from " + Table + " where " + Conditions;
		List<Map<String, Object>> maps = jdbc.queryForList(sql);
		return maps;
	}

	public List<Map<String, Object>> Select_List(String Table, String Field, String Conditions, String Order) {// 给条件查询表的一些信息
		String sql = "select " + Field + " from " + Table + " where " + Conditions + " order by " + Order;
		List<Map<String, Object>> maps = jdbc.queryForList(sql);
		return maps;
	}

	public String Select_String(String Table, String Field, String Conditions) {// 给条件查询表的一条信息
		String sql = "select " + Field + " from " + Table + " where " + Conditions + " limit 1 ";
		String maps = jdbc.queryForObject(sql, String.class);
		return maps;
	}

	public Boolean Select_Exsit(String Table, String Conditions) {// 给条件查询表条数
		String sql = "select count(*) from " + Table + " where " + Conditions + " limit 1 ";
		int results = jdbc.queryForObject(sql, Integer.class);
		if (results > 0) {
			return true;
		} else {
			return false;
		}
	}
	public int Select_login(String Table, String Conditions) {// 给条件查询表条数
		String sql = "select count(*) from " + Table + " where " + Conditions;
		int results = jdbc.queryForObject(sql, Integer.class);
		if (results > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	private String GetField(Object o) {// 获取字段名
		String Result = "";
		Class<? extends Object> t = o.getClass();
		Field[] fs = t.getDeclaredFields();
		for (Field f : fs) {
			if (!f.getName().toLowerCase().equals("id"))
				Result += f.getName() + ",";
		}
		Result = Result.substring(0, Result.length() - 1);
		return Result;
	}

	private String GetPlace(Object o) {// 占位
		String Result = "";
		Class<? extends Object> t = o.getClass();
		Field[] fs = t.getDeclaredFields();
		for (Field f : fs) {
			if (!f.getName().toLowerCase().equals("id"))
				Result += "?,";
		}
		Result = Result.substring(0, Result.length() - 1);
		return Result;
	}

	private String[] GetValues(Object o) {// 获取字段值
		List<String> list = new ArrayList<>();
		Class<? extends Object> t = o.getClass();
		Field[] fs = t.getDeclaredFields();
		for (Field f : fs) {
			f.setAccessible(true);// 让每个字段变成可见
			if (!f.getName().toLowerCase().equals("id"))
				try {
					if (f.get(o) == null || f.get(o) == "")
						list.add("");
					else
						list.add(f.get(o).toString());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		//System.out.println(list.toString());
		return list.toArray(new String[list.size()]);
	}

	private String GetFieldwithPlace(Object o) {// 获取完整的字段名
		String Result = "";
		Class<? extends Object> t = o.getClass();
		Field[] fs = t.getDeclaredFields();
		for (Field f : fs) {
			if (!f.getName().toLowerCase().equals("id"))
				Result += f.getName() + "=?,";
		}
		Result = Result.substring(0, Result.length() - 1);
		//System.out.println(Result);
		return Result;
	}

	private String GetFieldwithsome(Object o) {// 获取完整的字段名
		String Result = "";
		Class<? extends Object> t = o.getClass();
		Field[] fs = t.getDeclaredFields();
		for (Field f : fs) {

			if (!f.getName().toLowerCase().equals("id"))
				Result += f.getName() + "=?,";
		}
		Result = Result.substring(0, Result.length() - 1);
		return Result;
	}

	public int insert(String table, Object vaule) {
		String sql = "insert into " + table + "(" + GetField(vaule) + ") value ( " + GetPlace(vaule) + "  ) ";
		String[] a;
		int nums = jdbc.update(sql, GetValues(vaule));
		//System.out.println(sql);
		return nums;
	}

	public int Update_rows(String Table, Object o, String conditions) {// 更新某行信息
		String sql = "update " + Table + " set " + GetFieldwithPlace(o) + " where " + conditions;
		//System.out.println(sql);
		int maps = jdbc.update(sql, GetValues(o));
		return  maps;  
	}

	public String Update_String(String Table, String key, String value, String conditions) {// 更新某行某个信息
		String sql = "update " + Table + " set " + key + " = ? where " + conditions;
		//System.out.println(sql);
		int maps = jdbc.update(sql, value);
		return "影响条数" + maps;
	}
	public int batchDelete(String Table, String string) {// 更新某行某个信息
		String sql = "delete from " + Table + " where id in ("+string+ ")";
		System.out.println(sql);
		int maps = jdbc.update(sql);
		
		return maps;
	}
	public int Delete_String(String Table, String conditions) {// 更新某行某个信息
		String sql = "delete from " + Table + " where "+conditions;
		int maps = jdbc.update(sql);
		return maps;
	}
	public int Execute(String sql) {
		return jdbc.update(sql);
	}
	public List<Map<String, Object>> Select_BySQL(String sql) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> maps=jdbc.queryForList(sql);
		return maps;
	}
}

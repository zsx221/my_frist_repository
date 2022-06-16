package xhdProject.again.Model;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

public class PageHelper {
	public String GetModule() {// 拼接HTML
		SQLHelper sql = new SQLHelper();
		StringBuilder s = new StringBuilder();
		List<Map<String, Object>> ds_Father = sql.Select_List("module", "*", "classID=0", "sort DESC");
		for (Map<String, Object> maps : ds_Father) {
			s.append("<li>");
			s.append("<a href='#'>");
			s.append("<span class='nav-label'>" + maps.get("title") + "</span>");
			s.append("</a>");
			s.append("<ul class='nav nav-second-level'>\r\n");
			s.append("<li>");
			List<Map<String, Object>> ds_Child = sql.Select_List("module", "*", "classID=" + maps.get("Id"),
					"sort DESC");
			for (Map<String, Object> maps_child : ds_Child) {
				s.append("<li>");
				s.append("<a class='J_menuItem' href='" + maps_child.get("href") + "'>" + maps_child.get("title") + "</a>");
				s.append("</li>\r\n");
			}
			s.append("</li>\r\n");
			s.append("</ul>\r\n");
			s.append("</li>\r\n");
		}
		//System.out.println(s.toString());
		return s.toString();
	}

	// 用输入输出流的方式解析request数据
	public String GetPost(HttpServletRequest request) {
		String s = "";
		InputStream in = null;
		BufferedInputStream bin = null;
		try {
			in = request.getInputStream();
			bin = new BufferedInputStream(in);
			int len = 0;
			byte[] b = new byte[1024];
			while ((len = bin.read(b)) != -1) {
				s += new String(b, 0, len);// 把request的信息存到s里
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bin.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	public String GetTreeContent(String TableName, String ID_Field, String Title_Field, String ClassID_Field,
			String TopValue, String BaseURL, String OrderBy) {
		SQLHelper sql = new SQLHelper();
		List<Map<String, Object>> ds = sql.Select_BySQL(
				"select * from " + TableName + " where " + ClassID_Field + "=" + TopValue + " Order by " + OrderBy);
		if (ds != null) {
			StringBuilder s = new StringBuilder();
			s.append("[");
			for (Map<String, Object> maps : ds) {

				s.append("{'name': ' " + maps.get(Title_Field) + " ' , 'open':'true','url':'" + BaseURL
						+ maps.get(ID_Field) + "','target':'_self'");
				if (sql.Select_Exsit(TableName, OrderBy + "=" + maps.get(ID_Field))) {
					s.append(Tree_Children(maps.get(ID_Field).toString(), TableName, ID_Field, Title_Field,
							ClassID_Field, BaseURL, OrderBy));
				}
				s.append("},");

			}
			s.append("]");
			System.out.println(s.toString());
			return s.toString();
		} else {
			return null;
		}
	}

	private String Tree_Children(String ParentID, String TableName, String ID_Field, String Title_Field,
			String ClassID_Field, String BaseURL, String OrderBy) {
		SQLHelper sql = new SQLHelper();
		StringBuilder s = new StringBuilder();
		List<Map<String, Object>> ds = sql.Select_BySQL(
				"select * from " + TableName + " where " + ClassID_Field + "=" + ParentID + " order by " + OrderBy);
		s.append(", children: [");
		for (Map<String, Object> maps : ds) {
			s.append("{'name': '" + maps.get(Title_Field) + "','open':'true','url':'" + BaseURL + maps.get(ID_Field)
					+ "',' target':'_self'");
			if (sql.Select_Exsit(TableName, ClassID_Field + "=" + maps.get(ID_Field))) {
				s.append(Tree_Children(maps.get(ID_Field).toString(), TableName, ID_Field, Title_Field, ClassID_Field,
						BaseURL, OrderBy));
			}
			s.append("},");
		}
		s.append("]");
		// System.out.println(s.toString());
		return s.toString();
	}

	public String GetSelect(String TableName, String ID_Field, String Title_Field, String Conditions, String OrderBy) {
		SQLHelper sql = new SQLHelper();
		StringBuilder s = new StringBuilder();
		List<Map<String, Object>> ds = sql.Select_BySQL("select " + ID_Field + " as value ," + Title_Field
				+ " as text from " + TableName + " where " + Conditions);
//		for(Map<String, Object>maps :ds) {
//			System.out.println(maps);
//		}
		JSONArray json = JSONArray.fromObject(ds);
		// System.out.println(json.toString());
		return json.toString();
	}
}

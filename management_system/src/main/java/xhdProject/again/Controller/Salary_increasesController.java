package xhdProject.again.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xhdProject.again.Entites.salary_increases;
import xhdProject.again.Model.PageHelper;
import xhdProject.again.Model.SQLHelper;

@RestController
@RequestMapping("/Salary_increases")
public class Salary_increasesController {
	@RequestMapping("/Index")
	public ModelAndView Index() {
		ModelAndView mv = new ModelAndView("Salary_increases/Index");
		return mv;
	}

	@RequestMapping("/Form")
	public ModelAndView Form() {
		ModelAndView mv = new ModelAndView("Salary_increases/Form");
		return mv;
	}

	@RequestMapping("/Visit")
	public ModelAndView Visit() {
		ModelAndView mv = new ModelAndView("Salary_increases/Visit");
		return mv;
	}

	@RequestMapping("/GetList")
	public String GetList() {
		SQLHelper sql = new SQLHelper();
		List<Map<String, Object>> datas = sql.Select_List("salary_increases");
		JSONArray json = JSONArray.fromObject(datas);
		// System.out.println(json.toString());
		return json.toString();
	}

	@RequestMapping("/GetSingle")
	public String GetSingle(HttpServletRequest request) {
		SQLHelper sql = new SQLHelper();
		List<Map<String, Object>> datas = sql.Select_List("salary_increases", "*", "id=" + request.getParameter("id"));
		JSONArray json = JSONArray.fromObject(datas);
		// System.out.println(json.toString());
		return json.toString();
	}

	@RequestMapping("/Insert")
	public int Insert(HttpServletRequest request) {
		SQLHelper sql = new SQLHelper();
		JSONObject object = JSONObject.fromObject(new PageHelper().GetPost(request));
		// 把String对象转化为json数据
		salary_increases entity = (salary_increases) JSONObject.toBean(object, salary_increases.class);
		// 把json对象转换为java对象，或者是salary_increases实体对象
		// System.out.println(entity.toString());
		int result = sql.insert("salary_increases", entity);
		return result;
	}

	@RequestMapping("/Update")
	public int Update(HttpServletRequest request) {
		SQLHelper sql = new SQLHelper();
		JSONObject object = JSONObject.fromObject(new PageHelper().GetPost(request));
		salary_increases entity = (salary_increases) JSONObject.toBean(object, salary_increases.class);
		int result = sql.Update_rows("salary_increases", entity, "id=" + request.getParameter("id"));
		return result;
	}

	@RequestMapping("/Delete")
	public int Delete(HttpServletRequest request) {
		SQLHelper sql = new SQLHelper();
		int result = sql.Delete_String("salary_increases", "id=" + request.getParameter("id"));
		return result;
}
	@RequestMapping("/batchDelete.do")
	public String batchDelete(String userList){
		System.out.println(userList);
	    String[] strs = userList.split(",");
	    List<Integer> ids = new ArrayList<>();
	    for(int i = 0;i<strs.length; i++){
	        ids.add(Integer.parseInt(strs[i]));
	    }
	    //StringUtils.strip(ids.toString(),"[]");
	    //System.out.println(StringUtils.strip(ids.toString(),"[]"));
	    SQLHelper sql=new SQLHelper();
	    sql.batchDelete("salary_increases", StringUtils.strip(ids.toString(),"[]"));
	    return   "redirect:findAll.do";
	}
}

package xhdProject.again.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import net.sf.json.JSONObject;
import xhdProject.again.Entites.user;
import xhdProject.again.Model.PageHelper;
import xhdProject.again.Model.SQLHelper;

@RestController
@RequestMapping("/")
//ModelAndView用来连接前端和后端,并且显示页面
public class CommonController {
	@RequestMapping("/Index")
	public ModelAndView Index() {
		ModelAndView mv = new ModelAndView("Common/NormalFrame/Index");
		mv.addObject("Module", new PageHelper().GetModule());
		return mv;
	}

	@RequestMapping("/Login")
	public ModelAndView Login() {
		ModelAndView mv = new ModelAndView("Common/NormalFrame/Login");
		return mv;
	}

	@RequestMapping("/Welcome")
	public ModelAndView Welcome() {
		ModelAndView mv = new ModelAndView("Common/NormalFrame/Welcome");
		return mv;
	}

	@RequestMapping("/LoginCheck")
	public int LoginCheck(HttpServletRequest request) {
		JSONObject object = JSONObject.fromObject(new PageHelper().GetPost(request));
		// 把string转化为json
		// 再把String对象转化为实体对象，太妙了
		user user = (user) JSONObject.toBean(object, user.class);
		SQLHelper sql = new SQLHelper();
		int result = sql.Select_login("user",
				"login= '" + user.getLogin() + "' and " + " password='" + user.getPassword() + "'");
		return result;
	}
}

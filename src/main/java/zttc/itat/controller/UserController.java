package zttc.itat.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zttc.itat.model.User;


@Controller
@RequestMapping("/user")
public class UserController {
	private Map<String, User> users = new HashMap<String, User>();
	
	public UserController() {
		users.put("xxx", new User("xxx", "123", "ezra", "no1"));
		users.put("hhh", new User("hhh", "123", "mark", "no2"));
		users.put("www", new User("www", "123", "zzz", "no3"));
	}

	@RequestMapping(value="/users",method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("users",users);
		return "user/list";
	}


	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(@Validated User user,BindingResult br) {
		if(br.hasErrors()) {
			//如果有错误直接跳转回add
			return "user/add";
		}
		users.put(user.getUsername(),user);		
		return "redirect:/user/users";	
	}
	
	//get请求 进行跳转页面
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute(new User());
		return "user/add";
	}
//	get请求 进行跳转页面
//	model第二种方法
//	@RequestMapping(value="/add",method = RequestMethod.GET)
//	public String add(@ModelAttribute("user")User user) {	
//		return "user/add";
//	}


	
}

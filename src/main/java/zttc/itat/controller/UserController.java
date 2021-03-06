package zttc.itat.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import zttc.itat.model.*;


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

	// 传一组文件 mutipartfile改为数组  由于用了数组，无法自动转换。需要添加@Requestparam 
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Validated User user, BindingResult br, @RequestParam("attachs")MultipartFile[] attachs, HttpServletRequest req)
			throws IOException {
		if (br.hasErrors()) {
			// 如果有错误直接跳转回add
			return "user/add";
		}
		String realpath = req.getSession().getServletContext().getRealPath("/resource/uploda");
		System.out.println(realpath);
		for (MultipartFile attach : attachs) {
			if(attach.isEmpty())continue;
			File f = new File(realpath + "/" + attach.getOriginalFilename());
			FileUtils.copyInputStreamToFile(attach.getInputStream(), f);
			System.out.println(
					attach.getName() + "," + attach.getOriginalFilename() + "," + "," + attach.getContentType());
		}
		users.put(user.getUsername(), user);
		return "redirect:/user/users";
	}

	// @RequestMapping(value="/add",method = RequestMethod.POST)
	// public String add(@Validated User user,BindingResult br,MultipartFile
	// attach,HttpServletRequest req) throws IOException{
	// if(br.hasErrors()) {
//			//如果有错误直接跳转回add
//			return "user/add";
//		}
//		String realpath = req.getSession().getServletContext().getRealPath("/resource/uploda");
//		System.out.println(realpath);
//		File f = new File(realpath+"/"+attach.getOriginalFilename());
//		FileUtils.copyInputStreamToFile(attach.getInputStream(), f);
//		System.out.println(attach.getName()+","+attach.getOriginalFilename()+","+","+attach.getContentType());
//		users.put(user.getUsername(),user);		
//		return "redirect:/user/users";	
//	}
//	
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
	
	@RequestMapping(value="/{username}",method = RequestMethod.GET)
	public String show(@PathVariable String username,Model model){
		model.addAttribute(users.get(username));
		return "user/show";
	}
	

	@RequestMapping(value="/{username}",method = RequestMethod.GET,params="json")
	@ResponseBody
	public  User show(@PathVariable String username){	
		return users.get(username);
	}


	@RequestMapping(value="/{username}/update",method = RequestMethod.GET)
	public String update(@PathVariable String username ,Model model) {
		model.addAttribute(users.get(username));
		System.out.println(users.get(username).toString());
		return "user/update";
	}


	@RequestMapping(value="/{username}/update",method = RequestMethod.POST)
		public String update(@PathVariable String username, @Validated User user, BindingResult br) {	
		System.out.println(users.get(username).toString());
		if(br.hasErrors()) {
			//如果有错误直接跳转回add
			return "user/update";
		}
		System.out.println(users.get(username).toString());
		users.put(username, user);
		return "redirect:/user/users";
	}

	@RequestMapping(value="/{username}/delete",method = RequestMethod.GET)
	public String delete(@PathVariable String username){
		users.remove(username);	
		return "redirect:/user/users";
	}

	//第一种获取异常的方式
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username,String password,HttpSession session){
		if(!users.containsKey(username)) {			
			throw  new UserException("用户名不存在");
		}
		User u = users.get(username);
		if(!u.getPassword().equals(password)){		
			throw new UserException("密码错误");
		}
		session.setAttribute("loginUser",u);
			return "redirect:/user/users";
	}
	
	
	/**
	 * 局部异常,只能处理控制器中的异常
	 */
//	
//	@ExceptionHandler(value= {UserException.class})
//	public String handlerException(UserException e,HttpServletRequest req){
//		req.setAttribute("e", e);
//		return "error";
//	}
}

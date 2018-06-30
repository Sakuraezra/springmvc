package zttc.itat.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class SpringController {
	
	
	//RequesMapping表示用哪个URL来对应
	@RequestMapping("/hello")	
	public String hello() {
			return "hello";
		}
	
	//使用模型驱动 传值
	@RequestMapping({"/modelvalue","/"})
    public String modelvalue(String username,Model model) {
		System.out.println("modelvalue");
	        model.addAttribute("username",username);
	        //   model.addAttribute(username);
	        //此时用那个作为Key？它默认使用对象类型为： Key--> 键值对
	    
	        //   model.addAttribute(User());
	        //此时用那个作为Key？它默认使用对象类型为： Key--> addAttribute(user, new User())
	    
	        
	        return "modelvalue";
	    }
	
	
	
	//传值操作 使用@RequestParam
	@RequestMapping({"/welcome","/"})
	public String welcome(@RequestParam ("username") String username) {
		System.out.println("welcome");
		System.out.println(username);
		return "welcome";
	}
	
	//传值操作   使用map
		@RequestMapping({"/mapvalue","/"})
		public String mapvalue(String username ,Map<String,Object> context) {
			System.out.println("mapvalue");
			context.put("username", username);
			System.out.println(username);
			return "mapvalue";
		}
		
}


package zttc.itat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SpringController {
	
	@RequestMapping({"/spring","/"})
	    public String spring(Model model) {
	        model.addAttribute("msg","Hello, World!");
	        return "spring";
	    }
}


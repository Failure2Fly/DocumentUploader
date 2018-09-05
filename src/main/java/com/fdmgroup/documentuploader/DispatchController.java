package com.fdmgroup.documentuploader;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DispatchController {
	
	@RequestMapping(value="/")
	public String landingPage(Model model){
		return "index";
	}
	
	@RequestMapping(value="/UserHome")
	public String dynamicServletLogic(Model model){
		return "UserHome";
	}

}

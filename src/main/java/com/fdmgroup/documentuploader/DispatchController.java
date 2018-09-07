package com.fdmgroup.documentuploader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DispatchController {

	@RequestMapping(value = "/")
	public String landingPage(Model model) {
		return "index";
	}

	@RequestMapping(value = "/UserHome/*", method = RequestMethod.GET)
	public String dynamicUserPageLogic(Model model) {
		return "userHome";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String userRegistration(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("listOfQuestion",SecurityQuestion.allQuestions());
		model.addAttribute(userAccount);
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String userRegistrationSubmit(@ModelAttribute UserAccount userAccount) {	
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		UserAccountJdbcTemplate jdbc = (UserAccountJdbcTemplate)context.getBean("UserAccountJdbcTemplate");
		jdbc.create(userAccount);
		return "userHome";	
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String userLogin(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute(userAccount);
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLoginSuccess(@ModelAttribute UserAccount userAccount) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		UserAccountJdbcTemplate jdbc = (UserAccountJdbcTemplate)context.getBean("UserAccountJdbcTemplate");
		return "userHome";
	}
}

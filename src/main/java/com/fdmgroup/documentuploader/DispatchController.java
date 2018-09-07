package com.fdmgroup.documentuploader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DispatchController {
	private ApplicationContext context;
	private ApplicationContext getContext(){
		if(context != null){
			return context;
		}else{
			 context = new ClassPathXmlApplicationContext("context.xml");
			return context;
		}
	}

	@RequestMapping(value = "/")
	public String landingPage(Model model) {
		return "index";
	}

	@RequestMapping(value = "/userHome", method = RequestMethod.GET)
	public String dynamicUserPageLogic(@ModelAttribute UserAccount userAccount) {

		return "userHome";
	}

	@RequestMapping(value = "/serviceLevels")
	public String ServiceLevels(Model model) {

		return "serviceLevels";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String userRegistration(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute(userAccount);
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String userRegistrationSubmit(@ModelAttribute UserAccount userAccount, HttpSession session) {
		boolean isValid = true;
		Validator validator = new Validator();
		isValid = validator.validateUserRegistration(userAccount);
		if (isValid) {
			context = getContext();
			UserAccountJdbcTemplate dao = (UserAccountJdbcTemplate) context.getBean("UserAccountJdbcTemplate");
			try {
				dao.create(userAccount);
				session.setAttribute("user", userAccount);
				return "userHome";
			} catch (Exception e) {
				File file = new File("H:\\DebugInCreate.txt");
				try {
					FileWriter writer= new FileWriter(file);
					writer.write(e.toString()); 
				      writer.flush();
				      writer.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				// TODO specify exception
				return "register";
			}finally{
				
			}
		} else {

			return "register";
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String userLogin(Model model) {
		
		UserAccount userAccount = new UserAccount();
		model.addAttribute(userAccount);
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLoginSuccess(@ModelAttribute UserAccount userAccount, HttpSession session) {
		Validator validator = new Validator();
		boolean isValid = validator.validateUserLogin(userAccount.getUsername(), userAccount.getPassword());
		if (isValid) {
			context = getContext();
			UserAccountJdbcTemplate dao = (UserAccountJdbcTemplate) context.getBean("UserAccountJdbcTemplate");
			userAccount=dao.read(userAccount.getUsername());
			session.setAttribute("user", userAccount);
			return "userHome";
		} else {
			return "login";
		}
	}

}

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

	@RequestMapping(value = "/userHome/*", method = RequestMethod.GET)
	public String dynamicUserPageLogic(Model model) {
		return "userHome";
	}

	@RequestMapping(value = "/serviceLevels")
	public String ServiceLevels(Model model) {
		return "serviceLevels";
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
		boolean isValid = true;
		Validator validator = new Validator();
		isValid = validator.validateUserRegistration(userAccount);
		if (isValid) {
			ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
			UserAccountJdbcTemplate jdbc = (UserAccountJdbcTemplate) context.getBean("UserAccountJdbcTemplate");
			try {
				jdbc.create(userAccount);
				return "login";
			} catch (Exception e) {
				File file = new File("H:\\Debug.txt");
				try {
					FileWriter writer = new FileWriter(file);
					writer.write(e.toString());
					writer.write(userAccount.toString()); 
				    writer.flush();
				    writer.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO specify exception
				return "register";
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
	public String userLoginSuccess(@ModelAttribute UserAccount userAccount) {
		Validator validator = new Validator();
		boolean isValid = validator.validateUserLogin(userAccount.getUsername(), userAccount.getPassword());
		if (isValid) {
			return "userHome";
		} else {
			return "login";
		}
	}
}

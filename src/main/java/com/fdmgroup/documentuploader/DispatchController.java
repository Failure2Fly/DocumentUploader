package com.fdmgroup.documentuploader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
		model.addAttribute(userAccount);
		return "register";
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String userRegistrationSubmit(@ModelAttribute UserAccount userAccount) {
		UserAccountJdbcTemplate dao = new UserAccountJdbcTemplate();
		dao.create(userAccount);
		
		
		File file = new File("H:\\Debug.txt");
		try {
			FileWriter writer= new FileWriter(file);
			writer.write(userAccount.toString()); 
		      writer.flush();
		      writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "userHome";
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String userLogin(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute(userAccount);
		
		
		return "login";
	}
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String userLoginSuccess(@ModelAttribute UserAccount userAccount) {
		
		return "userHome";
	}


	

}

package com.fdmgroup.documentuploader;

import javax.servlet.http.HttpServletRequest;

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


	@ModelAttribute("userAccount")
	public UserAccount getUser(HttpServletRequest request) {
		return (UserAccount) request.getAttribute("userAccount");
	}

	@ModelAttribute("loggedInUser")
	public UserAccount getLoggedInUser(HttpServletRequest request) {
		return (UserAccount) request.getAttribute("loggedInUser");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String userRegistration(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute(userAccount);
		return "register";
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String userRegistrationSubmit(@ModelAttribute UserAccount userAccount) {
	
		return "UserHome/";//+userAccount.getUsername();
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String userLogin(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute(userAccount);
		return "login";
	}
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String userLoginSuccess(@ModelAttribute UserAccount userAccount) {
		
		return "UserHome/";//+userAccount.getUsername();
	}


	

}

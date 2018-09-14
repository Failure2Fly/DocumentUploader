package com.fdmgroup.documentuploader.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.fdmgroup.documentuploader.dao.UserAccountDao;
import com.fdmgroup.documentuploader.enumeratedtypes.SecurityQuestion;
import com.fdmgroup.documentuploader.logic.Validator;
import com.fdmgroup.documentuploader.pojo.Questions;
import com.fdmgroup.documentuploader.pojo.UserAccount;

@Controller
public class RegistrationController {

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String userRegistration(Model model, HttpSession session) {
		session.setAttribute("loginError", "");
		UserAccount userAccount = new UserAccount();
		model.addAttribute("listOfQuestion", SecurityQuestion.allQuestions());
		model.addAttribute(userAccount);
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public RedirectView userRegistrationSubmit(@ModelAttribute UserAccount userAccount, HttpServletRequest request ,HttpSession session) {
		boolean isValid = true;
		Validator validator = new Validator();
		
		String[] errorString = validator.validateUserRegistration(userAccount);
		if (Objects.isNull(errorString)||errorString.length<0) {
			
			UserAccountDao dao = (UserAccountDao) DispatchController.getContext().getBean("UserAccountDao");
			try {
				List<Questions> list = new ArrayList<>();
				Questions question = new Questions(SecurityQuestion
						.valueOf(request.getParameter("question").toUpperCase().replace(" ", "_").replace("?", "")),request.getParameter("securityQuestion"));
				list.add(question);
				userAccount.setListQA(list);
				dao.create(userAccount);
				session.setAttribute("user", userAccount);
				return new RedirectView("login");
			} catch (Exception e) {
				return new RedirectView("register");
			} finally {
			}
		} else {
			session.setAttribute("registrationError", "This is the error string:"+errorString);
			session.setAttribute("usernameError", errorString[0]);
			session.setAttribute("passwordError", errorString[1]);
			session.setAttribute("firstNameError", errorString[2]);
			session.setAttribute("lastNameError", errorString[3]);
			session.setAttribute("emailError", errorString[4]);
			
			return new RedirectView("register");
		}
	}
	
	
}

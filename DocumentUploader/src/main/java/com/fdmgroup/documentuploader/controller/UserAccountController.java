package com.fdmgroup.documentuploader.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdmgroup.documentuploader.dao.UserAccountDao;
import com.fdmgroup.documentuploader.enumeratedtypes.SecurityQuestion;
import com.fdmgroup.documentuploader.logic.Validator;
import com.fdmgroup.documentuploader.pojo.UserAccount;

@Controller
public class UserAccountController {

	@RequestMapping(value = "/userHome", method = RequestMethod.GET)
	public String dynamicUserPageLogic(@ModelAttribute UserAccount userAccount, HttpSession session) {
		session.setAttribute("accountHomeError", "");
		session.setAttribute("accountDetailsPasswordError", "");
		session.setAttribute("accountDetailsEmailError", "");
		try {
			UserAccount user = (UserAccount) session.getAttribute("user");
			if (user.getUsername().equals("") || user.getUsername() == null) {
				return "login";
			}
		} catch (NullPointerException e) {
			return "login";
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			UserAccount user = (UserAccount) session.getAttribute("user");
			UserAccountDao dao = (UserAccountDao) DispatchController.getContext().getBean("UserAccountDao");
			String json = mapper.writeValueAsString(dao.readAccounts(dao.getThisId(user)));
			session.setAttribute("accountList", json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "userHome";
	}

	@RequestMapping(value = "/userDetails", method = RequestMethod.GET)
	public String userAccountDetails(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("listOfQuestion", SecurityQuestion.allQuestions());
		model.addAttribute(userAccount);
		return "userDetails";
	}

	@RequestMapping(value = "/userDetails", method = RequestMethod.POST)
	public RedirectView UserAccountDetails(@ModelAttribute UserAccount userAccount, HttpSession session) {
		UserAccountDao dao = (UserAccountDao) DispatchController.getContext().getBean("UserAccountDao");
		UserAccount user = (UserAccount) session.getAttribute("user");
		Validator validator = new Validator();
		if (userAccount.getFirstName().length() > 0) {
			user.setFirstName(userAccount.getFirstName());
		}
		if (userAccount.getLastName().length() > 0) {
			user.setLastName(userAccount.getLastName());
		}
		if (userAccount.getUserEmail().length() ==0) {
			session.setAttribute("accountDetailsEmailError", "");
		}else if(validator.emailValidator(userAccount.getUserEmail())){
			user.setUserEmail(userAccount.getUserEmail());
			session.setAttribute("accountDetailsEmailError", "");
		}else{
			session.setAttribute("accountDetailsEmailError", "That is not a valid email address.");
		}
		if (userAccount.getPassword().length() > 8) {
			session.setAttribute("accountDetailsPasswordError", "");
			user.setPassword(userAccount.getPassword());
		} else if (userAccount.getPassword().length() == 0) {
			session.setAttribute("accountDetailsPasswordError", "");
		} else {
			session.setAttribute("accountDetailsPasswordError", "Password must be at least 8 characters!");
		}

		dao.update(user);
		session.setAttribute("user", user);
		return new RedirectView("userDetails");
	}
}

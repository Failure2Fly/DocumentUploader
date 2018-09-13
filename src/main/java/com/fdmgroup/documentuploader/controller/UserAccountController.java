package com.fdmgroup.documentuploader.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
import com.fdmgroup.documentuploader.pojo.UserAccount;

@Controller
public class UserAccountController {

	private static ConfigurableApplicationContext context;

	public static ConfigurableApplicationContext getContext() {
		if (context != null) {
			context.close();
			context = new ClassPathXmlApplicationContext("context.xml");
			return context;
		} else {
			context = new ClassPathXmlApplicationContext("context.xml");
			return context;
		}
	}

	@RequestMapping(value = "/userHome", method = RequestMethod.GET)
	public String dynamicUserPageLogic(@ModelAttribute UserAccount userAccount, HttpSession session) {
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
			File file1 = new File("H:\\useraccountcontroller.txt");
			try {
				FileWriter writer = new FileWriter(file1);
				writer.write(user.toString());
				writer.flush();
				writer.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			UserAccountDao dao = (UserAccountDao) context.getBean("UserAccountDao");
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
		context = getContext();
		UserAccountDao dao = (UserAccountDao) context.getBean("UserAccountDao");
		UserAccount user = (UserAccount) session.getAttribute("user");
		if (userAccount.getFirstName().length() > 0) {
			user.setFirstName(userAccount.getFirstName());
		}
		if (userAccount.getLastName().length() > 0) {
			user.setLastName(userAccount.getLastName());
		}
		if (userAccount.getUserEmail().length() > 0) {
			user.setUserEmail(userAccount.getUserEmail());
		}
		if (userAccount.getPassword().length() > 0) {
			user.setPassword(userAccount.getPassword());
		}
		dao.update(user);
		session.setAttribute("user", user);
		return new RedirectView("userHome");
	}
}

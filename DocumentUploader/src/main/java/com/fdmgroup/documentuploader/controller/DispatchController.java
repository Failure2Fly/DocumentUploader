
package com.fdmgroup.documentuploader.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.fdmgroup.documentuploader.dao.UserAccountDao;
import com.fdmgroup.documentuploader.pojo.UserAccount;

@Controller
public class DispatchController {
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
	@RequestMapping(value = "/")
	public String landingPage(HttpSession session) {
		session.setAttribute("registrationError", "");
		session.setAttribute("usernameError","");
		session.setAttribute("passwordError", "");
		session.setAttribute("firstNameError", "");
		session.setAttribute("lastNameError", "");
		session.setAttribute("emailError", "");
		session.setAttribute("loginError", "");
		return "index";
	}

	@RequestMapping(value="/recoveryPassword",method = RequestMethod.GET)
	public String recoveryPasswordPost(Model model, HttpServletRequest request){
		model.addAttribute(new UserAccount());
		return "recoveryPassword";
	}

	@RequestMapping(value="/recoveryPassword",method = RequestMethod.POST)
	public String recoveryPasswordPost(HttpServletRequest request, HttpSession session){
		String username = (String) request.getParameter("username");
		UserAccountDao userDao = (UserAccountDao) getContext().getBean("UserAccountDao");
		UserAccount user = userDao.read(username);
		if(Objects.isNull(user)){
			request.setAttribute("recoveryError","The username "+ username +" requested was not found in the database");
			return "recoveryPassword"; 
		}
		session.setAttribute("user", user);
		String question = user.getListQA().get(0).getQuestion().name().replace("_", " ");
		request.setAttribute("securityQuestion",question.substring(0,1)
				+question.substring(1).toLowerCase()+"?");
		return "recoveryPassword";
	}
	@RequestMapping(value="/recoveryPassword/question",method = RequestMethod.POST)
	public RedirectView recoveryPasswordQuestionPost(HttpServletRequest request, HttpSession session){
		UserAccount user = ((UserAccount) session.getAttribute("user"));
		String databaseAnswer = user.getListQA().get(0).getAnswer();	
		if(!databaseAnswer.equals(request.getParameter("answer"))){
			request.setAttribute("recoveryError","Incorrect Answer "+databaseAnswer);
			return new RedirectView("/DocumentUploader/recoveryPassword"); 
		}
		UserAccountDao userDao = (UserAccountDao) getContext().getBean("UserAccountDao");
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String pwd = RandomStringUtils.random( 8, characters );
		user.setPassword(pwd);
		userDao.update(user);
		session.setAttribute("recoveryError","New password is "+pwd);
		return new RedirectView("/DocumentUploader/recoveryPassword");
	}

}


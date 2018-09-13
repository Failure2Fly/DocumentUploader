//package com.fdmgroup.documentuploader.controller;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.view.RedirectView;
//
//import com.fdmgroup.documentuploader.dao.UserAccountDao;
//import com.fdmgroup.documentuploader.enumeratedtypes.SecurityQuestion;
//import com.fdmgroup.documentuploader.logic.Validator;
//import com.fdmgroup.documentuploader.pojo.Questions;
//import com.fdmgroup.documentuploader.pojo.UserAccount;
//
//@Controller
//public class RegistrationController {
//
//	private static ConfigurableApplicationContext context;
//
//	public static ConfigurableApplicationContext getContext() {
//		if (context != null) {
//			context.close();
//			context = new ClassPathXmlApplicationContext("context.xml");
//			return context;
//		} else {
//			context = new ClassPathXmlApplicationContext("context.xml");
//			return context;
//		}
//	}
//	
//	@RequestMapping(value = "/register", method = RequestMethod.GET)
//	public String userRegistration(Model model) {
//		UserAccount userAccount = new UserAccount();
//		model.addAttribute("listOfQuestion", SecurityQuestion.allQuestions());
//		model.addAttribute(userAccount);
//		return "register";
//	}
//
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public RedirectView userRegistrationSubmit(@ModelAttribute UserAccount userAccount, HttpServletRequest request ,HttpSession session) {
//		boolean isValid = true;
//		Validator validator = new Validator();
//		isValid = validator.validateUserRegistration(userAccount);
//		if (isValid) {
//			context = getContext();
//			UserAccountDao dao = (UserAccountDao) context.getBean("UserAccountDao");
//			try {
//				List<Questions> list = new ArrayList<>();
//				Questions question = new Questions(SecurityQuestion
//						.valueOf(request.getParameter("question").toUpperCase().replace(" ", "_").replace("?", "")),request.getParameter("securityQuestion"));
//				list.add(question);
//				userAccount.setListQA(list);
//				dao.create(userAccount);
//				session.setAttribute("user", userAccount);
//				return new RedirectView("login");
//			} catch (Exception e) {
//				File file = new File("H:\\Debug.txt");
//				try {
//					FileWriter writer = new FileWriter(file);
//					writer.write(e.toString());
//					writer.write(userAccount.toString());
//					writer.flush();
//					writer.close();
//				} catch (IOException e2) {
//					e.printStackTrace();
//				}
//				return new RedirectView("register");
//			} finally {
//			}
//		} else {
//			return new RedirectView("register");
//		}
//	}
//}

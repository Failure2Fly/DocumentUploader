//package com.fdmgroup.documentuploader.controller;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.RedirectView;
//
//import com.fdmgroup.documentuploader.dao.BusinessAccountDao;
//import com.fdmgroup.documentuploader.dao.UserAccountDao;
//import com.fdmgroup.documentuploader.logic.Validator;
//import com.fdmgroup.documentuploader.pojo.UserAccount;
//
//@Controller
//public class LoginController {
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
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String userLogin(Model model, HttpSession session) {
//		UserAccount userAccount = new UserAccount();
//		session.setAttribute("user", userAccount);
//		model.addAttribute(userAccount);
//		return "login";
//	}
//
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public ModelAndView userLoginSuccess(@ModelAttribute UserAccount userAccount, HttpSession session) {
//		Validator validator = new Validator();
//		boolean isValid = validator.validateUserLogin(userAccount.getUsername(), userAccount.getPassword());
//		if (isValid) {
//			context = getContext();
//			UserAccountDao userDao = (UserAccountDao) context.getBean("UserAccountDao");
//			userAccount = userDao.read(userAccount.getUsername());
//			session.setAttribute("user", userAccount);
//			BusinessAccountDao businessDao = (BusinessAccountDao) context.getBean("BusinessAccountDao");
//			session.setAttribute("AccountList", businessDao.read(userAccount.getUsername()));
//			return new ModelAndView(new RedirectView("/userHome", true));
//		} else {
//			return new ModelAndView(new RedirectView("/login", true));
//		}
//	}
//}

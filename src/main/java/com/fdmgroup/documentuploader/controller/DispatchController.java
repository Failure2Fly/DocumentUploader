
package com.fdmgroup.documentuploader.controller;

import javax.servlet.http.HttpSession;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
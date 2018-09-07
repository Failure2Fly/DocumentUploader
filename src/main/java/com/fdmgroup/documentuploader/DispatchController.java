package com.fdmgroup.documentuploader;

import java.io.File;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DispatchController {
	private static ApplicationContext context;

	public static ApplicationContext getContext() {
		if (context != null) {
			return context;
		} else {
			context = new ClassPathXmlApplicationContext("context.xml");
			return context;
		}
	}

	@RequestMapping(value = "/")
	public String landingPage(Model model) {
		return "index";
	}

	@RequestMapping(value = "/userHome", method = RequestMethod.GET)
	public String dynamicUserPageLogic(@ModelAttribute UserAccount userAccount) {
		return "userHome";
	}
	
	@RequestMapping(value = "/userDetails", method = RequestMethod.GET)
	public String UserAccountDetails(@ModelAttribute UserAccount userAccount) {
		return "userDetails";
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
	public String userRegistrationSubmit(@ModelAttribute UserAccount userAccount, HttpSession session) {
		boolean isValid = true;
		Validator validator = new Validator();
		isValid = validator.validateUserRegistration(userAccount);
		if (isValid) {
			context = getContext();
			UserAccountJdbcTemplate dao = (UserAccountJdbcTemplate) context.getBean("UserAccountJdbcTemplate");
			try {
				dao.create(userAccount);
				session.setAttribute("user", userAccount);
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
					e.printStackTrace();
				}
				// TODO specify exception
				return "register";
			} finally {

			}
		} else {
			session.setAttribute("listOfQuestion",SecurityQuestion.allQuestions());
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
	public String userLoginSuccess(@ModelAttribute UserAccount userAccount, HttpSession session) {
		Validator validator = new Validator();
		boolean isValid = validator.validateUserLogin(userAccount.getUsername(), userAccount.getPassword());
		if (isValid) {
			context = getContext();
			UserAccountJdbcTemplate userDao = (UserAccountJdbcTemplate) context.getBean("UserAccountJdbcTemplate");
			userAccount = userDao.read(userAccount.getUsername());
			session.setAttribute("user", userAccount);
			BusinessAccountDao businessDao = (BusinessAccountDao) context.getBean("BusinessAccountDao");
			session.setAttribute("AccountList", businessDao.read(userAccount.getUsername()));
			
			return "userHome";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/createAccount", method = RequestMethod.GET)
	public String createAccountGet(Model model, HttpSession session) {
		model.addAttribute(new BusinessAccount());
		return "createAccount";

	}
	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public ModelAndView createAccountPost (@ModelAttribute BusinessAccount account, HttpSession session) {
		context = getContext();
		BusinessAccountDao dao = (BusinessAccountDao) context.getBean("BusinessAccountDao");
		
		UserAccount user= ((UserAccount) session.getAttribute("user"));
		account.setOwner(user);
		
		List<String> fileList = new ArrayList<>();
		account.setFileList(fileList);
		
		//TODO put in servicelevel data
		account.setServicelevel(new ServiceLevel());
		
		List<UserAccount> usersAssociated = new ArrayList<>();
		usersAssociated.add(account.getOwner());
		account.setUserAccounts(usersAssociated);
		
		dao.create(account);
		ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(artist);
            System.out.println("JSON = " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
		session.setAttribute("accountList", dao.read(user.getUsername()));
		
		
		return new ModelAndView(new RedirectView("/userHome", true));

	}
	

}

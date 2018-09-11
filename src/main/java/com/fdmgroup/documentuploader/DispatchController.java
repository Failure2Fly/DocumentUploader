package com.fdmgroup.documentuploader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public String dynamicUserPageLogic(@ModelAttribute UserAccount userAccount, HttpSession session) {
		try {
			UserAccount user = (UserAccount) session.getAttribute("user");
			if (user.getUsername().equals("") || user.getUsername() == null) {
				return "login";
			}
		} catch (NullPointerException e) {
			return "login";
		}

		BusinessAccountDao businessDao = (BusinessAccountDao) context.getBean("BusinessAccountDao");
		ObjectMapper mapper = new ObjectMapper();
		try {
			UserAccount user = (UserAccount) session.getAttribute("user");
			String json = mapper.writeValueAsString(businessDao.read(user.getUsername()));
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

	@RequestMapping(value = "/serviceLevels")
	public String ServiceLevels(Model model) {
		return "serviceLevels";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String userRegistration(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("listOfQuestion", SecurityQuestion.allQuestions());
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
			UserAccountDao dao = (UserAccountDao) context.getBean("UserAccountDao");
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
				return "register";
			} finally {

			}
		} else {
			session.setAttribute("listOfQuestion", SecurityQuestion.allQuestions());
			return "register";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String userLogin(Model model, HttpSession session) {
		UserAccount userAccount = new UserAccount();
		session.setAttribute("user", userAccount);
		model.addAttribute(userAccount);
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView userLoginSuccess(@ModelAttribute UserAccount userAccount, HttpSession session) {
		Validator validator = new Validator();
		boolean isValid = validator.validateUserLogin(userAccount.getUsername(), userAccount.getPassword());
		if (isValid) {
			context = getContext();
			UserAccountDao userDao = (UserAccountDao) context.getBean("UserAccountDao");
			userAccount = userDao.read(userAccount.getUsername());
			session.setAttribute("user", userAccount);
			BusinessAccountDao businessDao = (BusinessAccountDao) context.getBean("BusinessAccountDao");
			session.setAttribute("AccountList", businessDao.read(userAccount.getUsername()));

			return new ModelAndView(new RedirectView("/userHome", true));
		} else {
			return new ModelAndView(new RedirectView("/login", true));
		}
	}
	@RequestMapping(value = "/createAccount", method = RequestMethod.GET)
	public String createAccountGet(Model model, HttpSession session) {
		model.addAttribute(new BusinessAccount());
		return "createAccount";

	}

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public ModelAndView createAccountPost(@ModelAttribute BusinessAccount account, HttpSession session) {
		context = getContext();
		BusinessAccountDao dao = (BusinessAccountDao) context.getBean("BusinessAccountDao");

		UserAccount user = ((UserAccount) session.getAttribute("user"));
		account.setOwner(user);

		List<String> fileList = new ArrayList<>();
		account.setFileList(fileList);

		account.setServicelevel(new ServiceLevel());

		List<UserAccount> usersAssociated = new ArrayList<>();
		usersAssociated.add(account.getOwner());
		account.setUserAccounts(usersAssociated);

		dao.create(account);


		return new ModelAndView(new RedirectView("/userHome", true));

	}

	@RequestMapping(value = "/accountDetails/{accountId}", method = RequestMethod.GET)
	public String AccountDetailsGet(Model model, HttpSession session,
			@PathVariable(value = "accountId") String accountId) {
		BusinessAccountDao businessDao = (BusinessAccountDao) context.getBean("BusinessAccountDao");
		BusinessAccount businessAccount = businessDao.read(new Integer(Integer.parseInt(accountId)));
		session.setAttribute("account", businessAccount);
		Document document = new Document();
		model.addAttribute(document);
		return "accountDetails";

	}

	@RequestMapping(value = "/accountDetails/{accountId}", method = RequestMethod.POST)
	public String AccountDetailsPost(@ModelAttribute Document document, HttpSession session, @PathVariable(value = "accountId") String accountId,@RequestParam("file") MultipartFile file) {
		DocumentDao documentDao = (DocumentDao) context.getBean("DocumentDao");
		File directory = new File("H:\\repository\\"+accountId);
	    if (! directory.exists()){
	        directory.mkdir();
	    }
	    int fileId = documentDao.getId();
	    File sourcePath = document.getSourcePath().toFile();
		String repositoryPath = "H:\\repository\\"+accountId+"\\"+fileId+sourcePath.getName();
		document.setRepositoryPath(Paths.get(repositoryPath));
		documentDao.create(document);
		return "accountDetails";

		
	}
	@RequestMapping(value = "/accountHome", method = RequestMethod.GET)
	public String AccountHomeGet(Model model,HttpSession session){
		return null;
	}

}

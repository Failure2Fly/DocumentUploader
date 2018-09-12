package com.fdmgroup.documentuploader.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ConfigurableApplicationContext;
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
import com.fdmgroup.documentuploader.dao.BusinessAccountDao;
import com.fdmgroup.documentuploader.dao.DocumentDao;
import com.fdmgroup.documentuploader.dao.UserAccountDao;
import com.fdmgroup.documentuploader.enumeratedtypes.SecurityQuestion;
import com.fdmgroup.documentuploader.logic.Validator;
import com.fdmgroup.documentuploader.pojo.BusinessAccount;
import com.fdmgroup.documentuploader.pojo.Document;
import com.fdmgroup.documentuploader.pojo.ServiceLevel;
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

	@RequestMapping(value = "/accountHome/{accountId}", method = RequestMethod.GET)
	public String AccountDetailsGet(Model model, HttpSession session,
			@PathVariable(value = "accountId") String accountId) {
		BusinessAccountDao businessDao = (BusinessAccountDao) context.getBean("BusinessAccountDao");
		DocumentDao documentDao = (DocumentDao) context.getBean("DocumentDao");
		BusinessAccount businessAccount = businessDao.read(new Integer(Integer.parseInt(accountId)));
		session.setAttribute("account", businessAccount);
		File file = new File("");
		model.addAttribute(file);

		List<Document> fileList = documentDao.read(Integer.parseInt(accountId));

		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		json=json+"[";
		if(fileList.size()>0){
		for(Document document:fileList){
			json =json+"{\"name\":\""+document.getName()+"\",";
			json =json+"\"repositoryPath\":\""+document.getRepositoryPath()+"\",";
			json =json+"\"date\":\""+document.getDate().toString()+"\"},";
		}
		
		File debugFile = new File("H:\\DebugJsonFileList.txt");
		try {
			FileWriter writer = new FileWriter(debugFile);
			writer.write("Json so far: " + json + " Json length: " +json.length());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		json=json.substring(0,json.length()-1);
		}
		json=json+"]";
		
		session.setAttribute("fileList", json);
		
		return "accountHome";
	}

	@RequestMapping(value = "/accountHome/{accountId}", method = RequestMethod.POST)
	public String AccountDetailsPost(HttpSession session, @PathVariable(value = "accountId") String accountId,
			@RequestParam MultipartFile file) {
		DocumentDao documentDao = (DocumentDao) context.getBean("DocumentDao");
		File directory = new File("H:\\repository\\" + accountId);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		Document document = new Document();
		document.setName(file.getOriginalFilename());
		document.setAccountId(Integer.parseInt(accountId));
		File sourceFile = new File(file.getOriginalFilename());
		try {
			file.transferTo(sourceFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		document.setSourcePath(Paths.get(sourceFile.toString()));
		String repositoryPath = "H:\\repository\\" + accountId + "\\" + file.getOriginalFilename();
		document.setRepositoryPath(Paths.get(repositoryPath));

		documentDao.create(document);

		return "accountHome";
	}

	@RequestMapping(value = "/accountDetails", method = RequestMethod.GET)
	public String accountDetailsGet(Model model, HttpSession session) {
		model.addAttribute(new BusinessAccount());
		
		return "accountDetails";
	}

	@RequestMapping(value = "/accountDetails", method = RequestMethod.POST)
	public String accountDetailsPost(HttpServletRequest request, HttpSession session) {
		String addAccount = request.getParameter("add");
		String remove = request.getParameter("remove");
		String accoutName = request.getParameter("AccountName");
		return "accountDetails";
	}
	
	


}

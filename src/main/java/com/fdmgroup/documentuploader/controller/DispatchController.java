
package com.fdmgroup.documentuploader.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.HandlerMapping;
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
import com.fdmgroup.documentuploader.pojo.Questions;
import com.fdmgroup.documentuploader.pojo.ServiceLevel;
import com.fdmgroup.documentuploader.pojo.UserAccount;
import com.fdmgroup.documentuploader.enumeratedtypes.ServiceLevels;

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
		ObjectMapper mapper = new ObjectMapper();
		try {
			UserAccount user = (UserAccount) session.getAttribute("user");
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
	public RedirectView userRegistrationSubmit(@ModelAttribute UserAccount userAccount, HttpServletRequest request ,HttpSession session) {
		boolean isValid = true;
		Validator validator = new Validator();
		isValid = validator.validateUserRegistration(userAccount);
		if (isValid) {
			context = getContext();
			UserAccountDao dao = (UserAccountDao) context.getBean("UserAccountDao");
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
				return new RedirectView("register");
			} finally {
			}
		} else {
			return new RedirectView("register");
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
		model.addAttribute("listOfLevels", ServiceLevels.allServiceLevels());
		return "createAccount";
	}

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public ModelAndView createAccountPost(@ModelAttribute BusinessAccount account, HttpServletRequest request,
			HttpSession session) {
		context = getContext();
		BusinessAccountDao dao = (BusinessAccountDao) context.getBean("BusinessAccountDao");
		UserAccount user = ((UserAccount) session.getAttribute("user"));
		account.setOwner(user);
		List<String> fileList = new ArrayList<>();
		account.setFileList(fileList);
		ServiceLevels level = ServiceLevels.valueOf(request.getParameter("level").toUpperCase());
		account.setServicelevel(new ServiceLevel(level));
		List<UserAccount> usersAssociated = new ArrayList<>();
		usersAssociated.add(account.getOwner());
		account.setUserAccounts(usersAssociated);
		dao.create(account);
		File file1 = new File("H:\\createAccount.txt");
		try {
			FileWriter writer = new FileWriter(file1);
			writer.write(account.getServicelevel().getServiceLevel().toString());
			writer.flush();
			writer.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
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
		json = json + "[";
		if (fileList.size() > 0) {
			for (Document document : fileList) {
				json = json + "{\"name\":\"" + document.getName() + "\",";
				json = json + "\"repositoryPath\":\"" + document.getRepositoryPath() + "\",";
				json = json + "\"date\":\"" + document.getDate().toString() + "\"},";
			}

			json = json.substring(0, json.length() - 1);
		}
		json = json + "]";

		session.setAttribute("fileList", json);

		return "accountHome";
	}

	@RequestMapping(value = "/accountHome/{accountId}", method = RequestMethod.POST)
	public RedirectView AccountDetailsPost(HttpSession session, @PathVariable(value = "accountId") String accountId,
			@RequestParam MultipartFile file) {
		DocumentDao documentDao = (DocumentDao) context.getBean("DocumentDao");
		int fileId = documentDao.getId();
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
		String repositoryPath = "H:\\repository\\" + accountId + "\\" + fileId + file.getOriginalFilename();
		document.setRepositoryPath(Paths.get(repositoryPath));

		File debugFile = new File("H:\\DebugUpload.txt");
		try {
			FileWriter writer = new FileWriter(debugFile);
			writer.write("Document to be uploaded: " + document);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		documentDao.create(document, file);

		return new RedirectView("/DocumentUploader/refreshAccount");
	}

	@RequestMapping(value = "/refreshAccount", method = RequestMethod.GET)
	public RedirectView refreshAccount(Model model, HttpSession session) {
		BusinessAccount account = (BusinessAccount) session.getAttribute("account");
		return new RedirectView("/DocumentUploader/accountHome/" + account.getBusinessAccountId());
	}

	@RequestMapping(value = "/downloadFile/**", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, HttpServletRequest request) {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		path = path.substring(14);
		path = path.replaceAll("%20", " ");
		File file1 = new File("H:\\DebugDownload1.txt");
		try {
			FileWriter writer = new FileWriter(file1);
			writer.write("File attempted to download: " + path);
			writer.flush();
			writer.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			InputStream input = new FileInputStream(path);
			org.apache.commons.io.IOUtils.copy(input, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			File file2 = new File("H:\\DebugDownload2.txt");
			try {
				FileWriter writer = new FileWriter(file2);
				writer.write("File attempted to download: " + path + " with error " + e);
				writer.flush();
				writer.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/deleteFile/**", method = RequestMethod.GET)
	public RedirectView deleteFile(HttpSession session, HttpServletRequest request) {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		path = path.substring(12);
		path = path.replaceAll("%20", " ");

		DocumentDao documentDao = (DocumentDao) context.getBean("DocumentDao");
		Document document = documentDao.read(path);
		File file2 = new File("H:\\DebugDelete.txt");
		try {
			FileWriter writer = new FileWriter(file2);
			writer.write("File attempted to delete: " + document);
			writer.flush();
			writer.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		documentDao.delete(document);
		BusinessAccount account = (BusinessAccount) session.getAttribute("account");
		return new RedirectView("/DocumentUploader/accountHome/" + account.getBusinessAccountId());

	}

	@RequestMapping(value = "/accountDetails", method = RequestMethod.GET)
	public String accountDetailsGet(HttpSession session) {
		session.setAttribute("accountDetailsError", "");
		UserAccount user = (UserAccount) session.getAttribute("user");
		BusinessAccount account = (BusinessAccount) session.getAttribute("account");
		UserAccountDao userDao = (UserAccountDao) getContext().getBean("UserAccountDao");
		if (userDao.getThisId(account.getOwner()) != userDao.getThisId(user)) {
			return "accountHome";
		} else {
			ObjectMapper mapper = new ObjectMapper();
			try {
				BusinessAccountDao dao = (BusinessAccountDao) context.getBean("BusinessAccountDao");
				String json = mapper.writeValueAsString(dao.read(account.getBusinessAccountId()));
				session.setAttribute("accountDetailJson", json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return "accountDetails";
		}
	}

	@RequestMapping(value = "/accountDetails/delete", method = RequestMethod.POST)
	public RedirectView accountDetailsDelete(HttpServletRequest request, HttpSession session) {
		BusinessAccountDao businessDao = (BusinessAccountDao) getContext().getBean("BusinessAccountDao");
		BusinessAccount account = (BusinessAccount) session.getAttribute("account");
		businessDao.delete(account);
		return new RedirectView("/DocumentUploader/userHome");

	}

	@RequestMapping(value = "/accountDetails/addUser", method = RequestMethod.POST)
	public RedirectView accountDetailsAddUser(HttpServletRequest request, HttpSession session) {
		BusinessAccount account = (BusinessAccount) session.getAttribute("account");
		String addUser = request.getParameter("add");
		UserAccountDao userDao = (UserAccountDao) getContext().getBean("UserAccountDao");
		UserAccount addedUser = userDao.read(addUser);
		if (account.getUserAccounts().contains(addedUser)) {
			session.setAttribute("accountDetailsError", "This user has already been added!");
			return new RedirectView("/DocumentUploader/accountDetails");
		} else if(account.getUserAccounts().size()>=account.getServicelevel().getUserLimit()) {
			session.setAttribute("accountDetailsError", "Your repository cannot support more users at your service level!");
			return new RedirectView("/DocumentUploader/accountDetails");
		}else{
			account.getUserAccounts().add(addedUser);
			BusinessAccountDao businessDao = (BusinessAccountDao) getContext().getBean("BusinessAccountDao");
			businessDao.update(account);
			session.setAttribute("account", account);
		}
		return new RedirectView("/DocumentUploader/accountDetails");

	}

	@RequestMapping(value = "/accountDetails/changeName", method = RequestMethod.POST)
	public RedirectView accountChangeName(HttpServletRequest request, HttpSession session) {
		BusinessAccount account = (BusinessAccount) session.getAttribute("account");
		String newName = request.getParameter("accountName");
		account.setAccountName(newName);
		BusinessAccountDao businessDao = (BusinessAccountDao) getContext().getBean("BusinessAccountDao");
		businessDao.update(account);
		session.setAttribute("account", account);
		return new RedirectView("/DocumentUploader/accountDetails");

	}

	@RequestMapping(value = "/accountDetails/removeUser", method = RequestMethod.POST)
	public RedirectView accountremoveUser(HttpServletRequest request, HttpSession session) {
		BusinessAccount account = (BusinessAccount) session.getAttribute("account");
		String removeUser = request.getParameter("remove");
		UserAccountDao userDao = (UserAccountDao) getContext().getBean("UserAccountDao");
		UserAccount removedUser = userDao.read(removeUser);
		
		if (!removedUser.getUsername().equals(account.getOwner().getUsername())) {
			account.getUserAccounts().remove(removedUser);
			BusinessAccountDao businessDao = (BusinessAccountDao) getContext().getBean("BusinessAccountDao");
			businessDao.update(account);
			session.setAttribute("account", account);
		}else{
			session.setAttribute("accountDetailsError", "That user is the owner and cannot be removed!");
		}
		return new RedirectView("/DocumentUploader/accountDetails/");

	}

}

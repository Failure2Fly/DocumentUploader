package com.fdmgroup.documentuploader.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import com.fdmgroup.documentuploader.enumeratedtypes.ServiceLevels;
import com.fdmgroup.documentuploader.pojo.BusinessAccount;
import com.fdmgroup.documentuploader.pojo.Document;
import com.fdmgroup.documentuploader.pojo.ServiceLevel;
import com.fdmgroup.documentuploader.pojo.UserAccount;

@Controller
public class BusinessAccountController {

	@RequestMapping(value = "/createRepository", method = RequestMethod.GET)
	public String createRepositoryGet(Model model, HttpSession session) {
		model.addAttribute(new BusinessAccount());
		model.addAttribute("listOfLevels", ServiceLevels.allServiceLevels());
		return "createRepository";
	}

	@RequestMapping(value = "/createRepository", method = RequestMethod.POST)
	public ModelAndView createRepositoryPost(@ModelAttribute BusinessAccount account, HttpServletRequest request,
			HttpSession session) {
		
		BusinessAccountDao dao = (BusinessAccountDao) DispatchController.getContext().getBean("BusinessAccountDao");
		UserAccount user = ((UserAccount) session.getAttribute("user"));
		account.setOwner(user);
		List<String> fileList = new ArrayList<>();
		account.setFileList(fileList);
		ServiceLevels level = ServiceLevels.valueOf(request.getParameter("level").toUpperCase());
		account.setServiceLevel(new ServiceLevel(level));
		List<UserAccount> usersAssociated = new ArrayList<>();
		usersAssociated.add(account.getOwner());
		account.setUserAccounts(usersAssociated);
		dao.create(account);
		File file1 = new File("H:\\createAccount.txt");
		try {
			FileWriter writer = new FileWriter(file1);
			writer.write(account.getServiceLevel().getServiceLevel().toString());
			writer.flush();
			writer.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		return new ModelAndView(new RedirectView("/userHome", true));
	}

	@RequestMapping(value = "/repositoryHome/{accountId}", method = RequestMethod.GET)
	public String RepositoryDetailsGet(Model model, HttpSession session,
			@PathVariable(value = "accountId") String accountId) {
		session.setAttribute("repositoryDetailsError", "");

		BusinessAccountDao businessDao = (BusinessAccountDao) DispatchController.getContext().getBean("BusinessAccountDao");
		DocumentDao documentDao = (DocumentDao) DispatchController.getContext().getBean("DocumentDao");
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

		return "repositoryHome";
	}

	@RequestMapping(value = "/repositoryHome/{accountId}", method = RequestMethod.POST)
	public RedirectView RepositoryDetailsPost(HttpSession session, @PathVariable(value = "accountId") String accountId,
			@RequestParam MultipartFile file) {
		BusinessAccount account = ((BusinessAccount) session.getAttribute("account"));
		DocumentDao documentDao = (DocumentDao) DispatchController.getContext().getBean("DocumentDao");
		
		
		if(file.isEmpty()){
			session.setAttribute("accountHomeError", "You forgot to include a file to upload!");
		}else if(documentDao.read(account.getBusinessAccountId()).size() < account.getServiceLevel().getDocumentLimit()) {
			
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
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			document.setSourcePath(Paths.get(sourceFile.toString()));
			String repositoryPath = "H:\\repository\\" + accountId + "\\" + fileId + file.getOriginalFilename();
			document.setRepositoryPath(Paths.get(repositoryPath));
			documentDao.create(document, file);
			session.setAttribute("accountHomeError", "");
		} else{
			session.setAttribute("accountHomeError", "You have reached your file limit at this service level!");
		}

		return new RedirectView("/DocumentUploader/refreshAccount");
	}

	@RequestMapping(value = "/refreshAccount", method = RequestMethod.GET)
	public RedirectView refreshAccount(Model model, HttpSession session) {
		BusinessAccount account = (BusinessAccount) session.getAttribute("account");
		return new RedirectView("/DocumentUploader/repositoryHome/" + account.getBusinessAccountId());
	}

	@RequestMapping(value = "/repositoryDetails", method = RequestMethod.GET)
	public String repositoryDetailsGet(HttpSession session) {
		UserAccount user = (UserAccount) session.getAttribute("user");
		BusinessAccount account = (BusinessAccount) session.getAttribute("account");
		UserAccountDao userDao = (UserAccountDao) DispatchController.getContext().getBean("UserAccountDao");
		if (userDao.getThisId(account.getOwner()) != userDao.getThisId(user)) {
			return "repositoryHome";
		} else {
			ObjectMapper mapper = new ObjectMapper();
			try {
				BusinessAccountDao dao = (BusinessAccountDao) DispatchController.getContext().getBean("BusinessAccountDao");
				String json = mapper.writeValueAsString(dao.read(account.getBusinessAccountId()));
				session.setAttribute("accountDetailJson", json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return "repositoryDetails";
		}
	}

	@RequestMapping(value = "/repositoryDetails/delete", method = RequestMethod.POST)
	public RedirectView repositoryDetailsDelete(HttpServletRequest request, HttpSession session) {
		BusinessAccountDao businessDao = (BusinessAccountDao) DispatchController.getContext().getBean("BusinessAccountDao");
		BusinessAccount account = (BusinessAccount) session.getAttribute("account");
		businessDao.delete(account);
		session.setAttribute("repositoryDetailsError", "");
		return new RedirectView("/DocumentUploader/userHome");

	}

	@RequestMapping(value = "/repositoryDetails/addUser", method = RequestMethod.POST)
	public RedirectView repositoryDetailsAddUser(HttpServletRequest request, HttpSession session) {
		BusinessAccount account = (BusinessAccount) session.getAttribute("account");
		String addUser = request.getParameter("add");
		UserAccountDao userDao = (UserAccountDao) DispatchController.getContext().getBean("UserAccountDao");
		UserAccount addedUser = userDao.read(addUser);
		if (Objects.isNull(addedUser)){
			session.setAttribute("repositoryDetailsError", "This user does not exist!");
			return new RedirectView("/DocumentUploader/repositoryDetails");
		}else if (account.getUserAccounts().contains(addedUser)) {
			session.setAttribute("repositoryDetailsError", "This user has already been added!");
			return new RedirectView("/DocumentUploader/repositoryDetails");
		} else if(account.getUserAccounts().size()>=account.getServiceLevel().getUserLimit()) {
			session.setAttribute("repositoryDetailsError", "Your repository cannot support more users at your service level!");
			return new RedirectView("/DocumentUploader/repositoryDetails");
		}else{
			session.setAttribute("repositoryDetailsError", "");
			account.getUserAccounts().add(addedUser);
			BusinessAccountDao businessDao = (BusinessAccountDao) DispatchController.getContext().getBean("BusinessAccountDao");
			businessDao.update(account);
			session.setAttribute("account", account);
		}
		return new RedirectView("/DocumentUploader/repositoryDetails");

	}

	@RequestMapping(value = "/repositoryDetails/changeName", method = RequestMethod.POST)
	public RedirectView repositoryChangeName(HttpServletRequest request, HttpSession session) {
		session.setAttribute("repositoryDetailsError", "");
		BusinessAccount account = (BusinessAccount) session.getAttribute("account");
		String newName = request.getParameter("accountName");
		account.setAccountName(newName);
		BusinessAccountDao businessDao = (BusinessAccountDao) DispatchController.getContext().getBean("BusinessAccountDao");
		businessDao.update(account);
		session.setAttribute("account", account);
		return new RedirectView("/DocumentUploader/repositoryDetails");

	}

	@RequestMapping(value = "/repositoryDetails/removeUser", method = RequestMethod.POST)
	public RedirectView repositoryRemoveUser(HttpServletRequest request, HttpSession session) {
		BusinessAccount account = (BusinessAccount) session.getAttribute("account");
		String removeUser = request.getParameter("remove");
		UserAccountDao userDao = (UserAccountDao) DispatchController.getContext().getBean("UserAccountDao");
		UserAccount removedUser = userDao.read(removeUser);

		if (!removedUser.getUsername().equals(account.getOwner().getUsername())) {
			session.setAttribute("repositoryDetailsError", "");
			account.getUserAccounts().remove(removedUser);
			BusinessAccountDao businessDao = (BusinessAccountDao) DispatchController.getContext().getBean("BusinessAccountDao");
			businessDao.update(account);
			session.setAttribute("account", account);

		}else{
			session.setAttribute("repositoryDetailsError", "That user is the owner and cannot be removed!");
		}
		return new RedirectView("/DocumentUploader/repositoryDetails/");
	}
}

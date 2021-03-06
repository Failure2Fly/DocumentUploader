
package com.fdmgroup.documentuploadertests.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import com.fdmgroup.documentuploader.controller.DispatchController;
import com.fdmgroup.documentuploader.dao.BusinessAccountDao;
import com.fdmgroup.documentuploader.dao.UserAccountDao;
import com.fdmgroup.documentuploader.enumeratedtypes.SecurityQuestion;
import com.fdmgroup.documentuploader.pojo.BusinessAccount;
import com.fdmgroup.documentuploader.pojo.Questions;
import com.fdmgroup.documentuploader.pojo.ServiceLevel;
import com.fdmgroup.documentuploader.pojo.UserAccount;

public class BusinessAccountDaoTest {

	@Test
	public void test_willCheckIfBusinessAccountCreated() {
		ApplicationContext context = DispatchController.getContext();
		BusinessAccountDao businessDao = (BusinessAccountDao)context.getBean("BusinessAccountDao");
		
		Questions question = new Questions(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET,"SheepFace");
		List<Questions> QA = new ArrayList<>();
		QA.add(question);
		UserAccountDao userDao = (UserAccountDao)context.getBean("UserAccountDao");
		String username ="ImaginaryUser";
		UserAccount user = new UserAccount(username,"Imaginary","User","password","fake@gmail.com",QA);
		
		userDao.create(user);
		List<UserAccount> users = new ArrayList<>();
		users.add(user);
		List<String> files= null;
		ServiceLevel level = null;
		String accountName="TestAccount";
		
		BusinessAccount account = new BusinessAccount(user,level,users,files,accountName);
		BusinessAccount account2 = new BusinessAccount(user,level,users,files,accountName);
		account.setServiceLevel(new ServiceLevel(1));
		account2.setServiceLevel(new ServiceLevel(1));
		System.out.println(account);
		businessDao.create(account);
		businessDao.create(account2);
		
		List<BusinessAccount>accounts = businessDao.read(username);
		for(BusinessAccount acc:accounts){
			account=acc;
			businessDao.delete(account);
		}
		
		userDao.delete(user);
		try{

		}catch(EmptyResultDataAccessException e){
			
		}
		
	}
	
	@Test
	public void test_businessAccountUpdateMethodWorks_UpdatesReflectedInDatabase(){
		ApplicationContext context = DispatchController.getContext();
		UserAccountDao userAccount = (UserAccountDao)context.getBean("UserAccountDao");
		BusinessAccountDao businessDao = (BusinessAccountDao)context.getBean("BusinessAccountDao");
		Questions question = new Questions(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET,"SheepFace");
		List<Questions> QA = new ArrayList<>();
		QA.add(question);
		UserAccount user = new UserAccount("IOwnNow","You","IOwn","youShallNotPass","userEmail",QA);
		UserAccount user2 = new UserAccount("IOwnNow2","You2","IOwn2","youShallNotPass2","userEmail2",QA);
		List<UserAccount> users = new ArrayList<>();
		users.add(user);
		userAccount.create(user);
		userAccount.create(user2);
		List<String> files= null;
		ServiceLevel level = null;
		String accountName="TestAccount";
		BusinessAccount account = new BusinessAccount(user,level,users,files,accountName);
		account.setServiceLevel(new ServiceLevel(1));
		businessDao.create(account);
		List<BusinessAccount> tempAccounts=businessDao.read(user.getUsername());
		account =tempAccounts.get(0);
		account.setAccountName("WhooAccount!");
		users.add(user2);
		account.setUserAccounts(users);
		businessDao.update(account);
		List<BusinessAccount> accounts=businessDao.read(user.getUsername());
		for(BusinessAccount acc:accounts){
			account=acc;
		}

		businessDao.delete(account);

		userAccount.delete(user);
		userAccount.delete(user2);
		


	}
	@Test
	public void test_businessAccountReadMethodWorks_WhenPassedInAnInteger() {
		ApplicationContext context = DispatchController.getContext();
		UserAccountDao userAccount = (UserAccountDao)context.getBean("UserAccountDao");
		BusinessAccountDao businessDao = (BusinessAccountDao)context.getBean("BusinessAccountDao");
		Questions question = new Questions(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET,"SheepFace");
		List<Questions> QA = new ArrayList<>();
		QA.add(question);
		UserAccount user = new UserAccount("IOwnNow","You","IOwn","youShallNotPass","userEmail",QA);
		
		List<UserAccount> users = new ArrayList<>();
		users.add(user);
		userAccount.create(user);
		List<String> files= null;
		ServiceLevel level = null;
		String accountName="TestAccount";
		
		BusinessAccount account = new BusinessAccount(user,level,users,files,accountName);
		account.setServiceLevel(new ServiceLevel(1));
		businessDao.create(account);
		
		List<BusinessAccount> accounts=businessDao.read(user.getUsername());
		for(BusinessAccount acc:accounts){
			account=acc;
		}
		
		businessDao.read(new Integer(account.getBusinessAccountId()));
		
		businessDao.delete(account);
		
		userAccount.delete(user);
	}
	@Test 
	public void test_generalBusinessAccountFunctionality(){
		BusinessAccount account = new BusinessAccount(null,  null,
				null, null,"TestAccount", 1);
		BusinessAccount account2 = new BusinessAccount(null,  null,
				null, null,"TestAccount2", 2);
		BusinessAccount account3 = new BusinessAccount(null,  null,
				null, null,"TestAccount", 1);
		if(account.equals(account2)){
			System.out.println("Equal");
		}
		if(account.equals(account3)){
			System.out.println("Equal");
		}
		
	}
	
}




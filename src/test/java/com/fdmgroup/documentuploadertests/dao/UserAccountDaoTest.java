package com.fdmgroup.documentuploadertests.dao;



import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
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



public class UserAccountDaoTest {
	private ApplicationContext context;
	private UserAccountDao userAccount;
	@Before
	public void setUp(){
		context= DispatchController.getContext();
		userAccount = (UserAccountDao)context.getBean("UserAccountDao");
	}
	
	
	
	@Test 
	public void testReadNonexistentUser(){
		String username = "ImaginaryUser";
		
	 	UserAccount expected = new UserAccount(username,"Imaginary","User","password","fake@gmail.com");
		try{
			System.out.println("This is the result of a nonexistent read"+userAccount.read(username));
		}catch(EmptyResultDataAccessException e){
		}
	}
		
	@Test
	public void testCreateThenReadThenDelete(){
		Questions question = new Questions(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET,"SheepFace");
		List<Questions> QA = new ArrayList<>();
		QA.add(question);
		String username = "LukeWeatherstein";
		
		UserAccount expected = new UserAccount(username,"Luke","Weatherstein","LukewarmWeather","lweather2@gmail.com",QA);
		
		userAccount.create(expected);
		UserAccount actual = userAccount.read(username);
		
		assertEquals(expected,actual);
		userAccount.delete(expected);
	}
	
	@Test
	public void test_createAUserWritesToDatabase(){
		String username = "testUser";
		String firstName = "Test";
		String lastName = "User";
		String password = "password";
		String userEmail = "email@email.com";
		Questions question = new Questions(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET,"SheepFace");
		List<Questions> QA = new ArrayList<>();
		QA.add(question);
		UserAccount testUser = new UserAccount(username,lastName,firstName,password,userEmail,QA);
		userAccount.create(testUser);

		userAccount.delete(testUser);
	}
	
	
	@Test
	public void test_updateWorks_andUpdtesInfoInDatabase(){
		String username = "testUser2";
		String firstName = "Bob";
		String lastName = "User";
		String password = "password";
		String userEmail = "email@email.com";
		Questions question = new Questions(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET,"SheepFace");
		List<Questions> QA = new ArrayList<>();
		QA.add(question);
		UserAccount testUser2 = new UserAccount(username,lastName,firstName,password,userEmail,QA);
		userAccount.create(testUser2);
	
		testUser2.setFirstName("Henry");

		userAccount.update(testUser2);
		userAccount.delete(testUser2);
	}
	
	@Test
	public void test_readMethodReads_WhenPassedAnInteger(){
		String username = "testUser2";
		String firstName = "Bob";
		String lastName = "User";
		String password = "password";
		String userEmail = "email@email.com";
		
		Questions question = new Questions(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET,"SheepFace");
		List<Questions> QA = new ArrayList<>();
		QA.add(question);
		UserAccount testUser2 = new UserAccount(username,lastName,firstName,password,userEmail,QA);
		userAccount.create(testUser2);
		int expectedId = userAccount.getID(username);
		UserAccount expected = testUser2;
		
		UserAccount actual = userAccount.read(expectedId);
		
		assertEquals(expected,actual);
		userAccount.delete(testUser2);
		
	}
	@Test
	public void test_readAllBusinessAccountsByUser(){
		String username = "testUser2";
		String firstName = "Bob";
		String lastName = "User";
		String password = "password";
		String userEmail = "email@email.com";
		Questions question = new Questions(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET,"SheepFace");
		List<Questions> QA = new ArrayList<>();
		QA.add(question);
		UserAccount testUser2 = new UserAccount(username,lastName,firstName,password,userEmail,QA);
		userAccount.create(testUser2);
		
		
		List<UserAccount> userList = new ArrayList<>();
		userList.add(testUser2);
		List<String> files = new ArrayList<>();
		BusinessAccountDao businessDao = (BusinessAccountDao) context.getBean("BusinessAccountDao");
		BusinessAccount account = new BusinessAccount(testUser2,new ServiceLevel(1),userList,files,"TestAccount");
		int bisId = businessDao.getId();
		businessDao.create(account);
		account = businessDao.read(bisId);
		int userId = userAccount.getThisId(testUser2);
		List<BusinessAccount> actualList = userAccount.readAccounts(userId);
		businessDao.delete(account);
		userAccount.delete(testUser2);
		assertEquals(account.getAccountName(),actualList.get(0).getAccountName());
		
		
	}


}



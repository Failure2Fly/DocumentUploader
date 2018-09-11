

package com.fdmgroup.documentuploader;

import static org.junit.Assert.*;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;



public class UserAccountDaoTest {
	private ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
	private UserAccountDao userAccount = (UserAccountDao)context.getBean("UserAccountJdbcTemplate");
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
		//Map<SecurityQuestion,String> QA = new HashMap<>();
		//QA.put(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET, "SheepFace");
		//QA.put(SecurityQuestion.WHAT_WAS_YOUR_MOTHERS_MAIDEN_NAME, "Statistics");
		String username = "LukeWeatherstein";
		
		UserAccount expected = new UserAccount(username,"Luke","Weatherstein","LukewarmWeather","lweather2@gmail.com");
		
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
		
		UserAccount testUser = new UserAccount(username,lastName,firstName,password,userEmail);
		userAccount.create(testUser);
		
		testUser.toString();
		//JdbcUtils.countRowsInTable(userAccount);
		//deleteuser
		userAccount.delete(testUser);
	}
	
	
	@Test
	public void test_updateWorks_andUpdtesInfoInDatabase(){
		String username = "testUser2";
		String firstName = "Bob";
		String lastName = "User";
		String password = "password";
		String userEmail = "email@email.com";
		
		UserAccount testUser2 = new UserAccount(username,lastName,firstName,password,userEmail);
		userAccount.create(testUser2);
		System.out.println(testUser2.getFirstName());
		testUser2.setFirstName("Henry");
		System.out.println(testUser2.getFirstName());
		userAccount.update(testUser2);
		userAccount.delete(testUser2);
	}
	
	@Test
	public void test_readMethodReads_WhenPassedAnInteger(){
		String username = "fakeUser";
		int id = 1000000;
		UserAccount expected = new UserAccount(username,"FakeName","AnotherFake","SuperFake","ImFake@gmail.com");
	
		UserAccount actual = userAccount.read(id);
		
		assertEquals(expected,actual);
		
	}


}

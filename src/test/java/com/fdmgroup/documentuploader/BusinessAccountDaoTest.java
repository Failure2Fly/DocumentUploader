
package com.fdmgroup.documentuploader;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

public class BusinessAccountDaoTest {

	@Test
	public void test_willCheckIfBusinessAccountCreated() {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		BusinessAccountDao businessDao = (BusinessAccountDao)context.getBean("BusinessAccountDao");
		
		
		UserAccountJdbcTemplate userDao = (UserAccountJdbcTemplate)context.getBean("UserAccountJdbcTemplate");
		String username ="ImaginaryUser";
		UserAccount user = new UserAccount(username,"Imaginary","User","password","fake@gmail.com");
		userDao.create(user);
		List<UserAccount> users = new ArrayList<>();
		users.add(user);
		List<String> files= null;
		ServiceLevel level = null;
		String accountName="TestAccount";
		
		BusinessAccount account = new BusinessAccount(user,level,users,files,accountName);
		businessDao.create(account);
		account = businessDao.read(username);
		businessDao.delete(account);
		userDao.delete(user);
		try{

		}catch(EmptyResultDataAccessException e){
			
		}
		
	}
	
	@Test
	public void test_businessAccountUpdateMethodWorks_UpdatesReflectedInDatabase(){
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		UserAccountJdbcTemplate userAccount = (UserAccountJdbcTemplate)context.getBean("UserAccountJdbcTemplate");
		BusinessAccountDao businessDao = (BusinessAccountDao)context.getBean("BusinessAccountDao");
		UserAccount user = new UserAccount("IOwnNow","You","IOwn","youShallNotPass","userEmail");
		userAccount.create(user);
		List<UserAccount> users = new ArrayList<>();
		users.add(user);
		List<String> files= null;
		ServiceLevel level = null;
		String accountName="TestAccount";
		BusinessAccount account = new BusinessAccount(user,level,users,files,accountName);
		businessDao.create(account);
		System.out.println("Before update: " + account.getAccountName());
		account.setAccountName("WhooAccount!");
		businessDao.update(account);
		System.out.println("After update: " + account.getAccountName());
		businessDao.delete(account);
		System.out.println("account deleted");
//		userAccount.delete(user);
//		System.out.println("user deleted");

	}

	@Test
	public void test_businessAccountReadMethodWorks_WhenPassedInAnInteger() {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		BusinessAccountDao businessDao = (BusinessAccountDao)context.getBean("BusinessAccountDao");
		
		
		UserAccountJdbcTemplate userDao = (UserAccountJdbcTemplate)context.getBean("UserAccountJdbcTemplate");
		String username ="FAKE";
		UserAccount user = new UserAccount(username,"FAKE","Fake","password","fake@gmail.com");
		userDao.create(user);
		System.out.println("User created");
		List<UserAccount> users = new ArrayList<>();
		users.add(user);
		System.out.println("User added");
		
		List<String> files= null;
		ServiceLevel level = null;
		String accountName="TestAccount";
		
		BusinessAccount account = new BusinessAccount(user,level,users,files,accountName);
		businessDao.create(account);
		System.out.println("account created");
		System.out.println(account);
		
		int id = 1000000;
		BusinessAccount expected = new BusinessAccount(user,level,users,files,accountName);
		System.out.println("expected account created");
		System.out.println(expected);
		
		//BREAKS HERE
		BusinessAccount actual = businessDao.read(id);
		System.out.println("read account");
		assertEquals(expected,actual);
		
	}
	
}



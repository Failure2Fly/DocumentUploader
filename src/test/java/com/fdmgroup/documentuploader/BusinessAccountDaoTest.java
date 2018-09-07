//package com.fdmgroup.documentuploader;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.dao.EmptyResultDataAccessException;
//
//public class BusinessAccountDaoTest {
//
//	@Test
//	public void test_willCheckIfBusinessAccountCreated() {
//		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
//		BusinessAccountDao businessDao = (BusinessAccountDao)context.getBean("BusinessAccountDao");
//		
//		
//		UserAccountJdbcTemplate userDao = (UserAccountJdbcTemplate)context.getBean("UserAccountJdbcTemplate");
//		String username ="ImaginaryUser";
//		UserAccount user = new UserAccount(username,"Imaginary","User","password","fake@gmail.com");
//		userDao.create(user);
//		List<UserAccount> users = new ArrayList<>();
//		users.add(user);
//		List<String> files= null;
//		ServiceLevel level = null;
//		String accountName="TestAccount";
//		
//		BusinessAccount account = new BusinessAccount(user,level,users,files,accountName);
//		businessDao.create(account);
//		account = businessDao.read(username);
//		//businessDao.delete(account);
//		//userDao.delete(user);
//		try{
//
//		}catch(EmptyResultDataAccessException e){
//			
//		}
//		
//	}
//	
//	@Test
//	public void test_businessAccountUpdateMethodWorks_UpdatesReflectedInDatabase(){
//		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
//		UserAccountJdbcTemplate userAccount = (UserAccountJdbcTemplate)context.getBean("UserAccountJdbcTemplate");
//		BusinessAccount business = new BusinessAccount();
//		BusinessAccountDao businessDao = (BusinessAccountDao)context.getBean("BusinessAccountDao");
//
//		UserAccount newOwner = new UserAccount("IOwnNow","You","IOwn","youShallNotPass","userEmail");
//		System.out.println(business);
//		business.setOwner(null);
//		businessDao.create(business);
//		//business.getOwner();
//		//business.getServicelevel();
//		System.out.println(business);
//		userAccount.create(newOwner);
//		business.setOwner(newOwner);
//		businessDao.update(business);
//		System.out.println(business);
//	}
//
//}
//

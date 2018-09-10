//
//package com.fdmgroup.documentuploader;
//
//import static org.junit.Assert.assertEquals;
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
//		ApplicationContext context = DispatchController.getContext();
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
//		BusinessAccount account2 = new BusinessAccount(user,level,users,files,accountName);
//		businessDao.create(account);
//		businessDao.create(account2);
//		
//		List<BusinessAccount>accounts = businessDao.read(username);
//		for(BusinessAccount acc:accounts){
//			account=acc;
//			businessDao.delete(account);
//		}
//		
//		userDao.delete(user);
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
//		ApplicationContext context = DispatchController.getContext();
//		UserAccountJdbcTemplate userAccount = (UserAccountJdbcTemplate)context.getBean("UserAccountJdbcTemplate");
//		BusinessAccountDao businessDao = (BusinessAccountDao)context.getBean("BusinessAccountDao");
//		UserAccount user = new UserAccount("IOwnNow","You","IOwn","youShallNotPass","userEmail");
//		
//		List<UserAccount> users = new ArrayList<>();
//		users.add(user);
//		userAccount.create(user);
//		List<String> files= null;
//		ServiceLevel level = null;
//		String accountName="TestAccount";
//		BusinessAccount account = new BusinessAccount(user,level,users,files,accountName);
//		businessDao.create(account);
//
//		account.setAccountName("WhooAccount!");
//		businessDao.update(account);
//		List<BusinessAccount> accounts=businessDao.read(user.getUsername());
//		for(BusinessAccount acc:accounts){
//			account=acc;
//		}
//
//		businessDao.delete(account);
//
//		userAccount.delete(user);
//
//
//	}
//	
//
//	@Test
//	public void test_businessAccountReadMethodWorks_WhenPassedInAnInteger() {
//		ApplicationContext context = DispatchController.getContext();
//		UserAccountJdbcTemplate userAccount = (UserAccountJdbcTemplate)context.getBean("UserAccountJdbcTemplate");
//		BusinessAccountDao businessDao = (BusinessAccountDao)context.getBean("BusinessAccountDao");
//		UserAccount user = new UserAccount("IOwnNow","You","IOwn","youShallNotPass","userEmail");
//		
//		List<UserAccount> users = new ArrayList<>();
//		users.add(user);
//		userAccount.create(user);
//		List<String> files= null;
//		ServiceLevel level = null;
//		String accountName="TestAccount";
//		
//		BusinessAccount account = new BusinessAccount(user,level,users,files,accountName);
//		businessDao.create(account);
//		
//		List<BusinessAccount> accounts=businessDao.read(user.getUsername());
//		for(BusinessAccount acc:accounts){
//			account=acc;
//		}
//		
//		businessDao.read(new Integer(account.getBusinessAccountId()));
//		
//		businessDao.delete(account);
//		
//		userAccount.delete(user);
//	}
//	
<<<<<<< HEAD

	

	
}


=======
//	@Test
//	public void justHumorMe(){
//		ApplicationContext context = DispatchController.getContext();
//		BusinessAccountDao businessDao = (BusinessAccountDao)context.getBean("BusinessAccountDao");
//		System.out.println(businessDao.read(1));
//		System.out.println(businessDao.read(2));
//		System.out.println(businessDao.read(3));
//		System.out.println(businessDao.read(4));
//		System.out.println(businessDao.read(5));
//		System.out.println(businessDao.read(6));
//
//	}
//	
//
//	
//}
//
//
>>>>>>> 625bb1c6be2981c3619f53f1a10e9800ae00a02d

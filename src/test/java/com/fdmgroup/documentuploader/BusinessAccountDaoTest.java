package com.fdmgroup.documentuploader;

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
		
		BusinessAccount account = new BusinessAccount(user,level,users,files);
		businessDao.create(account);
		account = businessDao.read(username);
		businessDao.delete(account);
		userDao.delete(user);
		try{

		}catch(EmptyResultDataAccessException e){
			
		}
		
	}

}


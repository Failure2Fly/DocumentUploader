package com.fdmgroup.documentuploader;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserAccountDAO {

	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		UserAccountJdbcTemplate userAccount = (UserAccountJdbcTemplate)context.getBean("UserAccountJdbcTemplate");;
		Map<SecurityQuestion,String> QA = new HashMap<>();
		QA.put(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET, "Bad dog");
		QA.put(SecurityQuestion.WHAT_WAS_YOUR_MOTHERS_MAIDEN_NAME, "Wagner");
		String username = "fredtodd";
		UserAccount expected = new UserAccount(username,"Todd","Fred","FredsCool","Fred@gmail.com",QA);
		UserAccount actual =  userAccount.read(username);
		
		assertEquals(expected,actual);
		
	}

}

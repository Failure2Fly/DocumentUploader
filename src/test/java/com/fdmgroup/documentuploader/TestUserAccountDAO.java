package com.fdmgroup.documentuploader;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserAccountDAO {
	
	@Test
	public void testCreateThenReadThenDelete(){
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		UserAccountJdbcTemplate userAccount = (UserAccountJdbcTemplate)context.getBean("UserAccountJdbcTemplate");
		Map<SecurityQuestion,String> QA = new HashMap<>();
		QA.put(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET, "SheepFace");
		QA.put(SecurityQuestion.WHAT_WAS_YOUR_MOTHERS_MAIDEN_NAME, "Statistics");
		String username = "FlySwat";
		
		UserAccount expected = new UserAccount(username,"Luke","Weatherstein","LukewarmWeather","lweather2@gmail.com",QA);
		UserAccount assertExpected = new UserAccount(username,"Luke","Weatherstein","LukewarmWeather","lweather2@gmail.com");
		//userAccount.delete(expected);
		userAccount.create(expected);
		UserAccount actual = userAccount.read(username);
		
		assertEquals(assertExpected,actual);
		userAccount.delete(expected);
	}
}

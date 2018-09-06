package com.fdmgroup.documentuploader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Validator {

	public void validateUserLogin(String username, String password) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		UserAccountJdbcTemplate jdbc = (UserAccountJdbcTemplate)context.getBean("UserAccountJdbcTemplate");
		UserAccount actualUser = jdbc.read(username);
		
		
	}

}

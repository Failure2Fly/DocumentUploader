package com.fdmgroup.documentuploader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

public class Validator {

	public boolean validateUserLogin(String username, String password) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		UserAccountJdbcTemplate jdbc = (UserAccountJdbcTemplate)context.getBean("UserAccountJdbcTemplate");
		try{
		UserAccount actualUser = jdbc.read(username);
		if(actualUser.getPassword().equals(password)){
			return true;
		}else{
			return false;
		}
		}catch(EmptyResultDataAccessException e){
			return false;
		}
		
		
		
	}

}

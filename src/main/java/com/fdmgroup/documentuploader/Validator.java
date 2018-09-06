package com.fdmgroup.documentuploader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

public class Validator {

	public boolean validateUserLogin(String username, String password) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		UserAccountJdbcTemplate jdbc = (UserAccountJdbcTemplate) context.getBean("UserAccountJdbcTemplate");
		try {
			UserAccount actualUser = jdbc.read(username);
			if (actualUser.getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}
		} catch (EmptyResultDataAccessException e) {
			return false;
		} catch (IncorrectResultSizeDataAccessException e) {
			return false;
		}catch (NullPointerException e){
			return false;
		}

	}

	public boolean validateUserRegistration(UserAccount userAccount) {
		// TODO add real validation here
		return true;
	}

}

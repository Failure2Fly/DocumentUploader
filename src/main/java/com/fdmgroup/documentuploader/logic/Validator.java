package com.fdmgroup.documentuploader.logic;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.fdmgroup.documentuploader.dao.UserAccountDao;
import com.fdmgroup.documentuploader.pojo.UserAccount;

public class Validator {

	private ApplicationContext context;
	private UserAccountDao dao;

	public boolean validateUserLogin(String username, String password) {
		context = new ClassPathXmlApplicationContext("context.xml");
		dao = (UserAccountDao) context.getBean("UserAccountDao");
		try {
			UserAccount actualUser = dao.read(username);
			if (actualUser.getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}
		} catch (EmptyResultDataAccessException e) {
			return false;
		} catch (IncorrectResultSizeDataAccessException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}
	
	public boolean validatePasswordConfirmation(String password,String confirmPassword){
		if(password.equals(confirmPassword)){
			return true;
		}
		return false;
	}
	
	public boolean validateUserRegistration(UserAccount userAccount) {
		boolean usernameValid = false;
		boolean passwordValid = false;
		boolean firstNameValid = false;
		boolean lastNameValid = false;
		boolean emailValid = false;

		try {
			if (userAccount != null) {
				if (userAccount.getUsername() != null) {
					if (!userAccount.getUsername().isEmpty() && userAccount.getUsername().length() <= 20) {
						usernameValid = true;
					}
				}
				if (userAccount.getPassword() != null) {
					if (!userAccount.getPassword().isEmpty() && userAccount.getPassword().length() >= 8) {
						passwordValid = true;
					}
				}
				if (userAccount.getFirstName() != null) {
					if (!userAccount.getFirstName().isEmpty() && userAccount.getFirstName().length() <= 20) {
						firstNameValid = true;
					}
				}
				if (userAccount.getLastName() != null) {
					if (!userAccount.getLastName().isEmpty() && userAccount.getLastName().length() <= 20) {
						lastNameValid = true;
					}
				}
				if (userAccount.getUserEmail() != null) {
					if (!userAccount.getUserEmail().isEmpty() && emailValidator(userAccount.getUserEmail())) {
						emailValid = true;
					}
				}

				if (usernameValid && passwordValid && firstNameValid && lastNameValid && emailValid) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean emailValidator(String email) {
		boolean isValid = false;

		try {
			InternetAddress emailAddress = new InternetAddress(email);
			emailAddress.validate();
			isValid = true;
		} catch (AddressException e) {

		}
		return isValid;
	}
	

}

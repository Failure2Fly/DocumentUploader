package com.fdmgroup.documentuploader.logic;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.fdmgroup.documentuploader.controller.DispatchController;
import com.fdmgroup.documentuploader.dao.UserAccountDao;
import com.fdmgroup.documentuploader.pojo.UserAccount;

public class Validator {

	private ApplicationContext context;
	private UserAccountDao dao;

	public String validateUserLogin(String username, String password) {
		context = new ClassPathXmlApplicationContext("context.xml");
		dao = (UserAccountDao) context.getBean("UserAccountDao");
		try {
			UserAccount actualUser = dao.read(username);
			if (actualUser.getPassword().equals(password)) {
				return "";
			} else {
				return "Password does not match our records!";
			}
		}  catch (IncorrectResultSizeDataAccessException e) {
			return "Multiple user with that username exist??";
		} catch (NullPointerException e) {
			return "Username does not exist!";
		}
	}
	
	public boolean validatePasswordConfirmation(String password,String confirmPassword){
		if(password.equals(confirmPassword)){
			return true;
		}
		return false;
	}
	
	public String[] validateUserRegistration(UserAccount userAccount) {
		boolean isValid = true;
		String usernameValid = "";
		String passwordValid = "";
		String firstNameValid = "";
		String lastNameValid = "";
		String emailValid = "";

		try {
			if (userAccount != null) {
				if (userAccount.getUsername() != null) {
					UserAccountDao userDao = (UserAccountDao) DispatchController.getContext().getBean("UserAccountDao");
					
					if(userDao.getThisId(userAccount)!=0){
						usernameValid = "Username is already taken!";
						isValid=false;
					}else if (!userAccount.getUsername().isEmpty() && userAccount.getUsername().length() <= 20) {
						usernameValid = "";
					}else{
						usernameValid = "Username too long!";
						isValid=false;
					}
				}
				if (userAccount.getPassword() != null) {
					if (!userAccount.getPassword().isEmpty() && userAccount.getPassword().length() >= 8) {
						passwordValid = "";
					}else{
						passwordValid = "Password must be at least 8 characters.";
						isValid=false;
					}
				}
				if (userAccount.getFirstName() != null) {
					if (!userAccount.getFirstName().isEmpty() && userAccount.getFirstName().length() <= 20) {
						firstNameValid = "";
					}else{
						firstNameValid = "First name too long!";
						isValid=false;
					}
				}
				if (userAccount.getLastName() != null) {
					if (!userAccount.getLastName().isEmpty() && userAccount.getLastName().length() <= 20) {
						lastNameValid = "";
					}else{
						lastNameValid = "Last name too long!";
						isValid=false;
					}
				}
				if (userAccount.getUserEmail() != null) {
					if (!userAccount.getUserEmail().isEmpty() && emailValidator(userAccount.getUserEmail())) {
						emailValid = "";
					}else{
						emailValid = "Email invalid.";
						isValid=false;
					}
				}

				if (isValid) {
					return null;
				} else {
					String[] errors = { usernameValid, passwordValid , firstNameValid , lastNameValid , emailValid};
					return errors;
				}
			} else {
				return null;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
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

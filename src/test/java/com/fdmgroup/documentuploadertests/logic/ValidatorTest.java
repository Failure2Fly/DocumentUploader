
package com.fdmgroup.documentuploadertests.logic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fdmgroup.documentuploader.controller.DispatchController;
import com.fdmgroup.documentuploader.dao.UserAccountDao;
import com.fdmgroup.documentuploader.enumeratedtypes.SecurityQuestion;
import com.fdmgroup.documentuploader.logic.Validator;
import com.fdmgroup.documentuploader.pojo.Questions;
import com.fdmgroup.documentuploader.pojo.UserAccount;

public class ValidatorTest {

	Validator validator;
	ApplicationContext context;
	UserAccount userAccount;
	Questions question;
	List<Questions> QA;
	String username;
	String password;
	@Before
	public void setUp() throws Exception {
		validator = new Validator();
		question = new Questions(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET,"SheepFace");
		QA = new ArrayList<>();
		QA.add(question);
		username = "Luke.Weatherstein";
		password = "LukeIsDaBomb##!";

		userAccount = new UserAccount("Luke.Weatherstein", "Weatherstein", "Luke", "LukewarmWeather",
				"lweather2@gmail.com",QA);
	}

	// ====== Begin validateUserLogin Tests ======
	
	@Test
	public void test_validateUserLogin_returnsTrue_whenGivenAValidUsernameAndPassword() {
		// Arrange
		context = DispatchController.getContext();
		UserAccountDao dao = (UserAccountDao) context.getBean("UserAccountDao");
		userAccount = new UserAccount("Luke.Weatherstein", "Weatherstein", "Luke", password,
				"lweather2@gmail.com",QA);

		dao.create(userAccount);

		// Act
		String result = validator.validateUserLogin(username, password);
		dao.delete(userAccount);
		// Assert
		assertTrue(result.length()<2);

	}

	@Test
	public void test_validateUserLogin_returnsFalse_whenGivenAnInvalidUsername() {
		// Arrange
		context = DispatchController.getContext();
		UserAccountDao dao = (UserAccountDao) context.getBean("UserAccountDao");
		userAccount = new UserAccount("Luke.Weatherstein", "Weatherstein", "Luke", "LukewarmWeather",
				"lweather2@gmail.com",QA);

		

		// Act
		String result = validator.validateUserLogin("", password);
		
		// Assert
		assertTrue(result.length()>1);
	}

	@Test
	public void test_validateUserLogin_returnsFalse_whenGivenAnInvalidPassword() {
		// Arrange
		context = DispatchController.getContext();
		UserAccountDao dao = (UserAccountDao) context.getBean("UserAccountDao");

		userAccount = new UserAccount("Luke.Weatherstein", "Weatherstein", "Luke", "LukewarmWeather",
				"lweather2@gmail.com",QA);
		
		dao.create(userAccount);
		

		// Act
		String result = validator.validateUserLogin(username, "");
		dao.delete(userAccount);
		// Assert
		assertTrue(result.length()>1);
	}

	@Test
	public void test_validateUserLogin_returnsErrorMessage_whenGivenAnInvalidUsernameAndPassword() {
		// Arrange

		userAccount = new UserAccount("Luke.Weatherstein", "Weatherstein", "Luke", "LukewarmWeather",
				"lweather2@gmail.com",QA);



		// Act
		String result = validator.validateUserLogin(username, password);

		// Assert
		assertTrue(result.length()>1);
	}

	@Test
	public void test_validateUserLogin_returnsErrorMessage_whenPassedANullUseranme() {
		// Arrange


		userAccount = new UserAccount("Luke.Weatherstein", "Weatherstein", "Luke", "LukewarmWeather",
				"lweather2@gmail.com",QA);


		// Act
		String result = validator.validateUserLogin(null, password);
		// Assert
		assertTrue(result.length()>1);
	}
	
	// ====== Begin validatePasswordConfirmation Tests ======
	
	@Test
	public void test_validatePasswordConfirmation_returnsTrue_whenPassedMatchingPasswords() {
		// Arrange
		String passwordOne = "password1234!";
		String passwordTwo = "password1234!";
		
		// Act
		boolean result = validator.validatePasswordConfirmation(passwordOne, passwordTwo);
		
		// Assert
		assertTrue(result);
	}
	
	@Test
	public void test_validatePasswordConfirmation_returnsFalse_whenPassedMismatchedPasword() {
		String passwordOne = "password1234!";
		String passwordTwo = "Passwrod1234!";
		
		// Act
		boolean result = validator.validatePasswordConfirmation(passwordOne, passwordTwo);
		
		// Assert
		assertFalse(result);
	}

	// ====== Begin validateUserRegistration Tests ======

	@Test
	public void test_validateUserRegistration_returnsTrue_whenGivenAUserAccountWithValidInformation() {
		// Arrange
		userAccount = new UserAccount("ScottyDoesn'tKnow", "Boutin", "Scotty", "FionaIsDaBest42!#$",
				"ScottyRox2018@aol.com");

		// Act
		String[] result = validator.validateUserRegistration(userAccount);

		// Assert
		assertTrue(Objects.isNull(result));
	}

	@Test
	public void test_validateUserRegistration_returnsFalse_whenGivenAUserAccountWithAnEmptyUsername() {
		// Arrange
		userAccount = new UserAccount("", "Boutin", "Scotty", "FionaIsDaBest42!#$", "ScottyRox2018@aol.com");

		// Act
		String[] result = validator.validateUserRegistration(userAccount);

		// Assert
		assertTrue(result[0].length()>1);
	}

	@Test
	public void test_validateUserRegistration_returnsFalse_whenGivenAUserAccountWithAnEmptyLastName() {
		// Arrange
		userAccount = new UserAccount("ScottyDoesn'tKnow", "", "Scotty", "FionaIsDaBest42!#$", "ScottyRox2018@aol.com");

		// Act
		String[] result = validator.validateUserRegistration(userAccount);

		// Assert
		assertTrue(result[3].length()>1);
	}

	@Test
	public void test_validateUserRegistration_returnsFalse_whenGivenAUserAccountWithAnEmptyFirstName() {
		// Arrange
		userAccount = new UserAccount("ScottyDoesn'tKnow", "Boutin", "", "FionaIsDaBest42!#$", "ScottyRox2018@aol.com");

		// Act
		String[] result = validator.validateUserRegistration(userAccount);

		// Assert
		assertTrue(result[2].length()>1);
	}

	@Test
	public void test_validateUserRegistration_returnsFalse_whenGivenAUserAccountWithAnEmptyPassword() {
		// Arrange
		userAccount = new UserAccount("ScottyDoesn'tKnow", "Boutin", "Scotty", "", "ScottyRox2018@aol.com");

		// Act
		String[] result = validator.validateUserRegistration(userAccount);

		// Assert
		assertTrue(result[1].length()>1);
	}

	@Test
	public void test_validateUserRegistration_returnsFalse_whenGivenAUserAccountWithAnEmptyEmailAddress() {
		// Arrange
		userAccount = new UserAccount("ScottyDoesn'tKnow", "Boutin", "Scotty", "FionaIsDaBest42!#$", "");

		// Act
		String[] result = validator.validateUserRegistration(userAccount);

		// Assert
		assertFalse(Objects.isNull(result));
	}

	@Test
	public void test_validateUserRegistration_returnsFalse_whenGivenAnEmptyUserAccount() {
		// Arrange
		userAccount = new UserAccount();

		// Act
		String[] result = validator.validateUserRegistration(userAccount);

		// Assert
		assertTrue(Objects.isNull(result));
	}

}


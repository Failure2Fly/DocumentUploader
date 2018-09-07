package com.fdmgroup.documentuploader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ValidatorTest {

	Validator validator;
	ApplicationContext context;
	UserAccount userAccount;

	@Before
	public void setUp() throws Exception {
		validator = new Validator();
	}

	//@Test
	public void test_validateUserLogin_returnsTrue_whenGivenAValidUsernameAndPassword() {
		// Arrange
		context = new ClassPathXmlApplicationContext("context.xml");
		UserAccountJdbcTemplate dao = (UserAccountJdbcTemplate) context.getBean("UserAccountJdbcTemplate");
		// Map<SecurityQuestion,String> QA = new HashMap<>();
		// QA.put(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET,
		// "SheepFace");
		// QA.put(SecurityQuestion.WHAT_WAS_YOUR_MOTHERS_MAIDEN_NAME,
		// "Statistics");
		String username = "Luke.Weatherstein";
		String password = "LukewarmWeather";

		userAccount = new UserAccount("Luke.Weatherstein", "Weatherstein", "Luke", "LukewarmWeather",
				"lweather2@gmail.com");
		dao.delete(userAccount);
		dao.create(userAccount);

		// Act
		boolean result = validator.validateUserLogin(username, password);

		// Assert
		assertTrue(result);

		dao.delete(userAccount);
	}

	// ====== Begin validateUserLogin Tests =====

	//@Test
	public void test_validateUserLogin_returnsFalse_whenGivenAnInvalidUsername() {
		// Arrange
		context = new ClassPathXmlApplicationContext("context.xml");
		UserAccountJdbcTemplate dao = (UserAccountJdbcTemplate) context.getBean("UserAccountJdbcTemplate");
		// Map<SecurityQuestion,String> QA = new HashMap<>();
		// QA.put(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET,
		// "SheepFace");
		// QA.put(SecurityQuestion.WHAT_WAS_YOUR_MOTHERS_MAIDEN_NAME,
		// "Statistics");
		String username = "Luke.Cage";
		String password = "LukewarmWeather";

		userAccount = new UserAccount("Luke.Weatherstein", "Weatherstein", "Luke", "LukewarmWeather",
				"lweather2@gmail.com");
		dao.delete(userAccount);
		dao.create(userAccount);

		// Act
		boolean result = validator.validateUserLogin(username, password);

		// Assert
		assertFalse(result);

		dao.delete(userAccount);
	}

	//@Test
	public void test_validateUserLogin_returnsFalse_whenGivenAnInvalidPassword() {
		// Arrange
		context = new ClassPathXmlApplicationContext("context.xml");
		UserAccountJdbcTemplate dao = (UserAccountJdbcTemplate) context.getBean("UserAccountJdbcTemplate");
		// Map<SecurityQuestion,String> QA = new HashMap<>();
		// QA.put(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET,
		// "SheepFace");
		// QA.put(SecurityQuestion.WHAT_WAS_YOUR_MOTHERS_MAIDEN_NAME,
		// "Statistics");
		String username = "Luke.Weatherstein";
		String password = "LukeIsDaBomb##!";

		userAccount = new UserAccount("Luke.Weatherstein", "Weatherstein", "Luke", "LukewarmWeather",
				"lweather2@gmail.com");
		dao.delete(userAccount);
		dao.create(userAccount);

		// Act
		boolean result = validator.validateUserLogin(username, password);

		// Assert
		assertFalse(result);

		dao.delete(userAccount);
	}

	//@Test
	public void test_validateUserLogin_returnsFalse_whenGivenAnInvalidUsernameAndPassword() {
		// Arrange
		context = new ClassPathXmlApplicationContext("context.xml");
		UserAccountJdbcTemplate dao = (UserAccountJdbcTemplate) context.getBean("UserAccountJdbcTemplate");
		// Map<SecurityQuestion,String> QA = new HashMap<>();
		// QA.put(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET,
		// "SheepFace");
		// QA.put(SecurityQuestion.WHAT_WAS_YOUR_MOTHERS_MAIDEN_NAME,
		// "Statistics");
		String username = "Luke.Cake";
		String password = "LukeIsDaBomb##!";

		userAccount = new UserAccount("Luke.Weatherstein", "Weatherstein", "Luke", "LukewarmWeather",
				"lweather2@gmail.com");
		dao.delete(userAccount);
		dao.create(userAccount);

		// Act
		boolean result = validator.validateUserLogin(username, password);

		// Assert
		assertFalse(result);

		dao.delete(userAccount);
	}

	//@Test
	public void test_validateUserLogin_reutrnsFalse_whenPassedANullUseranme() {
		// Arrange
		context = new ClassPathXmlApplicationContext("context.xml");
		UserAccountJdbcTemplate dao = (UserAccountJdbcTemplate) context.getBean("UserAccountJdbcTemplate");
		// Map<SecurityQuestion,String> QA = new HashMap<>();
		// QA.put(SecurityQuestion.WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET,
		// "SheepFace");
		// QA.put(SecurityQuestion.WHAT_WAS_YOUR_MOTHERS_MAIDEN_NAME,
		// "Statistics");
		String username = null;
		String password = "LukeIsDaBomb##!";

		userAccount = new UserAccount("Luke.Weatherstein", "Weatherstein", "Luke", "LukewarmWeather",
				"lweather2@gmail.com");
		dao.delete(userAccount);
		dao.create(userAccount);

		// Act
		boolean result = validator.validateUserLogin(username, password);

		// Assert
		assertFalse(result);

		dao.delete(userAccount);
	}
	

	// ====== Begin validateUserRegistration Tests =====

	@Test
	public void test_validateUserRegistration_returnsTrue_whenGivenAUserAccountWithValidInformation() {
		// Arrange
		userAccount = new UserAccount("ScottyDoesn'tKnow", "Boutin", "Scotty", "FionaIsDaBest42!#$",
				"ScottyRox2018@aol.com");

		// Act
		boolean result = validator.validateUserRegistration(userAccount);

		// Assert
		assertTrue(result);
	}

	@Test
	public void test_validateUserRegistration_returnsFalse_whenGivenAUserAccountWithAnEmptyUsername() {
		// Arrange
		userAccount = new UserAccount("", "Boutin", "Scotty", "FionaIsDaBest42!#$", "ScottyRox2018@aol.com");

		// Act
		boolean result = validator.validateUserRegistration(userAccount);

		// Assert
		assertFalse(result);
	}

	@Test
	public void test_validateUserRegistration_returnsFalse_whenGivenAUserAccountWithAnEmptyLastName() {
		// Arrange
		userAccount = new UserAccount("ScottyDoesn'tKnow", "", "Scotty", "FionaIsDaBest42!#$", "ScottyRox2018@aol.com");

		// Act
		boolean result = validator.validateUserRegistration(userAccount);

		// Assert
		assertFalse(result);
	}

	@Test
	public void test_validateUserRegistration_returnsFalse_whenGivenAUserAccountWithAnEmptyFirstName() {
		// Arrange
		userAccount = new UserAccount("ScottyDoesn'tKnow", "Boutin", "", "FionaIsDaBest42!#$", "ScottyRox2018@aol.com");

		// Act
		boolean result = validator.validateUserRegistration(userAccount);

		// Assert
		assertFalse(result);
	}

	@Test
	public void test_validateUserRegistration_returnsFalse_whenGivenAUserAccountWithAnEmptyPassword() {
		// Arrange
		userAccount = new UserAccount("ScottyDoesn'tKnow", "Boutin", "Scotty", "", "ScottyRox2018@aol.com");

		// Act
		boolean result = validator.validateUserRegistration(userAccount);

		// Assert
		assertFalse(result);
	}

	@Test
	public void test_validateUserRegistration_returnsFalse_whenGivenAUserAccountWithAnEmptyEmailAddress() {
		// Arrange
		userAccount = new UserAccount("ScottyDoesn'tKnow", "Boutin", "Scotty", "FionaIsDaBest42!#$", "");

		// Act
		boolean result = validator.validateUserRegistration(userAccount);

		// Assert
		assertFalse(result);
	}

	@Test
	public void test_validateUserRegistration_returnsFalse_whenGivenAnEmptyUserAccount() {
		// Arrange
		userAccount = new UserAccount();

		// Act
		boolean result = validator.validateUserRegistration(userAccount);

		// Assert
		assertFalse(result);
	}

}

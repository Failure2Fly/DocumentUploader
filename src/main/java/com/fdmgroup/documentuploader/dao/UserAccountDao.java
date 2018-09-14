package com.fdmgroup.documentuploader.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fdmgroup.documentuploader.controller.DispatchController;
import com.fdmgroup.documentuploader.enumeratedtypes.SecurityQuestion;
import com.fdmgroup.documentuploader.pojo.BusinessAccount;
import com.fdmgroup.documentuploader.pojo.Questions;
import com.fdmgroup.documentuploader.pojo.UserAccount;
import com.fdmgroup.documentuploader.rowmapper.UserAccountMapper;

@Repository
public class UserAccountDao implements Dao<UserAccount, String> {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(UserAccount user) {
		String SQL1 = "INSERT INTO USER_ACCOUNT (user_id,username,last_name,first_name,user_password,user_email) VALUES(?,?,?,?,?,?)";
		String SQL2 = "INSERT INTO USER_TO_SECURITY_QUESTION VALUES(?,?,?)";
		jdbcTemplateObject.update(SQL1, lastID(), user.getUsername(), user.getLastName(), user.getFirstName(),
				user.getPassword(), user.getUserEmail());
		jdbcTemplateObject.update(SQL2, getThisId(user), user.getListQA().get(0).getQuestion().ordinal() + 1,
				user.getListQA().get(0).getAnswer());
	}

	@Override
	public void delete(UserAccount user) {
		// TODO sql query deletion all useraccount's stuff
		String SQL1 = "DELETE FROM USER_TO_SECURITY_QUESTION WHERE USER_SECURITY_JOIN_ID = ?";
		jdbcTemplateObject.update(SQL1, getID(user.getUsername()));
		SQL1 = "DELETE FROM USER_ACCOUNT WHERE username = ?";
		//String SQL3 = "DELETE FROM BUSINESSACCOUNT WHERE USERACCOUNTOWNERID =
		jdbcTemplateObject.update(SQL1, user.getUsername());
		SQL1 = "DELETE FROM USER_TO_SECURITY_QUESTION WHERE USER_SECURITY_JOIN_ID=?";
		jdbcTemplateObject.update(SQL1, getID(user.getUsername()));

	}

	@Override
	public void update(UserAccount user) {
		// TODO sql query update username
		String SQL = "UPDATE user_account SET first_name=?,last_name=?,user_password=?,user_email=? WHERE username=?";
		jdbcTemplateObject.update(SQL, user.getFirstName(), user.getLastName(), user.getPassword(), user.getUserEmail(),
				user.getUsername());
		if(user.getListQA()!=null){
			SQL = "UPDATE user_to_security_question SET question_answer=? WHERE security_question_join_id=? AND user_security_join_id=?";
			jdbcTemplateObject.update(SQL, user.getListQA().get(0).getAnswer(),user.getListQA().get(0).getQuestion().ordinal()+1,getID(user.getUsername()));
		}
	}

	@Override
	public UserAccount read(String username) {
		try {
			String SQL = "SELECT username, user_password, user_email, first_name, last_name FROM USER_ACCOUNT WHERE username = ?";
			UserAccount user = jdbcTemplateObject.queryForObject(SQL, new Object[] { username },
					new UserAccountMapper());
			SQL = "SELECT security_question_join_id, question_answer FROM USER_TO_SECURITY_QUESTION WHERE user_security_join_id = ?";
			Questions question = new Questions();
			List<Map<String, Object>> rows = new ArrayList<>();
			rows = jdbcTemplateObject.queryForList(SQL, getID(username));
			for (Map<String, Object> map : rows) {
				question.setAnswer((String) map.get("question_answer"));
				BigDecimal questionId = (BigDecimal) map.get("security_question_join_id");
				SQL = "SELECT question FROM security_question WHERE question_id=?";
				String stringQuestion = (String) jdbcTemplateObject.queryForObject(SQL,
						new Object[] { questionId.intValue() }, String.class);
				question.setQuestion(SecurityQuestion.valueOf(stringQuestion.toUpperCase().replace(" ", "_").replace("?", "")));
			}
			List<Questions> list = new ArrayList<>();
			list.add(question);
			user.setListQA(list);
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public int lastID() {
		String SQL = "SELECT MAX(USER_ID) FROM USER_ACCOUNT";
		try {
			return ((Integer) jdbcTemplateObject.queryForObject(SQL, Integer.class)) + 1;
		} catch (NullPointerException e) {
			return 1;
		}
	}

	public int getID(String username) {
		String SQL = "SELECT USER_ID FROM USER_ACCOUNT where username = ?";
		return (int) jdbcTemplateObject.queryForObject(SQL, Integer.class, username);
	}

	public UserAccount read(Integer id) {
		String SQL = "SELECT username, user_password, user_email, first_name, last_name FROM USER_ACCOUNT WHERE user_id = ?";
		UserAccount user = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new UserAccountMapper());
		return user;
	}

	public List<BusinessAccount> readAccounts(Integer userId) {
		String SQL = "SELECT BUSINESS_USER_JOIN_ID FROM BUSINESS_TO_USER WHERE USER_BUSINESS_JOIN_ID = ?";
		List<BusinessAccount> businessAccounts = new ArrayList<>();
		List<Map<String, Object>> rows = new ArrayList<>();
		rows = jdbcTemplateObject.queryForList(SQL, userId);
		for (Map<String, Object> map : rows) {
			BusinessAccount account = new BusinessAccount();
			BusinessAccountDao businessDao = (BusinessAccountDao) DispatchController.getContext()
					.getBean("BusinessAccountDao");
			BigDecimal temp = (BigDecimal) map.get("BUSINESS_USER_JOIN_ID");

			account = businessDao.read(temp.intValue());
			businessAccounts.add(account);
		}
		return businessAccounts;
	}

	public int getThisId(UserAccount user) {
		String SQL = "SELECT user_id FROM USER_ACCOUNT WHERE username = ?";
		Integer userId = jdbcTemplateObject.queryForObject(SQL, new Object[] { user.getUsername() }, Integer.class);
		return userId;

	}
}

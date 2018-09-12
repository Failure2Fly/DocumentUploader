package com.fdmgroup.documentuploader.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fdmgroup.documentuploader.controller.DispatchController;
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
	public void create(UserAccount item) {
		String SQL1 = "INSERT INTO USERACCOUNT (userid,username,lastname,firstname,userpassword,useremail) VALUES(?,?,?,?,?,?)";
		// String SQL2="INSERT INTO USERACCOUNTTOSECURITYQUESTION
		// VALUES(?,?,?)";
		// String key =
		// item.getListQA().get(0).getQuestion().name().toLowerCase().replace("_",
		// " ")+"?";
		// key = key.substring(0, 1).toUpperCase() + key.substring(1);
		// File file = new File("H:\\DebugInCreate.txt");
		// try {
		// FileWriter writer= new FileWriter(file);
		// writer.write(item.toString());
		// writer.flush();
		// writer.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		jdbcTemplateObject.update(SQL1, lastID(), item.getUsername(), item.getLastName(), item.getFirstName(),
				item.getPassword(), item.getUserEmail());
		// jdbcTemplateObject.update(SQL2,lastID(),item.getListQA().get(0).getQuestion().ordinal()+1,item.getListQA().get(0).getAnswer());
	}

	@Override
	public void delete(UserAccount item) {
		// TODO sql query deletion all useraccount's stuff
		String SQL1 = "DELETE FROM USERACCOUNTTOSECURITYQUESTION WHERE USERACCOUNTSECURITYJOINID = ?";
		String SQL2 = "DELETE FROM USERACCOUNT WHERE username = ?";
		// String SQL3 = "DELETE FROM BUSINESSACCOUNT WHERE USERACCOUNTOWNERID =
		// ?";
		jdbcTemplateObject.update(SQL1, getID(item.getUsername()));
		jdbcTemplateObject.update(SQL2, item.getUsername());
	}

	@Override
	public void update(UserAccount item) {
		// TODO sql query update username
		String SQL = "UPDATE useraccount SET firstname=?,lastname=?,userpassword=?,useremail=? WHERE username=?";
		jdbcTemplateObject.update(SQL, item.getFirstName(), item.getLastName(), item.getPassword(), item.getUserEmail(),
				item.getUsername());
	}

	@Override
	public UserAccount read(String username) {
		String SQL = "SELECT username, userpassword, useremail, firstname, lastname FROM USERACCOUNT WHERE username = ?";
		UserAccount user = jdbcTemplateObject.queryForObject(SQL, new Object[] { username }, new UserAccountMapper());
		return user;
	}

	public int lastID() {
		String SQL = "SELECT MAX(USERID) FROM USERACCOUNT";
		try {

			return ((Integer) jdbcTemplateObject.queryForObject(SQL, Integer.class)) + 1;

		} catch (NullPointerException e) {
			return 1;
		}
	}

	public int getID(String username) {
		String SQL = "SELECT USERID FROM USERACCOUNT where username = ?";
		return (int) jdbcTemplateObject.queryForObject(SQL, Integer.class, username);
	}

	public UserAccount read(Integer id) {
		String SQL = "SELECT username, userpassword, useremail, firstname, lastname FROM USERACCOUNT WHERE userid = ?";

		UserAccount user = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new UserAccountMapper());
		return user;
	}

	public List<BusinessAccount> readAccounts(Integer userId) {
		String SQL = "SELECT BUSINESSACCOUNTUSERJOINID FROM BUSINESSACCOUNTTOUSERACCOUNT WHERE USERACCOUNTBUSINESSJOINID = ?";
		List<BusinessAccount> businessAccounts = new ArrayList<>();
		List<Map<String, Object>> rows = new ArrayList<>();
		rows = jdbcTemplateObject.queryForList(SQL, userId);
		for (Map<String, Object> map : rows) {
			BusinessAccount account = new BusinessAccount();
			BusinessAccountDao businessDao = (BusinessAccountDao) DispatchController.getContext().getBean("BusinessAccountDao");
			BigDecimal temp =(BigDecimal) map.get("BUSINESSACCOUNTUSERJOINID");
			
			account = businessDao.read(temp.intValue());
			businessAccounts.add(account);
		}
		return businessAccounts;
	}
	public int getThisId(UserAccount user){
		
			String SQL = "SELECT userid FROM USERACCOUNT WHERE username = ?";

			Integer userId = jdbcTemplateObject.queryForObject(SQL, new Object[] { user.getUsername() }, Integer.class);
			return userId;
		
	}
}

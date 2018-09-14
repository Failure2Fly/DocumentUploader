package com.fdmgroup.documentuploader.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import com.fdmgroup.documentuploader.pojo.ServiceLevel;
import com.fdmgroup.documentuploader.pojo.UserAccount;
import com.fdmgroup.documentuploader.rowmapper.BusinessAccountMapper;

@Repository
public class BusinessAccountDao implements Dao<BusinessAccount, Integer> {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(BusinessAccount account) {

		String SQL1 = "INSERT INTO business_account(business_account_id, user_account_owner_id, service_level, user_limit, monthly_file_count, account_name) VALUES(?, ?, ?, ?, ?, ?)";
		String sqlOwnerId = "SELECT user_id FROM user_account WHERE username=?";
		String ownerUsername = account.getOwner().getUsername();
		Integer ownerId = (Integer) jdbcTemplateObject.queryForObject(sqlOwnerId, new Object[] { ownerUsername },
				Integer.class);
		int businessId = getId();
		
		jdbcTemplateObject.update(SQL1, businessId, ownerId, account.getServiceLevel().getServiceLevel().ordinal() + 1,
				account.getUserLimit(), 0, account.getAccountName());
		SQL1 = "INSERT INTO business_to_user (business_user_join_id, user_business_join_id) VALUES(?,?)";
		jdbcTemplateObject.update(SQL1, businessId, ownerId);
	}

	@Override
	public void delete(BusinessAccount account) {
		String SQL = "DELETE FROM business_to_user WHERE business_user_join_id = ?";
		jdbcTemplateObject.update(SQL, account.getBusinessAccountId());
		SQL = "DELETE FROM documents WHERE associated_account_id = ?";
		jdbcTemplateObject.update(SQL, account.getBusinessAccountId());
		SQL = "DELETE FROM business_account WHERE business_account_id = ?";
		jdbcTemplateObject.update(SQL, account.getBusinessAccountId());
	}

	@Override
	public void update(BusinessAccount account) {
		String SQL = "UPDATE business_account SET user_account_owner_id = ?, service_level = ?, user_limit = ?, monthly_file_count = ?, account_name= ? WHERE business_account_id = ?";
		UserAccountDao userDao = (UserAccountDao) DispatchController.getContext().getBean("UserAccountDao");
		Integer ownerId = userDao.getThisId(account.getOwner());
		jdbcTemplateObject.update(SQL, ownerId, account.getServiceLevel().getServiceLevel().ordinal() + 1,
				account.getUserLimit(), account.getMonthlyFileCount(), account.getAccountName(), account.getBusinessAccountId());
		for (UserAccount secondaryUser : account.getUserAccounts()) {
			if (!secondaryUser.getUsername().equals(account.getOwner().getUsername())) {
				SQL = "INSERT INTO business_to_user (business_user_join_id, user_business_join_id) VALUES(?,?)";
				int secondaryUserId = userDao.getThisId(secondaryUser);
				jdbcTemplateObject.update(SQL, account.getBusinessAccountId(), secondaryUserId);
			}
		}
		List<UserAccount> userAccounts = readUsers(account.getBusinessAccountId());
		for (UserAccount secondaryUser : userAccounts) {
			if (!account.getUserAccounts().contains(secondaryUser)) {
				SQL = "DELETE FROM business_to_user WHERE business_user_join_id = ? AND user_business_join_id = ? ";
				jdbcTemplateObject.update(SQL, account.getBusinessAccountId(), userDao.getThisId(secondaryUser));
			}
		}
	}

	public List<BusinessAccount> read(String username) {

		String SQL = "SELECT business_account_id, user_account_owner_id, service_level, user_limit, monthly_file_count, account_name FROM business_account WHERE user_account_owner_id=?";
		String sqlOwnerId = "SELECT user_id FROM user_account WHERE username=?";
		Integer ownerId = (Integer) jdbcTemplateObject.queryForObject(sqlOwnerId, new Object[] { username },
				Integer.class);

		List<BusinessAccount> businessAcounts = new ArrayList<>();
		List<Map<String, Object>> rows = new ArrayList<>();
		rows = jdbcTemplateObject.queryForList(SQL, ownerId);
		for (Map<String, Object> map : rows) {
			BusinessAccount account = new BusinessAccount();
			BigDecimal accountId = (BigDecimal) map.get("business_account_id");
			account.setAccountName((String) map.get("account_name"));
			account.setBusinessAccountId(accountId.intValue());
			BigDecimal serviceLevel = (BigDecimal) map.get("service_level");
			account.setServiceLevel(new ServiceLevel(serviceLevel.intValue()));
			BigDecimal userLimit = (BigDecimal) map.get("user_limit");
			account.setUserLimit(userLimit.intValue());
			BigDecimal monthlyFileCount = (BigDecimal) map.get("monthly_file_count");
			account.setMonthlyFileCount(monthlyFileCount.intValue());
			ApplicationContext context = DispatchController.getContext();
			UserAccountDao userDao = (UserAccountDao) context.getBean("UserAccountDao");
			UserAccount owner = userDao.read(ownerId);
			account.setOwner(owner);

			List<UserAccount> associatedUsers = new ArrayList<>();
			List<Map<String, Object>> innerRows = new ArrayList<>();
			SQL = "SELECT user_business_join_id FROM business_to_user WHERE business_user_join_id = ?";
			innerRows = jdbcTemplateObject.queryForList(SQL, accountId);
			for (Map<String, Object> innerMap : innerRows) {
				UserAccount userAccount = new UserAccount();
				BigDecimal userId = (BigDecimal) innerMap.get("user_business_join_id");
				userAccount = userDao.read(userId.intValue());
			}
			account.setUserAccounts(associatedUsers);

			List<String> fileList = new ArrayList<>();
			innerRows = new ArrayList<>();
			SQL = "SELECT STORED_FILE_PATH FROM documents WHERE associated_account_id = ?";
			innerRows = jdbcTemplateObject.queryForList(SQL, accountId);
			for (Map<String, Object> innerMap : innerRows) {
				String file = (String) innerMap.get("STORED_FILE_PATH");
				fileList.add(file);
			}
			account.setFileList(fileList);

			businessAcounts.add(account);
		}
		return businessAcounts;
	}

	@Override
	public BusinessAccount read(Integer id) {
		String SQL = "SELECT business_account_id, user_account_owner_id, service_level, user_limit, monthly_file_count, account_name FROM business_account WHERE business_account_id = ?";

		BusinessAccount business = jdbcTemplateObject.queryForObject(SQL, new Object[] { id.toString() },
				new BusinessAccountMapper());

		List<UserAccount> associatedUsers = new ArrayList<>();
		List<Map<String, Object>> innerRows = new ArrayList<>();
		SQL = "SELECT user_business_join_id FROM business_to_user WHERE business_user_join_id = ?";
		innerRows = jdbcTemplateObject.queryForList(SQL, business.getBusinessAccountId());
		for (Map<String, Object> innerMap : innerRows) {
			UserAccount userAccount = new UserAccount();
			UserAccountDao userDao = (UserAccountDao) DispatchController.getContext().getBean("UserAccountDao");
			BigDecimal userId = (BigDecimal) innerMap.get("user_business_join_id");
			userAccount = userDao.read(userId.intValue());
			associatedUsers.add(userAccount);
		}
		business.setUserAccounts(associatedUsers);
		List<String> fileList = new ArrayList<>();
		innerRows = new ArrayList<>();
		SQL = "SELECT STORED_FILE_PATH FROM documents WHERE associated_account_id = ?";
		innerRows = jdbcTemplateObject.queryForList(SQL, business.getBusinessAccountId());
		for (Map<String, Object> innerMap : innerRows) {
			String file = (String) innerMap.get("STORED_FILE_PATH");
			fileList.add(file);
		}
		business.setFileList(fileList);

		return business;
	}

	public int getId() {
		String SQL = "SELECT MAX(business_account_id) FROM business_account ";
		try {

			return ((Integer) jdbcTemplateObject.queryForObject(SQL, Integer.class)) + 1;

		} catch (NullPointerException e) {
			return 1;
		}
	}
	

	public List<UserAccount> readUsers(Integer userId) {
		String SQL = "SELECT user_business_join_id  FROM business_to_user WHERE business_user_join_id = ?";
		List<UserAccount> userAccounts = new ArrayList<>();
		List<Map<String, Object>> rows = new ArrayList<>();
		rows = jdbcTemplateObject.queryForList(SQL, userId);
		for (Map<String, Object> map : rows) {
			UserAccount user = new UserAccount();
			UserAccountDao userDao = (UserAccountDao) DispatchController.getContext().getBean("UserAccountDao");
			BigDecimal temp = (BigDecimal) map.get("user_business_join_id");

			user = userDao.read(temp.intValue());
			userAccounts.add(user);
		}
		return userAccounts;
	}

	public void linkUsertoRepository(int userId, int businessId) {
		String SQL = "INSERT INTO business_to_user VALUES(?,?) ";
		jdbcTemplateObject.update(SQL, businessId, userId);

	}
}

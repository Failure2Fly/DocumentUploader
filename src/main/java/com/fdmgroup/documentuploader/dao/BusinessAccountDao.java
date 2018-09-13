package com.fdmgroup.documentuploader.dao;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fdmgroup.documentuploader.controller.DispatchController;
import com.fdmgroup.documentuploader.pojo.BusinessAccount;
import com.fdmgroup.documentuploader.pojo.Questions;
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
		
		String SQL1 = "INSERT INTO BUSINESSACCOUNT(businessaccountid, useraccountownerid, servicelevel, accountname) VALUES(?, ?, ?, ?)";
		String sqlOwnerId = "SELECT UserID FROM useraccount WHERE username=?";
		String ownerUsername = account.getOwner().getUsername();
		Integer ownerId = (Integer) jdbcTemplateObject.queryForObject(sqlOwnerId, new Object[] { ownerUsername },
				Integer.class);
		int businessId= getId();
		File file1 = new File("H:\\businessaccountservicelevel.txt");
		try {
			FileWriter writer = new FileWriter(file1);
			writer.write(ownerId.toString());
			writer.flush();
			writer.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		jdbcTemplateObject.update(SQL1,businessId , ownerId,account.getServicelevel().getServiceLevel().ordinal()+1, account.getAccountName());
		
		SQL1 = "INSERT INTO BUSINESSACCOUNTTOUSERACCOUNT (businessaccountuserjoinid,useraccountbusinessjoinid) VALUES(?,?)";
		jdbcTemplateObject.update(SQL1, businessId, ownerId);

		// UserAccount admin, int businessAccountId, ServiceLevel servicelevel,
		// TODO: write servicelevel
		
	}

	@Override
	public void delete(BusinessAccount account) {
		String SQL = "DELETE FROM BUSINESSACCOUNTTOUSERACCOUNT WHERE businessaccountuserjoinid = ?";
		jdbcTemplateObject.update(SQL, account.getBusinessAccountId());
		SQL = "DELETE FROM DOCUMENTS WHERE associatedaccountid = ?";
		jdbcTemplateObject.update(SQL, account.getBusinessAccountId());
		SQL = "DELETE FROM BUSINESSACCOUNT WHERE businessaccountid = ?";
		jdbcTemplateObject.update(SQL, account.getBusinessAccountId());
	}

	@Override
	public void update(BusinessAccount account) {
		String SQL = "UPDATE BUSINESSACCOUNT SET useraccountownerid=?, servicelevel=?, accountname=? WHERE businessaccountId=?";
		UserAccountDao userDao = (UserAccountDao) DispatchController.getContext().getBean("UserAccountDao");
		Integer ownerId = userDao.getThisId(account.getOwner());
		jdbcTemplateObject.update(SQL, ownerId, account.getServicelevel().getServiceLevel().ordinal()+1, account.getAccountName(), account.getBusinessAccountId());
		for(UserAccount secondaryUser:account.getUserAccounts()){
			
			if(!secondaryUser.getUsername().equals(account.getOwner().getUsername())){
				
				SQL = "INSERT INTO BUSINESSACCOUNTTOUSERACCOUNT (businessaccountuserjoinid,useraccountbusinessjoinid) VALUES(?,?)";
				int secondaryUserId = userDao.getThisId(secondaryUser);
				jdbcTemplateObject.update(SQL, account.getBusinessAccountId(),secondaryUserId );
				
			}
		}
		List<UserAccount> userAccounts = readUsers(account.getBusinessAccountId());
		for(UserAccount secondaryUser:userAccounts){
			if(!account.getUserAccounts().contains(secondaryUser)){
				SQL = "DELETE FROM BUSINESSACCOUNTTOUSERACCOUNT WHERE businessaccountuserjoinid = ? AND useraccountbusinessjoinid = ? ";
				jdbcTemplateObject.update(SQL, account.getBusinessAccountId(),userDao.getThisId(secondaryUser));
				
			}
		}
		
	}

	public List<BusinessAccount> read(String username) {
		
		String SQL = "SELECT businessaccountid, useraccountownerid, servicelevel, accountname FROM BUSINESSACCOUNT WHERE useraccountownerid=?";
		String sqlOwnerId = "SELECT UserID FROM useraccount WHERE username=?";
	    Integer ownerId = (Integer) jdbcTemplateObject.queryForObject(sqlOwnerId, new Object[] { username }, Integer.class);
	   
		List<BusinessAccount> businessAccounts = new ArrayList<>();
		List<Map<String, Object>> rows = new ArrayList<>();
		rows = jdbcTemplateObject.queryForList(SQL, ownerId);
		for(Map<String, Object> map : rows){
			BusinessAccount account = new BusinessAccount();
			BigDecimal accountId =(BigDecimal) map.get("businessaccountid");
			account.setAccountName((String)map.get("accountname"));
			account.setBusinessAccountId(accountId.intValue());
			BigDecimal serviceLevel =(BigDecimal) map.get("servicelevel");
			account.setServicelevel(new ServiceLevel(serviceLevel.intValue()));
			ApplicationContext context= DispatchController.getContext(); 
			UserAccountDao userDao = (UserAccountDao) context.getBean("UserAccountDao");
			UserAccount owner = userDao.read(ownerId);
			account.setOwner(owner);
			
			List<UserAccount> associatedUsers = new ArrayList<>();
			List<Map<String,Object>> innerRows = new ArrayList<>();
			SQL="SELECT useraccountbusinessjoinid FROM BUSINESSACCOUNTTOUSERACCOUNT WHERE businessaccountuserjoinid = ?";
			innerRows=jdbcTemplateObject.queryForList(SQL, accountId);
			for(Map<String, Object> innerMap:innerRows){
				UserAccount userAccount = new UserAccount();
				BigDecimal userId= (BigDecimal) innerMap.get("useraccountbusinessjoinid");
				userAccount = userDao.read(userId.intValue());
				associatedUsers.add(userAccount);
			}
			account.setUserAccounts(associatedUsers);
			
			List<String> fileList = new ArrayList<>();
			innerRows = new ArrayList<>();
			SQL="SELECT STOREDFILEPATH FROM documents WHERE ASSOCIATEDACCOUNTID = ?";
			innerRows=jdbcTemplateObject.queryForList(SQL, accountId);
			for(Map<String, Object> innerMap : innerRows){
				String file =(String)innerMap.get("STOREDFILEPATH");
				fileList.add(file);
			}
			account.setFileList(fileList);
			businessAccounts.add(account);
		}
		return businessAccounts;
	}

	@Override
	public BusinessAccount read(Integer id) {
		String SQL = "SELECT businessaccountid, useraccountownerid, servicelevel, accountname FROM BUSINESSACCOUNT WHERE businessaccountid=?";
		
		BusinessAccount business = jdbcTemplateObject.queryForObject(SQL, new Object[]{id.toString()}, new BusinessAccountMapper());
		
		List<UserAccount> associatedUsers = new ArrayList<>();
		List<Map<String,Object>> innerRows = new ArrayList<>();
		SQL="SELECT useraccountbusinessjoinid FROM BUSINESSACCOUNTTOUSERACCOUNT WHERE businessaccountuserjoinid = ?";
		innerRows=jdbcTemplateObject.queryForList(SQL, business.getBusinessAccountId());
		for(Map<String, Object> innerMap:innerRows){
			UserAccount userAccount = new UserAccount();
			UserAccountDao userDao= (UserAccountDao) DispatchController.getContext().getBean("UserAccountDao");
			BigDecimal userId= (BigDecimal) innerMap.get("useraccountbusinessjoinid");
			userAccount = userDao.read(userId.intValue());
			associatedUsers.add(userAccount);
		}
		business.setUserAccounts(associatedUsers);

		return business;
	}

	public int getId() {
		String SQL = "SELECT MAX(BUSINESSACCOUNTID) FROM BUSINESSACCOUNT ";
		try {
			
			return ((Integer) jdbcTemplateObject.queryForObject(SQL, Integer.class))+1;

		} catch (NullPointerException e) {
			return 1;
		}
   }
	public List<UserAccount> readUsers(Integer userId) {
		String SQL = "SELECT USERACCOUNTBUSINESSJOINID  FROM BUSINESSACCOUNTTOUSERACCOUNT WHERE BUSINESSACCOUNTUSERJOINID = ?";
		List<UserAccount> userAccounts = new ArrayList<>();
		List<Map<String, Object>> rows = new ArrayList<>();
		rows = jdbcTemplateObject.queryForList(SQL, userId);
		for (Map<String, Object> map : rows) {
			UserAccount user = new UserAccount();
			UserAccountDao userDao = (UserAccountDao) DispatchController.getContext().getBean("UserAccountDao");
			BigDecimal temp =(BigDecimal) map.get("USERACCOUNTBUSINESSJOINID");
			
			user = userDao.read(temp.intValue());
			userAccounts.add(user);
		}
		return userAccounts;
	}
	
	
	public void linkUsertoRepository(int userId,int businessId){
		String SQL = "INSERT INTO BUSINESSACCOUNTTOUSERACCOUNT VALUES(?,?) ";
		jdbcTemplateObject.update(SQL,businessId,userId);

	}

}

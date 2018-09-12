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
		
		String SQL1 = "INSERT INTO BUSINESSACCOUNT(businessaccountid, useraccountownerid, servicelevel, accountname) VALUES(?, ?, null, ?)";
		String sqlOwnerId = "SELECT UserID FROM useraccount WHERE username=?";
		String ownerUsername = account.getOwner().getUsername();
		Integer ownerId = (Integer) jdbcTemplateObject.queryForObject(sqlOwnerId, new Object[] { ownerUsername },
				Integer.class);
		int businessId= getId();
		jdbcTemplateObject.update(SQL1,businessId , ownerId, account.getAccountName());
		
		SQL1 = "INSERT INTO BUSINESSACCOUNTTOUSERACCOUNT (businessaccountuserjoinid,useraccountbusinessjoinid) VALUES(?,?)";
		jdbcTemplateObject.update(SQL1, businessId, ownerId);

		// UserAccount admin, int businessAccountId, ServiceLevel servicelevel,
		// List<UserAccount> userAccounts, List<String> fileList
		// TODO: write servicelevel
		
	}

	@Override
	public void delete(BusinessAccount account) {
		String SQL = "DELETE FROM BUSINESSACCOUNTTOUSERACCOUNT WHERE businessaccountuserjoinid = ?";
		jdbcTemplateObject.update(SQL, account.getBusinessAccountId());
		SQL = "DELETE FROM BUSINESSACCOUNT WHERE businessaccountid = ?";
		jdbcTemplateObject.update(SQL, account.getBusinessAccountId());
	}

	@Override
	public void update(BusinessAccount account) {
		String SQL = "UPDATE BUSINESSACCOUNT SET useraccountownerid=?, servicelevel=?, accountname=?";
		String sqlOwnerId = "SELECT UserID FROM useraccount WHERE username=?";
		String ownerUsername = account.getOwner().getUsername();
		Integer ownerId = (Integer) jdbcTemplateObject.queryForObject(sqlOwnerId, new Object[] { ownerUsername },
				Integer.class);
		jdbcTemplateObject.update(SQL, ownerId, account.getServicelevel(), account.getAccountName());
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
				userAccount.setFirstName((String)innerMap.get("firstname"));
				userAccount.setLastName((String)innerMap.get("lastname"));
				userAccount.setUsername((String)innerMap.get("username"));
				userAccount.setPassword((String)innerMap.get("password"));
				userAccount.setUserEmail((String)innerMap.get("useremail"));
				List<Questions> listQA = null;
				userAccount.setListQA(listQA);
				associatedUsers.add(userAccount);
			}
			account.setUserAccounts(associatedUsers);
			
			List<String> fileList = new ArrayList<>();
			innerRows = new ArrayList<>();
			SQL="SELECT filename FROM documents WHERE associatedaccountid = ?";
			innerRows=jdbcTemplateObject.queryForList(SQL, accountId);
			for(Map<String, Object> innerMap : innerRows){
				String file =(String)innerMap.get("filename");
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
		
		File file = new File("H:\\DebugBusinessDao.txt");
		try {
			FileWriter writer = new FileWriter(file);

			writer.write("Account id:"+id.toString()+"\n SQL "+SQL+"\nIs jdbcTemplate null?"+Objects.isNull(jdbcTemplateObject));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BusinessAccount business = jdbcTemplateObject.queryForObject(SQL, new Object[]{id.toString()}, new BusinessAccountMapper());

		return business;
	}

	private int getId() {
		String SQL = "SELECT MAX(BUSINESSACCOUNTID) FROM BUSINESSACCOUNT ";
		try {
			
			return ((Integer) jdbcTemplateObject.queryForObject(SQL, Integer.class))+1;

		} catch (NullPointerException e) {
			return 1;
		}
   }
	public void linkUsertoRepository(int userId,int businessId){
		String SQL = "INSERT INTO BUSINESSACCOUNTTOUSERACCOUNT VALUES(?,?) ";
		jdbcTemplateObject.update(SQL,businessId,userId);

	}

}

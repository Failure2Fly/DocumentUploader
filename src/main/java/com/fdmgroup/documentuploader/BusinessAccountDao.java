package com.fdmgroup.documentuploader;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleDriver;

@Repository
public class BusinessAccountDao implements DAOExample<BusinessAccount, Integer> {


   private DataSource dataSource;
   private JdbcTemplate jdbcTemplateObject;
   
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }


	@Override
	public void create(BusinessAccount account) {
		//UserAccount admin, int businessAccountId, ServiceLevel servicelevel,
		//List<UserAccount> userAccounts, List<String> fileList
		//TODO: write servicelevel
		File file = new File("H:\\DebugInCreate.txt");
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(account.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String SQL1="INSERT INTO BUSINESSACCOUNT(businessaccountid, useraccountownerid, servicelevel, accountname) VALUES(businessaccount_seq.nextval, ?, null, ?)";
		String sqlOwnerId = "SELECT UserID FROM useraccount WHERE username=?";
		String ownerUsername =account.getOwner().getUsername();
	    Integer ownerId = (Integer) jdbcTemplateObject.queryForObject(
	    		sqlOwnerId, new Object[] { ownerUsername }, Integer.class);	
	    
		jdbcTemplateObject.update(SQL1, ownerId, account.getAccountName());
		
	}

	@Override
	public void delete(BusinessAccount account) {
		String SQL = "DELETE FROM BUSINESSACCOUNT WHERE businessaccountid = ?";
		jdbcTemplateObject.update(SQL,account.getBusinessAccountId());
	}

	@Override
	public void update(BusinessAccount account) {
		String SQL = "UPDATE BUSINESSACCOUNT SET useraccountownerid=?, servicelevel=?, accountname=?";
		String sqlOwnerId = "SELECT UserID FROM useraccount WHERE username=?";
		String ownerUsername =account.getOwner().getUsername();
	    Integer ownerId = (Integer) jdbcTemplateObject.queryForObject(
	    		sqlOwnerId, new Object[] { ownerUsername }, Integer.class);
		jdbcTemplateObject.update(SQL, ownerId,account.getServicelevel(),account.getAccountName());
	}
	

	public BusinessAccount read(String username) {
		//TODO : will fail when user has multiple accounts
		String SQL = "SELECT businessaccountid, useraccountownerid, servicelevel, accountname FROM BUSINESSACCOUNT WHERE useraccountownerid=?";
		String sqlOwnerId = "SELECT UserID FROM useraccount WHERE username=?";
			    Integer ownerId = (Integer) jdbcTemplateObject.queryForObject(
	    		sqlOwnerId, new Object[] { username }, Integer.class);
		
		BusinessAccount business = jdbcTemplateObject.queryForObject(SQL, new Object[]{ownerId}, new BusinessAccountMapper());
		return business;
	}


	@Override
	public BusinessAccount read(Integer id) {
		String SQL = "SELECT businessaccountid, useraccountownerid, servicelevel, accountname FROM BUSINESSACCOUNT WHERE businessaccountid=?";
		String sqlOwnerId = "SELECT UserID FROM useraccount WHERE userid=?";
			    Integer ownerId = (Integer) jdbcTemplateObject.queryForObject(
	    		sqlOwnerId, new Object[] { id }, Integer.class);
		
		BusinessAccount business = jdbcTemplateObject.queryForObject(SQL, new Object[]{ownerId}, new BusinessAccountMapper());
		return business;
	}



}


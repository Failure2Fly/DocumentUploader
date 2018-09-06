package com.fdmgroup.documentuploader;


import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleDriver;

@Repository
public class BusinessAccountDAO implements DAOExample<BusinessAccount, Integer> {


   private DataSource dataSource;
   private JdbcTemplate jdbcTemplateObject;
   
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }


	@Override
	public void create(BusinessAccount item) {
		//UserAccount admin, int businessAccountId, ServiceLevel servicelevel,
		//List<UserAccount> userAccounts, List<String> fileList
		String SQL1="INSERT INTO BUSINESSACCOUNT(businessaccountid, useraccountownerid, servicelevel) VALUES(businessaccount_seq.nextval, ?, ?)";
		String sqlOwnerId = "SELECT UserID FROM useraccount WHERE username=?";
		String ownerUsername =item.getOwner().getUsername();
	    Integer ownerId = (Integer) jdbcTemplateObject.queryForObject(
	    		sqlOwnerId, new Object[] { ownerUsername }, Integer.class);		
		jdbcTemplateObject.update(SQL1, ownerId,item.getServicelevel());
		
	}

	@Override
	public void delete(BusinessAccount item) {
		String SQL = "DELETE FROM BUSINESSACCOUNT WHERE businessaccountid = ?";
		jdbcTemplateObject.update(SQL,item.getBusinessAccountId());
	}

	@Override
	public void update(BusinessAccount item) {
		String SQL = "UPDATE BUSINESSACCOUNT SET useraccountownerid=?, servicelevel=?";
		String sqlOwnerId = "SELECT UserID FROM useraccount WHERE username=?";
		String ownerUsername =item.getOwner().getUsername();
	    Integer ownerId = (Integer) jdbcTemplateObject.queryForObject(
	    		sqlOwnerId, new Object[] { ownerUsername }, Integer.class);
		jdbcTemplateObject.update(SQL, ownerId,item.getServicelevel());
	}
	

	public BusinessAccount read(String username) {
		String SQL = "SELECT businessaccountid, servicelevel FROM BUSINESSACCOUNT WHERE useraccountownerid=?";
		String sqlOwnerId = "SELECT UserID FROM useraccount WHERE username=?";
			    Integer ownerId = (Integer) jdbcTemplateObject.queryForObject(
	    		sqlOwnerId, new Object[] { username }, Integer.class);
		
		BusinessAccount business = jdbcTemplateObject.queryForObject(SQL, new Object[]{ownerId}, new BusinessAccountMapper());
		return business;
	}


	@Override
	public BusinessAccount read(Integer item) {
		// TODO Auto-generated method stub
		return null;
	}



}


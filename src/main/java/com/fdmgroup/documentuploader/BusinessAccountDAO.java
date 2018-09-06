package com.fdmgroup.documentuploader;


import java.util.List;

import javax.sql.DataSource;

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
		String SQL1="INSERT INTO BUSINESSACCOUNT(businessaccountid, owner, servicelevel) VALUES(businessaccount_seq.nextval, ?, ?)";
		jdbcTemplateObject.update(SQL1, item.getOwner(),item.getServicelevel());
		
	}

	@Override
	public void delete(BusinessAccount item) {
		String SQL = "DELETE FROM BUSINESSACCOUNT WHERE businessaccountid = ?";
		jdbcTemplateObject.update(SQL,item.getOwner());
	}

	@Override
	public void update(BusinessAccount item) {
		String SQL = "UPDATE BUSINESSACCOUNT SET owner=?, servicelevel=?";
		jdbcTemplateObject.update(SQL, item.getOwner(),item.getServicelevel());
	}
	
/*	@Override
	public BusinessAccount readAll(List<BusinessAccount> item) {
		// TODO Auto-generated method stub
		return null;
	}*/

	public BusinessAccount read(String owner) {
		String SQL = "SELECT owner, servicelevel FROM BUSINESSACCOUNT WHERE admin=?";
		BusinessAccount business = jdbcTemplateObject.queryForObject(SQL, new Object[]{owner}, new BusinessAccountMapper());
		return business;
	}


	@Override
	public BusinessAccount read(Integer item) {
		// TODO Auto-generated method stub
		return null;
	}



}


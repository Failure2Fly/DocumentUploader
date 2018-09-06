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
		String SQL1="INSERT INTO BUSINESSACCOUNT(businessaccountid, admin, servicelevel) VALUES(businessaccount_seq.nextval, ?, ?)";
		jdbcTemplateObject.update(SQL1, item.getAdmin(),item.getServicelevel());
		
	}

	@Override
	public void delete(BusinessAccount item) {
		String SQL = "DELETE FROM BUSINESSACCOUNT WHERE businessaccountid = ?";
		jdbcTemplateObject.update(SQL,item.getAdmin());
	}

	@Override
	public void update(BusinessAccount item) {
		String SQL = "UPDATE BUSINESSACCOUNT SET admin=?, servicelevel=?";
		jdbcTemplateObject.update(SQL, item.getAdmin(),item.getServicelevel());
	}
	
/*	@Override
	public BusinessAccount readAll(List<BusinessAccount> item) {
		// TODO Auto-generated method stub
		return null;
	}*/

	public BusinessAccount read(String admin) {
		String SQL = "SELECT admin, servicelevel FROM BUSINESSACCOUNT WHERE admin=?";
		BusinessAccount business = jdbcTemplateObject.queryForObject(SQL, new Object[]{admin}, new BusinessAccountMapper());
		return admin;
	}


}


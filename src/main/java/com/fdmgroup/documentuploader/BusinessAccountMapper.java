package com.fdmgroup.documentuploader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;

public class BusinessAccountMapper implements RowMapper<BusinessAccount> {

	@Override
	public BusinessAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		BusinessAccount business = new BusinessAccount();
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		UserAccountJdbcTemplate dao = (UserAccountJdbcTemplate)context.getBean("UserAccountJdbcTemplate");
		UserAccount owner = dao.read(rs.getInt("useraccountownerid"));
		business.setBusinessAccountId(rs.getInt("businessaccountid"));
		business.setOwner(owner);
		business.setAccountName(rs.getString("accountname"));
		
		//TODO: ServiceLevel DAO
		business.setServicelevel(null);
		
		return business;
	}

}

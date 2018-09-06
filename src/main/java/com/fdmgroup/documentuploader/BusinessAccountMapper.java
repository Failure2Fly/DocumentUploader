package com.fdmgroup.documentuploader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class BusinessAccountMapper implements RowMapper<BusinessAccount> {

	@Override
	public BusinessAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		BusinessAccount business = new BusinessAccount();
		//business.setOwner(rs.getString("admin"));
		//business.setServicelevel(rs.getString("servicelevel"));
		Map<BusinessAccount, UserAccount> rsAccountMap = new HashMap<>();
		return business;
	}

}

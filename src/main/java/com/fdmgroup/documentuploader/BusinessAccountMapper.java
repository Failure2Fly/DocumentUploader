package com.fdmgroup.documentuploader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BusinessAccountMapper implements RowMapper<BusinessAccount> {

	@Override
	public BusinessAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		BusinessAccount business = new BusinessAccount();
		business.setAdmin(rs.getString("admin"));
		business.setServicelevel(rs.getString("servicelevel"));
		return null;
	}

}

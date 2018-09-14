package com.fdmgroup.documentuploader.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.fdmgroup.documentuploader.pojo.UserAccount;

public class UserAccountMapper implements RowMapper<UserAccount> {
	public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserAccount user = new UserAccount();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("user_password"));
		user.setUserEmail(rs.getString("user_email"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		return user;
	}
}

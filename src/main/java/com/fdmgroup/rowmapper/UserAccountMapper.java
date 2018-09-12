package com.fdmgroup.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.fdmgroup.pojo.UserAccount;

public class UserAccountMapper implements RowMapper<UserAccount> {
	public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserAccount user = new UserAccount();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("userpassword"));
		user.setUserEmail(rs.getString("useremail"));
		user.setFirstName(rs.getString("firstname"));
		user.setLastName(rs.getString("lastname"));
		return user;
	}
}

package com.fdmgroup.documentuploader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class UserAccountMapper implements RowMapper<UserAccount> {
	public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserAccount user = new UserAccount();
		//TODO comeback after sql database coded
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("userpassword"));
		user.setEmailAddress(rs.getString("useremail"));
		user.setFirstName(rs.getString("firstname"));
		user.setLastName(rs.getString("lastname"));
		Map<SecurityQuestion,String> rsQuestionMap = new HashMap<>();

	    rsQuestionMap.put(SecurityQuestion.valueOf(rs.getString("question").toUpperCase().replace(" ", "_").replace("?", "")),rs.getString("questionanswer"));
	    while(rs.next()){
	    	rsQuestionMap.put(SecurityQuestion.valueOf(rs.getString("question").toUpperCase().replace(" ", "_").replace("?", "")),rs.getString("questionanswer"));
	    }
		user.setMapQA(rsQuestionMap);

		return user;
	}
}

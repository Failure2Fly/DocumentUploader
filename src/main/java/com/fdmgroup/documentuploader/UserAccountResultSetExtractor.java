package com.fdmgroup.documentuploader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.ResultSetExtractor;

@SuppressWarnings("rawtypes")
public class UserAccountResultSetExtractor implements ResultSetExtractor {

	    @Override
	    public Object extractData(ResultSet rs) throws SQLException {
	    	UserAccount user = new UserAccount();
	        user.setUsername(rs.getString(1));
	        user.setPassword(rs.getString(2));
	        user.setUserEmail(rs.getString(3));
	        user.setFirstName(rs.getString(4));
	        user.setLastName(rs.getString(5));
	        List<Questions> rsQuestionList = new ArrayList<>();
	        rsQuestionList.add(new Questions(SecurityQuestion.valueOf(rs.getString(6).toUpperCase().replace(" ", "_").replace("?", "")),rs.getString(7)));
	        while(rs.next()){
	        	rsQuestionList.add(new Questions(SecurityQuestion.valueOf(rs.getString(6).toUpperCase().replace(" ", "_").replace("?", "")),rs.getString(7)));
	        }
	        user.setListQA(rsQuestionList);
	        return user;
	    }
}

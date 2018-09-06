package com.fdmgroup.documentuploader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.ResultSetExtractor;

@SuppressWarnings("rawtypes")
public class UserAccountResultSetExtractor implements ResultSetExtractor {

	    @Override
	    public Object extractData(ResultSet rs) throws SQLException {
	    	UserAccount user = new UserAccount();
	        user.setUsername(rs.getString(1));
	        user.setPassword(rs.getString(2));
	        user.setEmailAddress(rs.getString(3));
	        user.setFirstName(rs.getString(4));
	        user.setLastName(rs.getString(5));
	        Map<SecurityQuestion,String> rsQuestionMap = new HashMap<>();
	        //rsQuestionMap.put(SecurityQuestion.valueOf(rs.getString(6).toUpperCase().replace(" ", "_").replace("?", "")),rs.getString(7));
	        //while(rs.next()){
		        //rsQuestionMap.put(SecurityQuestion.valueOf(rs.getString(6).toUpperCase().replace(" ", "_").replace("?", "")),rs.getString(7));
	        //}
	        user.setMapQA(rsQuestionMap);
	        return user;
	    }
	    
	    
}

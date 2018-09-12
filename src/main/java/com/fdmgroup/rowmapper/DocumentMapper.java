package com.fdmgroup.rowmapper;

import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fdmgroup.pojo.Document;

public class DocumentMapper implements RowMapper {

	public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
		Document document = new Document();
		//TODO comeback after sql database coded
		document.setName(rs.getString("filename"));
		document.setDate(rs.getDate("storedate"));
		document.setAccountId(rs.getInt("associatedaccountid"));
		document.setRepositoryPath(Paths.get(rs.getString("storedfilepath")));
		document.setSourcePath(null);
//		Map<SecurityQuestion,String> rsQuestionMap = new HashMap<>();
//	    rsQuestionMap.put(SecurityQuestion.valueOf(rs.getString("question").toUpperCase().replace(" ", "_").replace("?", "")),rs.getString("questionanswer"));
//	    while(rs.next()){
//	    	rsQuestionMap.put(SecurityQuestion.valueOf(rs.getString("question").toUpperCase().replace(" ", "_").replace("?", "")),rs.getString("questionanswer"));
//	    }
//		user.setMapQA(rsQuestionMap);
		return document;
	}

}
